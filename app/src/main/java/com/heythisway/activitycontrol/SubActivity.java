package com.heythisway.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button btnReturn;
    String value = "";
    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        btnReturn = (Button) findViewById(R.id.btnReturn);

        // 이전 액티비티에서 넘어온 인텐트 객체를 받아온다.
        Intent intent = getIntent();
        // 값의 묶음을 꺼낸다.
        Bundle bundle = intent.getExtras();
        // 받아온 인텐트의 엑스트라에 값이 null인 경우 대비해 if문으로 체크
        // 조건문이 없으면 엑스트라 값이 null인 경우 null point exception 에러가 발생한다. (null.getString())
        if (bundle != null) {
            value = bundle.getString("key");
            if (value.equals("")) {
                value = "0";
            }
            textView.setText("입력하신 값 : " + value);
        }
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num2;
                temp = editText.getText().toString();
                if (!value.equals("")) {
                    int num1 = Integer.parseInt(value);
                    if (!temp.equals("")) {
                        num2 = Integer.parseInt(temp);
                    } else {
                        num2 = 0;
                    }
                    int result = num1 + num2;

                    int[] num = {num1, num2, result};

                    /**
                     * 인텐트의 값 반환하기
                     * 1. 결과값을 인텐트에 담는다.
                     * 2. 값을 setResult 메서드를 이용해 넘겨준다.
                     * 3. 액티비티를 종료한다.
                     */
                    Intent intent = new Intent();
                    intent.putExtra("result", num);

                    setResult(RESULT_OK, intent);

                }

                finish();
            }
        });
    }
}
