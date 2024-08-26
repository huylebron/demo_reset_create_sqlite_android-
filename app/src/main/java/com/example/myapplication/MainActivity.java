package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private EditText editName , editYear , editSchool;
private RadioButton radioMale , radioFemale;
private CheckBox cbSport , cbTraver, cbReadBook;
private SQLiteDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initData();


        editName = findViewById(R.id.editName);
        editYear = findViewById(R.id.editYear);
        editSchool = findViewById(R.id.editSchool);
        radioMale = findViewById(R.id.radmale);
        radioFemale = findViewById(R.id.radfemale);
        cbSport = findViewById(R.id.cbSport);
        cbReadBook = findViewById(R.id.cbReadBook);
        cbTraver = findViewById(R.id.cbTravel);




        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetView();

            }
        });

       findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SaveStudent();
           }
       });

    }

    private void SaveStudent() {
        String name = editName.getText().toString();
        String year = editYear.getText().toString();
        String School = editSchool.getText().toString();
        int sex = 0 ;
        if(radioFemale.isChecked()) sex = 1 ;

        String favorite = "";
        if ( cbSport.isChecked() ) {
            favorite += "," + cbSport.getText().toString();

        }
        if ( cbTraver.isChecked() ) {
            favorite += "," + cbTraver.getText().toString();

        }
        if ( cbReadBook.isChecked() ) {
            favorite += "," + cbReadBook.getText().toString();

        }

        String sql = "INSERT INTO tbsv ( name , birth , school , sex , favorite) VALUES ('"+name+"'," +
                "'"+year+"'," +
                "'"+School+"'," +
                "'"+sex+"'," +
                "'"+favorite+"')";
        db.execSQL(sql);
    }

    private void initData() {
        db = openOrCreateDatabase("qlsv.db",MODE_PRIVATE,null);
       String sql = "CREATE TABLE IF NOT EXISTS tbsv ( id integer primary key autoincrement , " +
               "name text , " +
               "birth text," +
               "School text," +
               "sex integer," +
               "favorite text)";

       db.execSQL(sql);

    }

    private void resetView() {
        editName.setText("");
        editYear.setText("");
        editSchool.setText("");
        radioMale.setChecked(true);
        cbReadBook.setChecked(false);
        cbSport.setChecked(false);
        cbTraver  .setChecked(false);


    }
    private void deleteByName (String name){
        String sql = "DELETE FROM tbsv WHERE name = '"+ name+"'";
        db.execSQL(sql);
    }
}