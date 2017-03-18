package com.example.user.work2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
    }

    private void init(){
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(v.getId() == R.id.button1) {
                    intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else if(v.getId() == R.id.button2) {
                    intent = new Intent(StartActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        };
        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
    }
}
