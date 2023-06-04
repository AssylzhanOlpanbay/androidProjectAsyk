package com.kz.finalandroidproject.authentification;

import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_EMAIL;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.COLUMN_PASSWORD;
import static com.kz.finalandroidproject.DataBase.StoreDatabase.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.kz.finalandroidproject.R;
import com.kz.finalandroidproject.DataBase.StoreDatabase;
import com.kz.finalandroidproject.MainActivity;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    EditText et_email, et_password,et_register_phone;
    Button btn_login;

    TextView btn_register;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_register_phone = findViewById(R.id.et_register_phone);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registrationIntent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_email.getText())) {
                    et_email.setError(getResources().getString(R.string.write_your_email));
                    return;
                }
                if (TextUtils.isEmpty(et_password.getText())) {
                    et_password.setError(getResources().getString(R.string.write_password));
                    return;
                }

                String uEmail = et_email.getText().toString();
                String uPass = et_password.getText().toString();

                Cursor loginCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                        COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{uEmail, uPass});

                if (loginCursor != null && loginCursor.getCount() > 0) {
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    loginIntent.putExtra("email", uEmail);
                    loginIntent.putExtra("password", uPass);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.email_or_password_is_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config,dm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.eng_language) {
            setLocale("en");
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else if (id == R.id.rus_language) {
            setLocale("ru");
            Intent intent2 = getIntent();
            finish();
            startActivity(intent2);
        } else if (id == R.id.kaz_language) {
            setLocale("kk");
            Intent intent3 = getIntent();
            finish();
            startActivity(intent3);
        }
        return true;
    }
}