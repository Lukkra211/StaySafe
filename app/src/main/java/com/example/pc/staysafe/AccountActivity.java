package com.example.pc.staysafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.pc.staysafe.model.RemoteModel;

import org.json.JSONObject;

public class AccountActivity extends AppCompatActivity {
    private boolean passwordMatch;
    private boolean inRegisterView;
    private CheckFilledEditText checkFilledEditText;
    private CheckMatchingPassword checkMatchingPassword;

    private EditText editText_nick;
    private EditText editText_password;
    private EditText editText_repeatPassword;
    private Button button_action;
    private Button button_switch;
    private TextView textView_header;
    private TextView textView_passwordMatching;

    private RemoteModel remoteModel;

    private View.OnClickListener loginButtonListener;
    private View.OnClickListener registerButtonListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initParams();
        initUI();
    }

    private void initParams() {
        remoteModel = new RemoteModel(this);

        loginButtonListener = new LoginViewOnClickListener();
        registerButtonListener = new RegisterViewOnListener();
        checkFilledEditText = new CheckFilledEditText();
        checkMatchingPassword = new CheckMatchingPassword();

        editText_nick = findViewById(R.id.account_activity_editText_nick);
        editText_password = findViewById(R.id.account_activity_editText_password);
        editText_repeatPassword = findViewById(R.id.account_activity_editText_repeatPassword);
        button_action = findViewById(R.id.account_activity_button_action);
        button_switch = findViewById(R.id.account_activity_button_switch);
        textView_header = findViewById(R.id.account_textView_header);
        textView_passwordMatching = findViewById(R.id.register_activity_textView_paswordMatching);
    }

    private void initUI() {
        editText_nick.addTextChangedListener(checkFilledEditText);
        editText_password.addTextChangedListener(checkFilledEditText);
        editText_repeatPassword.addTextChangedListener(checkFilledEditText);

        button_action.setOnClickListener(loginButtonListener);
        button_switch.setOnClickListener(loginButtonListener);

        inRegisterView = false;
    }

    /* =============================================================================================
     * Used in both view
     * =============================================================================================
     */

    private Response.ErrorListener errorListener = error -> {
        String defaultMessage = "Unspecified net error. Please check your internet connection.";
        String message = (error.getMessage() == null) ? error.getMessage() : defaultMessage;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    };

    private class CheckFilledEditText implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            button_action.setEnabled((!inRegisterView || passwordMatch) && areFilled());
        }

        /**
         * Check if all EditTexts are filled
         * {@code editText_repeatPassword} is checked only if {@code inRegisterView} is {@code true}
         *
         * @return if EditTexts are filled, returns true, otherwise false
         */
        private boolean areFilled() {
            return filled(editText_nick) && filled(editText_password)
                    && (filled(editText_repeatPassword) || !inRegisterView);
        }

        private boolean filled(EditText editText) {
            return !TextUtils.isEmpty(editText.getText().toString().trim());
        }
    }

    /* =============================================================================================
     * Used in login view
     * =============================================================================================
     */
    private class LoginViewOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.account_activity_button_action:
                    String nick = editText_nick.getText().toString();
                    String password = editText_password.getText().toString();
                    remoteModel.login(nick, password, onLoginSuccess, null);
                    break;

                case R.id.account_activity_button_switch:
                    switchToRegister();
                    break;
            }
        }
    }

    private void switchToRegister() {
        inRegisterView = true;

        // Button
        button_action.setOnClickListener(registerButtonListener);
        button_action.setText(getText(R.string.account_button_action_register));
        button_switch.setOnClickListener(registerButtonListener);
        button_switch.setText(getText(R.string.account_button_switch_to_register));

        // TextView
        textView_header.setText(getText(R.string.account_textView_header_register));

        // EditText
        editText_repeatPassword.setVisibility(View.VISIBLE);

        editText_password.addTextChangedListener(checkMatchingPassword);
        editText_repeatPassword.addTextChangedListener(checkMatchingPassword);

        // Run EditText's listeners to verify if register should be enabled with current data
        checkFilledEditText.afterTextChanged(null);
        checkMatchingPassword.afterTextChanged(null);
    }

    private Response.Listener<JSONObject> onLoginSuccess = response -> {
        if (RemoteModel.getUser() != null) {
            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
            finish();
        }
    };

    /* =============================================================================================
     * Used in register view
     * =============================================================================================
     */
    private class RegisterViewOnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.account_activity_button_action:
                    String nick = editText_nick.getText().toString();
                    String password = editText_password.getText().toString();
                    remoteModel.register(nick, password, onRegisterSuccess, null);
                    break;

                case R.id.account_activity_button_switch:
                    switchToLogin();
                    break;
            }
        }
    }

    private void switchToLogin() {
        inRegisterView = false;

        // Buttons
        button_action.setOnClickListener(loginButtonListener);
        button_action.setText(getText(R.string.account_button_action_login));
        button_switch.setOnClickListener(loginButtonListener);
        button_switch.setText(getText(R.string.account_button_switch_to_login));

        // TextView
        textView_header.setText(getText(R.string.account_textView_header_login));

        // EditText
        editText_repeatPassword.setVisibility(View.INVISIBLE);
        textView_passwordMatching.setVisibility(View.INVISIBLE);

        editText_password.removeTextChangedListener(checkMatchingPassword);
        editText_repeatPassword.removeTextChangedListener(checkMatchingPassword);
    }

    private Response.Listener<JSONObject> onRegisterSuccess = response -> {
        startActivity(new Intent(this, AccountActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.do_nothing);
        finish();
    };

    /**
     * Checks if passwords match
     *
     * Updates {@code passwordMatch} and sets visibility of {@code textView_passwordMatching}
     * This TextWatcher have to be called after {@code CheckFilledEditText}.
     */
    private class CheckMatchingPassword implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            passwordMatch = same();
            button_action.setEnabled(passwordMatch);
            textView_passwordMatching.setVisibility(passwordMatch ? View.INVISIBLE : View.VISIBLE);
        }

        private boolean same() {
            return editText_password.getText().toString().equals(
                    editText_repeatPassword.getText().toString());
        }
    };
}
