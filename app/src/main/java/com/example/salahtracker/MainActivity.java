package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DBHandler db;
    Button add;
    Button gitbutton;
    Button showData;

    EditText sabak;
    EditText manzil;
    TextView person;
    String stName;
    EditText etstudentName;
    EditText select_date;
    String date;
    EditText sabki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = findViewById(R.id.txtPersonName);

        add = findViewById(R.id.btnAddData);
        showData = findViewById(R.id.btnDisplay);
        etstudentName = findViewById((R.id.txtStName));
        sabak =(EditText)findViewById(R.id.txtSabak);

        sabki = (EditText)findViewById(R.id.txtSabki);
        manzil = (EditText) findViewById(R.id.txtManzil);

        select_date=findViewById(R.id.dateDisplay);

        //adding calander
        final Calendar calandar=Calendar.getInstance();
        final int year = calandar.get(Calendar.YEAR);
        final int month = calandar.get(Calendar.MONTH);
        final int day = calandar.get(Calendar.DAY_OF_MONTH);
        gitbutton=findViewById(R.id.gitButton);
        gitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://github.com/sheriamjad/HifzApp.git";
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        select_date.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date = day+"/"+month+"/"+year;
                        select_date.setText(date);
                    }
                },year,month,day);
                dialog.show();

            }
        });

   //// add Button functionality
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Ssabak =sabak.getText().toString();
                String Ssabki =sabki.getText().toString();
                String SstName =etstudentName.getText().toString();
                String Mmanzil = manzil.getText().toString();
                //////NAMAZ OBJECTTTTTTTTT
                Student student =new Student(SstName,date,Ssabak,Ssabki,Mmanzil);
               // Namaz namaz1= new Namaz("fajar", "12/2/23", 2,"yes", 4);

                Person person=new Person("Teacher",1);

                //dbbbb
                db=new DBHandler(MainActivity.this);

                if(db.insertStudent(student)==false)
                {
                    Toast.makeText(MainActivity.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data is Inserted Successfully", Toast.LENGTH_SHORT).show();

                }

   }
        });


       showData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, DiaplayDataActivity.class);
               startActivity(intent);
           }

       });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        stName = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(this, stName, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}