package com.example.jcj.learningskid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText Email, Password, Name;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabase;
    String SQLiteDataBaseQueryHolder;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = findViewById(R.id.buttonRegister);
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        Name = findViewById(R.id.editName);

        sqLiteHelper = new SQLiteHelper(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating SQLite database if doesn't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if doesn't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Name is already exists or not.
                CheckingNameAlreadyExistsOrNot();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();
            }
        });

    }

    // SQLite database build method.
    public void SQLiteDataBaseBuild() {

        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.dbName, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.tableName + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR);");

    }

    public void InsertDataIntoSQLiteDatabase() {

        if (EditTextEmptyHolder == true) {

            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.tableName + " (name,email,password) VALUES('" + NameHolder + "', '" + EmailHolder + "', '" + PasswordHolder + "');";
            sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabase.close();

            Toast.makeText(RegisterActivity.this, "User Registered Successfully. Back to login screen to login", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(RegisterActivity.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }
    }

    public void EmptyEditTextAfterDataInsert() {
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }

    public void CheckEditTextStatus() {

        NameHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    public void CheckingNameAlreadyExistsOrNot() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        // Adding search name query to cursor.
        cursor = sqLiteDatabase.query(SQLiteHelper.tableName, null, " " + SQLiteHelper.Table_Column_1_Name + "=?", new String[]{NameHolder}, null, null, null);

        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                F_Result = "Name Found";
                cursor.close();
            }
        }

        CheckFinalResult();

    }

    public void CheckFinalResult() {

        // Checking whether name is already exists or not.
        if (F_Result.equalsIgnoreCase("Name Found")) {

            Toast.makeText(RegisterActivity.this, "Username Already Exists", Toast.LENGTH_LONG).show();

        } else {

            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found";

    }
}
