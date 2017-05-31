package com.heythisway.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnForResult;
    Intent intentForSubActivity;
    EditText inputValue;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentForSubActivity = new Intent(this, SubActivity.class);

        btnForResult = (Button) findViewById(R.id.btnForResult);
        btnStart = (Button) findViewById(R.id.btnStart);
        inputValue = (EditText) findViewById(R.id.inputValue);
        textResult = (TextView) findViewById(R.id.textResult);

        btnForResult.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }

    public static final int BUTTON_RESULT = 99;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startActivity(intentForSubActivity);
                inputValue.setText("");
                break;
            case R.id.btnForResult:
                intentForSubActivity.putExtra("key", inputValue.getText().toString());
                startActivityForResult(intentForSubActivity, BUTTON_RESULT);
                inputValue.setText("");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case BUTTON_RESULT:
                    int[] result = data.getIntArrayExtra("result");
                    textResult.setText("결과값 : " + result[0] +  " + " + result[1] + " = " + result[2]);
                    break;
            }
        }
    }
}
