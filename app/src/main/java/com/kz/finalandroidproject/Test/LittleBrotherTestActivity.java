package com.kz.finalandroidproject.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kz.finalandroidproject.R;

public class LittleBrotherTestActivity extends AppCompatActivity {

    TextView tv_question1, tv_question2, tv_question3, tv_question4, tv_question5, tv_question6, tv_question7, tv_question8, tv_question9, tv_question10, procent;
    RadioButton rb_question1_ans1, rb_question1_ans2;
    RadioButton rb_question2_ans1, rb_question2_ans2;
    RadioButton rb_question3_ans1, rb_question3_ans2;
    RadioButton rb_question4_ans1, rb_question4_ans2;
    RadioButton rb_question5_ans1, rb_question5_ans2;
    RadioButton rb_question6_ans1, rb_question6_ans2;
    RadioButton rb_question7_ans1, rb_question7_ans2;
    RadioButton rb_question8_ans1, rb_question8_ans2;
    RadioButton rb_question9_ans1, rb_question9_ans2;
    RadioButton rb_question10_ans1, rb_question10_ans2;
    Button btn_check;

    String [] questions = {"What year was your little brother born?", "Little brother's name?", "Little brother's email?", "Little brother's birthday?", "Little brother's hobby?",
            "Little brother's instagram?", "Little brother's telegram?", "Little brother's twitter", "How old is little brother?", "What month was little brother born?"};

    double questionCount = questions.length;
    double counter = 0;
    String [][] variants = {
            {"2008" , "2007"},
            {"Michael Cruz" , "Will Smith"},
            {"michael08@gmail.com" , "michael2008@gmail.com"},
            {"31.05.2008" , "29.06.2007"},
            {"Football" , "watch a TV program"},
            {"michael.cruz" , "willsmith"},
            {"will.smith" , "@michaelcruz"},
            {"Michael Cruz" , "Will Smith"},
            {"15" , "16"},
            {"05" , "06"}
    };

    String [] answers = {"2008", "Michael Cruz", "michael08@gmail.com", "31.05.2008", "Football", "michael.cruz", "@michaelcruz", "Michael Cruz", "15", "05"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_brother_test);

        tv_question1 = findViewById(R.id.tv_question1);
        tv_question2 = findViewById(R.id.tv_question2);
        tv_question3 = findViewById(R.id.tv_question3);
        tv_question4 = findViewById(R.id.tv_question4);
        tv_question5 = findViewById(R.id.tv_question5);
        tv_question6 = findViewById(R.id.tv_question6);
        tv_question7 = findViewById(R.id.tv_question7);
        tv_question8 = findViewById(R.id.tv_question8);
        tv_question9 = findViewById(R.id.tv_question9);
        tv_question10 = findViewById(R.id.tv_question10);
        procent = findViewById(R.id.procent);

        rb_question1_ans1 = findViewById(R.id.rb_question1_ans1);
        rb_question1_ans2 = findViewById(R.id.rb_question1_ans2);

        rb_question2_ans1 = findViewById(R.id.rb_question2_ans1);
        rb_question2_ans2 = findViewById(R.id.rb_question2_ans2);

        rb_question3_ans1 = findViewById(R.id.rb_question3_ans1);
        rb_question3_ans2 = findViewById(R.id.rb_question3_ans2);

        rb_question4_ans1 = findViewById(R.id.rb_question4_ans1);
        rb_question4_ans2 = findViewById(R.id.rb_question4_ans2);

        rb_question5_ans1 = findViewById(R.id.rb_question5_ans1);
        rb_question5_ans2 = findViewById(R.id.rb_question5_ans2);

        rb_question6_ans1 = findViewById(R.id.rb_question6_ans1);
        rb_question6_ans2 = findViewById(R.id.rb_question6_ans2);

        rb_question7_ans1 = findViewById(R.id.rb_question7_ans1);
        rb_question7_ans2 = findViewById(R.id.rb_question7_ans2);

