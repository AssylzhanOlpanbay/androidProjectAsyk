package com.kz.finalandroidproject.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kz.finalandroidproject.R;
import com.kz.finalandroidproject.Test.SisterTestActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SisterFragment extends Fragment {

    View view;

    CircleImageView user_photo, users_photo;

    TextView user_name, users_phoneNumber;

    Button btn_call, btn_sms, btn_sister_startTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sister, container, false);
        btn_call = view.findViewById(R.id.btn_call);
        btn_sms = view.findViewById(R.id.btn_sms);
        user_photo = view.findViewById(R.id.user_photo);
        users_photo = view.findViewById(R.id.users_photo);
        users_phoneNumber = view.findViewById(R.id.users_phoneNumber);
        btn_sister_startTest = view.findViewById(R.id.btn_sister_startTest);



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

        btn_sister_startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), SisterTestActivity.class);
                startActivity(intent2);
            }
        });
        return view;
    }
}