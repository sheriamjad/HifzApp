package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_update extends AppCompatActivity {
    EditText etStName, etSabak, etSabki, etManzil;
    TextView tvDate;
    Button updateBtn, deleteBtn;
    DBHandler db;
    String id1,date1,stName1, sabak1, sabki1, manzil1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        deleteBtn=findViewById(R.id.btnDelete);
        updateBtn=findViewById(R.id.btnUpdate);
        etStName=findViewById(R.id.etName);
        etSabak=findViewById(R.id.etSabak);
        etSabki=findViewById(R.id.etSabki);
        etManzil=findViewById(R.id.etManzil);
        tvDate=findViewById(R.id.tvDate);
        getAndSetIntentData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new DBHandler(activity_update.this);
                stName1 = etStName.getText().toString().trim();
                sabak1 = etSabak.getText().toString().trim();
                sabki1 = etSabki.getText().toString().trim();
                manzil1= etManzil.getText().toString().trim();
                date1= tvDate.getText().toString().trim();

                Student student =new Student(stName1,date1,sabak1,sabki1,manzil1);
                if(db.updateData(student)==false)
                {

                    Toast.makeText(activity_update.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity_update.this, student.stName, Toast.LENGTH_SHORT).show();

                }

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db = new DBHandler(activity_update.this);

                stName1 = etStName.getText().toString().trim();

                if( db.deleteData(stName1)==false)
                {
                    Toast.makeText(activity_update.this, "Not deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity_update.this,"deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("stName") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("sabak") && getIntent().hasExtra("sabki")
                && getIntent().hasExtra("manzil")){
            //Getting Data from Intent
            id1= getIntent().getStringExtra("id");
            stName1 = getIntent().getStringExtra("stName");
            date1 = getIntent().getStringExtra("date");
            sabak1 = getIntent().getStringExtra("sabak");
            sabki1=getIntent().getStringExtra("sabki");
            manzil1=getIntent().getStringExtra("manzil");
           // Toast.makeText(activity_update.this,  namazName1, Toast.LENGTH_SHORT).show();
            //Setting Intent Data
            etSabak.setText(sabak1);
            etStName.setText(stName1);
            etSabak.setText(sabak1);
            etManzil.setText(manzil1);
            tvDate.setText(date1);
            //Log.d("stev", title+" "+author+" "+pages);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

    }
}