package com.example.user.work2;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.os.SystemClock.elapsedRealtime;

public class MainActivity2 extends AppCompatActivity {
    Switch switch1;
    EditText editTextAdult,editTextTeen,editTextChild;
    Chronometer chronometer;
    DatePicker datePicker;
    TimePicker timePicker;
    Button buttonNext,buttonPrev;
    TextView textTime,textDate,textAdultNum,textTeenNum,textChildNum;
    GridLayout layout_count;
    LinearLayout layout_result;
    LinearLayout layout_button;
    TextView textViewTime;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work2_2);
        init();
    }

    private void init(){
        setTitle("레스토랑 예약 시스템");
        switch1 = (Switch)findViewById(R.id.switch1);

        editTextAdult = (EditText)findViewById(R.id.editTextAdult);
        editTextTeen  = (EditText)findViewById(R.id.editTextTeen);
        editTextChild = (EditText)findViewById(R.id.editTextChild);

        chronometer = (Chronometer)findViewById(R.id.chronometer);

        datePicker = (DatePicker)findViewById(R.id.datePicker);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        scrollView = (ScrollView)findViewById(R.id.scrollView);

        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonPrev = (Button)findViewById(R.id.buttonPrev);
        ButtonListener buttonListener = new ButtonListener();
        buttonNext.setOnClickListener(buttonListener);
        buttonPrev.setOnClickListener(buttonListener);
        switch1.setOnClickListener(buttonListener);

        textTime = (TextView)findViewById(R.id.text_time);
        textDate = (TextView)findViewById(R.id.text_date);
        textAdultNum = (TextView)findViewById(R.id.text_adultNum);
        textTeenNum  = (TextView)findViewById(R.id.text_teenNum);
        textChildNum = (TextView)findViewById(R.id.text_childNum);

        textViewTime = (TextView)findViewById(R.id.textViewTime);
        layout_count  = (GridLayout)findViewById(R.id.layout_count);
        layout_result = (LinearLayout)findViewById(R.id.layout_result);
        layout_button = (LinearLayout)findViewById(R.id.layout_button);
    }

    private class ButtonListener implements View.OnClickListener{
        int state = 0;
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.buttonNext)
                if(this.state == 3
                        &&(editTextAdult.getText().toString().isEmpty()
                        || editTextTeen.getText().toString().isEmpty()
                        || editTextChild.getText().toString().isEmpty()))
                    Toast.makeText(getApplicationContext(), "숫자 입력 항목은 필수입니다.", Toast.LENGTH_SHORT).show();
                else
                    changeState(++this.state);
            else if(v.getId() == R.id.buttonPrev)
                changeState(--this.state);
            else if(v.getId() == R.id.switch1){
                state = 0;
                if(switch1.isChecked())
                    changeState(++state);
                else
                    changeState(state);
            }

        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        private void changeState(int state){
            layout_count.setVisibility(View.INVISIBLE);
            layout_result.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            layout_button.setVisibility(View.VISIBLE);

            switch(state){
                case 1:
                    textViewTime.setVisibility(View.VISIBLE);
                    chronometer.setVisibility(View.VISIBLE);
                    chronometer.start();
                    scrollView.setVisibility(View.VISIBLE);
                    buttonPrev.setEnabled(false);
                    buttonNext.setEnabled(true);
                    break;
                case 2:
                    timePicker.setVisibility(View.VISIBLE);
                    buttonPrev.setEnabled(true);
                    buttonNext.setEnabled(true);
                    break;
                case 3:
                    layout_count.setVisibility(View.VISIBLE);
                    buttonPrev.setEnabled(true);
                    buttonNext.setEnabled(true);
                    break;
                case 4:
                    yieldResult();
                    layout_result.setVisibility(View.VISIBLE);
                    buttonPrev.setEnabled(true);
                    buttonNext.setEnabled(false);
                    break;

                default:
                    textViewTime.setVisibility(View.INVISIBLE);
                    chronometer.setVisibility(View.INVISIBLE);
                    layout_button.setVisibility(View.INVISIBLE);
                    chronometer.setBase(elapsedRealtime());
                    chronometer.stop();
                    break;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        private void yieldResult(){
            textDate.setText(datePicker.getYear() + "년 " + datePicker.getMonth() + "월 " + datePicker.getDayOfMonth() +"일");
            textTime.setText(timePicker.getHour() + "시 " + timePicker.getMinute()+ "분");
            textAdultNum.setText(editTextAdult.getText() + "명");
            textTeenNum.setText(editTextTeen.getText() + "명");
            textChildNum.setText(editTextChild.getText() + "명");
        }
    }







}
