package com.example.preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView checkbox, ringtone, txtScreen;
    EditText text;
    Button btnOk;
    SharedPreferences prefs; //환경설정 xml 파일
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox=findViewById(R.id.checkbox);
        ringtone=findViewById(R.id.ringtone);
        txtScreen=findViewById(R.id.screen);
        text=findViewById(R.id.text);
        btnOk=findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //환경설정 편집 모드로 진입
                SharedPreferences.Editor edit=prefs.edit();
                // putString(key,value)
                edit.putString("text",text.getText().toString());
                // 저장 완료
                edit.commit();
                Toast.makeText(getApplicationContext(),
"환경설정이 변경되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 추가, 메뉴를 누르면 환경설정 화면으로 이동
        menu.add("환경설정").setIntent(
                new Intent(this, EditPreferences.class));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 환경설정 정보가 저장된 xml 파일 로딩
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        // 환경설정 정보는 기본적으로 스트링으로 저장되며
        // 형변환 가능함
        float font_size=Float.parseFloat(prefs.getString("font",
                "10"));
        ringtone.setTextSize(TypedValue.COMPLEX_UNIT_SP, font_size);
        checkbox.setTextSize(TypedValue.COMPLEX_UNIT_SP, font_size);
        String color=prefs.getString("font_color","#ffffff");

        checkbox.setTextColor(Color.parseColor(color));
        ringtone.setTextColor(Color.parseColor(color));
        txtScreen.setTextColor(Color.parseColor(color));
        //체크박스의 경우 boolean 으로 리턴
        boolean ch=prefs.getBoolean("checkbox",false);
        checkbox.setText(""+ch);
        boolean screenCheck=prefs.getBoolean("screenOn",false);
        txtScreen.setText(""+screenCheck);
        String ring=prefs.getString("ringtone","<unset>");
        ringtone.setText(ring);
        if(screenCheck){
            //화면 조명 유지 옵션
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);;
        }
        String str=prefs.getString("text","");
        text.setText(str);
    }
}








