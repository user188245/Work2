package com.example.user.work2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work2_2);
        init();
    }

    private void init(){
        switch1 = (Switch)findViewById(R.id.switch1);


        editTextAdult = (EditText)findViewById(R.id.editTextAdult);
        editTextTeen  = (EditText)findViewById(R.id.editTextTeen);
        editTextChild = (EditText)findViewById(R.id.editTextChild);

        chronometer = (Chronometer)findViewById(R.id.chronometer);

        datePicker = (DatePicker)findViewById(R.id.datePicker);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

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
        @Override
        public void onClick(View v) {
            //Toast.makeText(getApplicationContext(),"버튼 클릭 됨",Toast.LENGTH_SHORT).show();
            if(v.getId() == R.id.buttonNext)
                changeState(++this.state);
            else if(v.getId() == R.id.buttonPrev)
                changeState(--this.state);
            else if(v.getId() == R.id.switch1){
                if(switch1.isChecked()){
                    changeState(0);
                    Toast.makeText(getApplicationContext(),"Activated",Toast.LENGTH_SHORT).show();
                }
                else {
                    changeState(1);
                    Toast.makeText(getApplicationContext(),"Inactivated",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void changeState(int state){
        layout_count.setVisibility(View.INVISIBLE);
        layout_result.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        layout_button.setVisibility(View.VISIBLE);

        switch(state){
            case 1:
                textViewTime.setVisibility(View.VISIBLE);
                chronometer.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.VISIBLE);
                buttonPrev.setClickable(false);
                buttonNext.setClickable(true);
                break;
            case 2:
                timePicker.setVisibility(View.VISIBLE);
                buttonPrev.setClickable(true);
                buttonNext.setClickable(true);
                break;
            case 3:
                layout_count.setVisibility(View.VISIBLE);
                buttonPrev.setClickable(true);
                buttonNext.setClickable(true);
                break;
            case 4:
                layout_result.setVisibility(View.VISIBLE);
                buttonPrev.setClickable(true);
                buttonNext.setClickable(false);
                break;

            default:
                textViewTime.setVisibility(View.INVISIBLE);
                chronometer.setVisibility(View.INVISIBLE);
                layout_button.setVisibility(View.INVISIBLE);
                chronometer.setBase(0);
                chronometer.stop();
                break;
        }
    }


}
