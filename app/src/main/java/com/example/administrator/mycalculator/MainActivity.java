package com.example.administrator.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView input;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        input = (TextView) findViewById(R.id.text_input);

        findViewById(R.id.btn_0).setOnClickListener(this);//把当前的类当成事件监听器
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_pia).setOnClickListener(this);
        findViewById(R.id.btn_sin).setOnClickListener(this);
        findViewById(R.id.btn_cos).setOnClickListener(this);
        findViewById(R.id.btn_tan).setOnClickListener(this);
        findViewById(R.id.btn_cls).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_lna).setOnClickListener(this);
        findViewById(R.id.btn_log).setOnClickListener(this);
        findViewById(R.id.btn_fac).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_pow).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_sqr).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_lef).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_poi).setOnClickListener(this);
        findViewById(R.id.btn_rig).setOnClickListener(this);
        findViewById(R.id.btn_equ).setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                input.append("0");
                break;
            case R.id.btn_1:
                input.append("1");
                break;
            case R.id.btn_2:
                input.append("2");
                break;
            case R.id.btn_3:
                input.append("3");
                break;
            case R.id.btn_4:
                input.append("4");
                break;
            case R.id.btn_5:
                input.append("5");
                break;
            case R.id.btn_6:
                input.append("6");
                break;
            case R.id.btn_7:
                input.append("7");
                break;
            case R.id.btn_8:
                input.append("8");
                break;
            case R.id.btn_9:
                input.append("9");
                break;
            case R.id.btn_pia:
                input.append(",");
                break;
            case R.id.btn_sin:
                input.append("sin");
                break;
            case R.id.btn_cos:
                input.append("cos");
                break;
            case R.id.btn_tan:
                input.append("tan");
                break;
            case R.id.btn_cls:
                input.setText("");
                break;
            case R.id.btn_del:
                String str0 = input.getText().toString();
                String str1 = str0.substring(0,str0.length()-1);
                input.setText(str1);
                break;
            case R.id.btn_lna:
                input.append("ln");
                break;
            case R.id.btn_log:
                input.append("log");
                break;
            case R.id.btn_fac:
                input.append("!");
                break;
            case R.id.btn_add:
                input.append("+");
                break;
            case R.id.btn_pow:
                input.append("^");
                break;
            case R.id.btn_sub:
                input.append("-");
                break;
            case R.id.btn_sqr:
                input.append("sqr");
                break;
            case R.id.btn_mul:
                input.append("*");
                break;
            case R.id.btn_lef:
                input.append("(");
                break;
            case R.id.btn_div:
                input.append("/");
                break;
            case R.id.btn_poi:
                input.append(".");
                break;
            case R.id.btn_rig:
                input.append(")");
                break;
            case R.id.btn_equ:
                onEquClick ();
        }
    }
    public void onEquClick () {
        String sc = input.getText().toString();

        if(sc.matches("^sin.[0-9]{0,20}")|| sc.matches("^cos.[0-9]{0,20}") ||sc.matches("^tan.[0-9]{0,20}")
                || sc.matches("^[0-9]{0,20}!$") || sc.matches("[0-9]{0,20}\\^[0-9]{0,20}")
                ||sc.matches("^sqr.[0-9]{0,20}") ||sc.matches("^ln.[0-9]{0,20}") ||
                sc.matches("^log\\(.[0-9]{0,20},.[0-9]{0,20}\\)")) {
            SpecialActivity spec = new SpecialActivity();
            String res = spec.calculateOthers(sc);
            input.setText(res);
        }
        else {
            MainActivity1 ca = new MainActivity1();
            String behind = ca.toBehind(sc + "#");
            input.setText(new Double((double) ca.caculate(behind)).toString());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
