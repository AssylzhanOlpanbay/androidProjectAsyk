package com.kz.finalandroidproject.ui.home;

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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.kz.finalandroidproject.R;
import com.kz.finalandroidproject.DataBase.StoreDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    View view;

    CircleImageView user_photo, users_photo;

    TextView user_name, users_phoneNumber, tv_userEmail, tv_userBirthDay, tv_userHobby, tv_userLinkInstagram, tv_userLinkTelegram, tv_userLinkTwitter;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    Button btn_call, btn_sms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        Intent intent = getActivity().getIntent();
        String uEmail = intent.getStringExtra("email");
        String uPassword = intent.getStringExtra("password");


        user_photo = view.findViewById(R.id.user_photo);
        users_photo = view.findViewById(R.id.users_photo);
        user_name = view.findViewById(R.id.user_name);
        users_phoneNumber = view.findViewById(R.id.users_phoneNumber);
        tv_userEmail = view.findViewById(R.id.tv_userEmail);
        tv_userBirthDay = view.findViewById(R.id.tv_userBirthDay);
        tv_userHobby = view.findViewById(R.id.tv_userHobby);
        tv_userLinkInstagram = view.findViewById(R.id.tv_userLinkInstagram);
        tv_userLinkTelegram = view.findViewById(R.id.tv_userLinkTelegram);
        tv_userLinkTwitter = view.findViewById(R.id.tv_userLinkTwitter);

        btn_call = view.findViewById(R.id.btn_call);
        btn_sms = view.findViewById(R.id.btn_sms);



        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + users_phoneNumber.getText()));
                startActivity(intent);
            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto: " + users_phoneNumber.getText());
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, uri);
                intent1.putExtra("sms_body", "Hello, ");
                startActivity(intent1);
            }
        });

        getDataFromDatabase(uEmail, uPassword);

        Glide.with(this)
                .load("https://cdn-icons-png.flaticon.com/512/3135/3135715.png")
                .centerCrop()
                .placeholder(R.drawable.account)
                .into(user_photo);

        Glide.with(this)
                .load("https://cdn-icons-png.flaticon.com/512/3135/3135715.png")
                .centerCrop()
                .placeholder(R.drawable.account)
                .into(users_photo);
        return view;
    }

    public void getDataFromDatabase(String uEmail, String uPass){

        storeDatabase = new StoreDatabase(getActivity());
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        Cursor loginCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{uEmail, uPass});

        if (loginCursor != null && loginCursor.getCount() > 0) {
            loginCursor.moveToFirst();
            @SuppressLint("Range") String userName = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") String userPhone = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_PHONE));
            @SuppressLint("Range") String userEmail = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_EMAIL));
            @SuppressLint("Range") String userDate = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_BIRTHDAY));
            @SuppressLint("Range") String userHobby = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_HOBBY));
            @SuppressLint("Range") String userInstagram = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_INSTAGRAM));
            @SuppressLint("Range") String userTelegram = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_TELEGRAM));
            @SuppressLint("Range") String userTwitter = loginCursor.getString(loginCursor.getColumnIndex(COLUMN_TWITTER));

            user_name.setText(userName);
            users_phoneNumber.setText(userPhone);
            tv_userEmail.setText(userEmail);
            tv_userBirthDay.setText(userDate);
            tv_userHobby.setText(userHobby);
            tv_userLinkInstagram.setText(userInstagram);
            tv_userLinkTelegram.setText(userTelegram);
            tv_userLinkTwitter.setText(userTwitter);


            Toast.makeText(getActivity(), "Welcome, " + userName, Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.email_or_password_is_wrong), Toast.LENGTH_SHORT).show();
        }
    }
}