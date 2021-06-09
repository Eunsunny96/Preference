package com.example.preference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class EditPreferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //프레그먼트-화면 구성 요소
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new MyFragment()).commit();
    }

    public static class MyFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // res/xml/preferences.xml 을 화면으로 사용함
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}