        rb_question8_ans1 = findViewById(R.id.rb_question8_ans1);
        rb_question8_ans2 = findViewById(R.id.rb_question8_ans2);

        rb_question9_ans1 = findViewById(R.id.rb_question9_ans1);
        rb_question9_ans2 = findViewById(R.id.rb_question9_ans2);

        rb_question10_ans1 = findViewById(R.id.rb_question10_ans1);
        rb_question10_ans2 = findViewById(R.id.rb_question10_ans2);

        btn_check = findViewById(R.id.btn_check);

        questionsJinau();

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checking(tv_question1, rb_question1_ans1, rb_question1_ans2, 0);
                checking(tv_question2, rb_question2_ans1, rb_question2_ans2, 1);
                checking(tv_question3, rb_question3_ans1, rb_question3_ans2, 2);
                checking(tv_question4, rb_question4_ans1, rb_question4_ans2, 3);
                checking(tv_question5, rb_question5_ans1, rb_question5_ans2, 4);
                checking(tv_question6, rb_question6_ans1, rb_question6_ans2, 5);
                checking(tv_question7, rb_question7_ans1, rb_question7_ans2, 6);
                checking(tv_question8, rb_question8_ans1, rb_question8_ans2, 7);
                checking(tv_question9, rb_question9_ans1, rb_question9_ans2, 8);
                checking(tv_question10, rb_question10_ans1, rb_question10_ans2, 9);
                procent.setText("" + (counter * 100) / questionCount + '%');
            }
        });


    }

    public void checking(TextView tv_question, RadioButton rb1, RadioButton rd2, int index) {
        if (rb1.isChecked() &&
                rb1.getText().toString().equals(answers[index])) {
            tv_question.setText(questions[index] + "\nCorrect answer");
            tv_question.setTextColor(getResources().getColor(R.color.green));
            counter++;
        } else if (rd2.isChecked() &&
                rd2.getText().toString().equals(answers[index])) {
            tv_question.setText(questions[index] + "\nCorrect answer");
            tv_question.setTextColor(getResources().getColor(R.color.green));
            counter++;
        } else {
            tv_question.setText(questions[index] + "\nIncorrect answer");
            tv_question.setTextColor(getResources().getColor(R.color.red));
        }
    }



    public void questionsJinau() {
        tv_question1.setText(questions[0]);
        rb_question1_ans1.setText(variants[0][0]);
        rb_question1_ans2.setText(variants[0][1]);

        tv_question2.setText(questions[1]);
        rb_question2_ans1.setText(variants[1][0]);
        rb_question2_ans2.setText(variants[1][1]);

        tv_question3.setText(questions[2]);
        rb_question3_ans1.setText(variants[2][0]);
        rb_question3_ans2.setText(variants[2][1]);

        tv_question4.setText(questions[3]);
        rb_question4_ans1.setText(variants[3][0]);
        rb_question4_ans2.setText(variants[3][1]);

        tv_question5.setText(questions[4]);
        rb_question5_ans1.setText(variants[4][0]);
        rb_question5_ans2.setText(variants[4][1]);

        tv_question6.setText(questions[5]);
        rb_question6_ans1.setText(variants[5][0]);
        rb_question6_ans2.setText(variants[5][1]);

        tv_question7.setText(questions[6]);
        rb_question7_ans1.setText(variants[6][0]);
        rb_question7_ans2.setText(variants[6][1]);

        tv_question8.setText(questions[7]);
        rb_question8_ans1.setText(variants[7][0]);
        rb_question8_ans2.setText(variants[7][1]);

        tv_question9.setText(questions[8]);
        rb_question9_ans1.setText(variants[8][0]);
        rb_question9_ans2.setText(variants[8][1]);

        tv_question10.setText(questions[9]);
        rb_question10_ans1.setText(variants[9][0]);
        rb_question10_ans2.setText(variants[9][1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}