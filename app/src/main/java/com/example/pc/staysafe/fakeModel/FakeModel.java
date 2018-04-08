package com.example.pc.staysafe.fakeModel;

import java.util.ArrayList;

public class FakeModel {
    private int pageCount = 10;

    ArrayList<FakePage> fkpages;
    public FakeModel()
    {
        fkpages = new ArrayList<>();
        for (int i = 0; i < pageCount; i++){
            FakePage onePage = new FakePage();
            onePage.subtitle = "Trolololo";
            onePage.text = "jkgafslnhbgfeshnjkagjafsdkjgvkjasnkfjgnkdjfngvkjsnkjfgkjhnkjdfnhilu";
            fkpages.add(onePage);
        }
    }

    public ArrayList<FakePage> getFkpages() {
        return fkpages;
    }
}
