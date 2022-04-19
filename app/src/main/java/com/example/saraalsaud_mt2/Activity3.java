package com.example.saraalsaud_mt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {
    DatabaseHelper MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Button delete=(Button)findViewById(R.id.bttnDelete);
        Button view=(Button)findViewById(R.id.bttnview2);
        EditText id_val=(EditText)findViewById(R.id.editTextId);
        Button bttnfirstAct2=(Button)findViewById(R.id.bttnact2);
        MyDB = new DatabaseHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int temp = id_val.getText().toString().length();
                if (temp>0){
                    Integer deleteRow = MyDB.delete(id_val.getText().toString());
                    if (deleteRow>0) {
                        Toast.makeText(getBaseContext(), "data deleted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getBaseContext(),"enter id to delete",Toast.LENGTH_SHORT).show();
                    }
                }
                Log.d("Sara", "after deleted value");

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = MyDB.View();
                StringBuffer buffer = new StringBuffer();

                while(cur.moveToNext()) {
                    buffer.append("id: " + cur.getString(0)+ "\n");
                    buffer.append("Name: " + cur.getString(1)+ "\n");
                    buffer.append("email: " + cur.getString(2)+ "\n");
                    buffer.append("phone: " + cur.getString(3)+ "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity3.this);
                builder.setCancelable(true);  // a dialog box that can be closed
                builder.setTitle("All data");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(Activity3.this,"Successful view",Toast.LENGTH_LONG).show();
            }


        });

        bttnfirstAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity3.this,Activity2.class));
            }
        });


    }
}