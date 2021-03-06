package com.example.smartcloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    String substitute = "";
    String clothName = "";
    String moreInf = "";
    private Stop_DBHelper helperlogin;
    private Stop_DBBasic dbbasic;
    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        dbbasic = new Stop_DBBasic();
        helperlogin = helperlogin.getinstence(getApplicationContext());

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final EditText clothText = (EditText)findViewById(R.id.clothText);
        final EditText infText = (EditText)findViewById(R.id.infText);
        Button cancleButton = (Button)findViewById(R.id.cancleButton);
        Button inputButton = (Button)findViewById(R.id.inputButton);

        final Intent inputIntent = new Intent(this, Main4Activity.class);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                substitute = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        clothName = clothText.getText().toString();
        moreInf = infText.getText().toString();

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothName = clothText.getText().toString();
                moreInf = infText.getText().toString();
                if(substitute.equals("") || clothText.equals("") || moreInf.equals("")){
                    Toast.makeText(AddActivity.this, "정보가 제대로 입력되지 않았습니다.", Toast.LENGTH_LONG).show(); //주석
                }else {
                    dbbasic.insert(new Stop_DBLogin(helperlogin.getWritableDatabase()),
                            "insert into Clothe values(null,'" + clothName + "','" + substitute + "','" + moreInf + "');");
                    startActivity(inputIntent);
                    finish();
                }
            }
        });
    }
}
