package com.example.user.work2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    TextView t1,t2;
    Button b1,b2;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work2_1);
        init();
    }

    private void init(){
        setTitle("학점 계산기");
        e1 = (EditText)findViewById(R.id.editText1);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);
        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        i1 = (ImageView)findViewById(R.id.imageView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        ButtonListener buttonListener = new ButtonListener();
        b1.setOnClickListener(buttonListener);
        b2.setOnClickListener(buttonListener);
    }

    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            float average = 0;
            switch(v.getId()){

                case R.id.button1:
                    num1 = getInt(e1.getText().toString());
                    num2 = getInt(e2.getText().toString());
                    num3 = getInt(e3.getText().toString());
                    average = ((num1 + num2 + num3)/3);

                    i1.setImageResource(getImageResource(average));
                    i1.setVisibility(View.VISIBLE);
                    break;

                case R.id.button2:
                    i1.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"초기화 되었습니다.",Toast.LENGTH_SHORT).show();
                    break;
            }
            t1.setText((num1 + num2 + num3) + "점");
            t2.setText(String.format("%.1f",average) + "점");
        }
    }

    private int getImageResource(float score){
        if(score >= 90)
            return R.drawable.a;
        else if(score >= 80)
            return R.drawable.b;
        else if(score >= 70)
            return R.drawable.c;
        else if (score >= 60)
            return R.drawable.d;
        else
            return R.drawable.f;
    }

    private int getInt(String s){
        return s.isEmpty()?0:Integer.parseInt(s);
    }

}
