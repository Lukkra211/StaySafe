package com.example.pc.staysafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main menu of the application. Points to different places in application
 */
public class HomeActivity extends AppCompatActivity {
    Button virtualDanger, realDanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initParams();
        initUI();
    }

    private void initParams() {
        realDanger = findViewById(R.id.home_btn_real);
        virtualDanger = findViewById(R.id.home_btn_virtual);
    }

    private void initUI() {
        realDanger.setOnClickListener(dangerListener);
        virtualDanger.setOnClickListener(dangerListener);
    }

    /**
     * Listener triggered by buttons selecting the type of a danger
     */
    private View.OnClickListener dangerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String type = (view.getId() == R.id.home_btn_real) ? "real" : "virtual";
            Intent intent = new Intent(getBaseContext(), DangerActivity.class);
            intent.putExtra("dangerType", type);

            startActivity(intent);
        }
    };
}
