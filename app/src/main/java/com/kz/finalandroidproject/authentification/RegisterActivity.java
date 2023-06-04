package com.kz.finalandroidproject.authentification;

import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_BIRTHDAY;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_EMAIL;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_HOBBY;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_INSTAGRAM;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_NAME;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_PASSWORD;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_PHONE;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_TELEGRAM;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_TWITTER;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.kz.finalandroidproject.R;
import com.kz.finalandroidproject.DataBase.StoreDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText et_register_name, et_register_phone, et_register_email, et_register_password, et_register_hobby, et_register_birthDay, et_register_instagram, et_register_telegram, et_register_twitter;
    Button btn_register_register, btn_register_login;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    RadioButton grandfather_radioBtn, grandmother_radioBtn;
    RadioButton father_radioBtn, mother_radioBtn;
    RadioButton brother_radioBtn, sister_radioBtn;
    RadioButton littleSister_radioBtn, littleBrother_radioBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_register_name = findViewById(R.id.et_register_name);
        et_register_phone = findViewById(R.id.et_register_phone);
        et_register_email = findViewById(R.id.et_register_email);
        et_register_password = findViewById(R.id.et_register_password);
        et_register_hobby = findViewById(R.id.et_register_hobby);
        et_register_birthDay = findViewById(R.id.et_register_birthDay);
        et_register_instagram = findViewById(R.id.et_register_instagram);
        et_register_telegram = findViewById(R.id.et_register_telegram);
        et_register_twitter = findViewById(R.id.et_register_twitter);

        /*grandfather_radioBtn = findViewById(R.id.grandfather_radioBtn);
        grandmother_radioBtn = findViewById(R.id.grandmother_radioBtn);

        father_radioBtn = findViewById(R.id.father_radioBtn);
        mother_radioBtn = findViewById(R.id.mother_radioBtn);

        brother_radioBtn = findViewById(R.id.brother_radioBtn);
        sister_radioBtn = findViewById(R.id.sister_radioBtn);

        littleSister_radioBtn = findViewById(R.id.littleSister_radioBtn);
        littleBrother_radioBtn = findViewById(R.id.littleBrother_radioBtn);*/

        btn_register_register = findViewById(R.id.btn_register_register);
        btn_register_login = findViewById(R.id.btn_register_login);
        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        btn_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_register_name.getText())) {
                    et_register_name.setError("Write your name");
                    return;
                }
                if (TextUtils.isEmpty(et_register_phone.getText())) {
                    et_register_phone.setError("Write your phone");
                    return;
                }
                if (TextUtils.isEmpty(et_register_email.getText())) {
                    et_register_email.setError("Write a email");
                    return;
                }
                if (TextUtils.isEmpty(et_register_password.getText())) {
                    et_register_password.setError("Write your password");
                    return;
                }

                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME, et_register_name.getText().toString());
                values.put(COLUMN_PHONE, et_register_phone.getText().toString());
                values.put(COLUMN_EMAIL, et_register_email.getText().toString());
                values.put(COLUMN_HOBBY, et_register_hobby.getText().toString());
                values.put(COLUMN_BIRTHDAY, et_register_birthDay.getText().toString());
                values.put(COLUMN_INSTAGRAM, et_register_instagram.getText().toString());
                values.put(COLUMN_TELEGRAM, et_register_telegram.getText().toString());
                values.put(COLUMN_TWITTER, et_register_twitter.getText().toString());
                values.put(COLUMN_PASSWORD, et_register_password.getText().toString());

                sqLiteDatabase.insert(TABLE_NAME, null, values);

                Intent newregistrationIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(newregistrationIntent);
            }
        });

        btn_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newregistrationIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(newregistrationIntent);
            }
        });
    }
}