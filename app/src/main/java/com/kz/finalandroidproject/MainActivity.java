package com.kz.finalandroidproject;

import androidx.appcompat.app.AppCompatActivity;
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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import com.kz.finalandroidproject.DataBase.StoreDatabase;
import com.kz.finalandroidproject.databinding.ActivityMainBinding;

import de.hdodenhof.circleimageview.CircleImageView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CircleImageView nav_userPhoto;

    TextView nav_userName;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cabinet, R.id.nav_ata, R.id.nav_apa, R.id.nav_ake, R.id.nav_ana, R.id.nav_aga, R.id.nav_apke, R.id.nav_karyndas, R.id.nav_ini)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("email");
        String userPassword = intent.getStringExtra("password");

        View headerLayout = navigationView.getHeaderView(0); // 0-index header
        nav_userName = headerLayout.findViewById(R.id.nav_userName);


        getDataFromDatabase(userEmail, userPassword);


    }

    public void getDataFromDatabase(String userEmail, String userPassword) {

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        Cursor loginCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{userEmail, userPassword});

        if (loginCursor != null && loginCursor.getCount() > 0) {
            loginCursor.moveToFirst();
            @SuppressLint("Range") String userName = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") String userPhone = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_PHONE));
            @SuppressLint("Range") String usersEmail = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_EMAIL));
            @SuppressLint("Range") String userDate = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_BIRTHDAY));
            @SuppressLint("Range") String userHobby = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_HOBBY));
            @SuppressLint("Range") String userInstagram = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_INSTAGRAM));
            @SuppressLint("Range") String userTelegram = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_TELEGRAM));
            @SuppressLint("Range") String userTwitter = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_TWITTER));

            nav_userName.setText(userName);


            Toast.makeText(MainActivity.this, getResources().getString(R.string.welcome) + userName, Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.email_or_password_is_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    public void setLocale(String localeCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}