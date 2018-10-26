package com.example.jcj.learningskid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminHomeContent extends AppCompatActivity {

    String NameHolder;
    TextView Name;
    Button LogOUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_content);

        Name = findViewById(R.id.textView2);
        LogOUT = findViewById(R.id.button2);

        Intent intent = getIntent();
        NameHolder = intent.getStringExtra(MainActivity.UserName);

        Name.setText(Name.getText().toString() + NameHolder);

        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(AdminHomeContent.this, "Log Out Successfuly", Toast.LENGTH_LONG).show();
            }
        });
    }
}
