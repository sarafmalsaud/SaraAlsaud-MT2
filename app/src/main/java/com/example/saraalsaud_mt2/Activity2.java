package com.example.saraalsaud_mt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addbttn = (Button) findViewById(R.id.bttnInsert);
        EditText id = (EditText) findViewById(R.id.editTextID);
        EditText name = (EditText) findViewById(R.id.editTextName);
        EditText surname = (EditText) findViewById(R.id.EdittxtSurname);
        EditText nid = (EditText) findViewById(R.id.editTextNID);

        Button bttnSecondAct = (Button) findViewById(R.id.goto1act);
        Button bttnThirdAct = (Button) findViewById(R.id.goto3act);


        MyDB = new DatabaseHelper(this);

        addbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_val = id.getText().toString();
                String name_val = name.getText().toString();
                String sur_val = surname.getText().toString();
                String nid_val = nid.getText().toString();

                Boolean insertData = MyDB.addData(id_val, name_val, sur_val, nid_val);

                Log.d("Sara", "after added value");

                if (insertData == true) {
                    Toast.makeText(getBaseContext(), "added data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "add data fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bttnSecondAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, MainActivity.class));
            }
        });


        bttnThirdAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, Activity3.class));
            }
        });
    }
}