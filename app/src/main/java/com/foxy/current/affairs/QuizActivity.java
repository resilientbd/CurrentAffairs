package com.foxy.current.affairs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.foxy.current.affairs.model.Question;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button next;
    ArrayList<Question> questions = new ArrayList<>();
    int counter = 0;
    AppCompatRadioButton optionA, optionB, optionC, optionD;
    RadioGroup radioGroup;
    TextView question;
    TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        questions.add(new Question("This is a question", "A", "B", "C", "D", "A"));
        questions.add(new Question("This is a question2", "A", "B", "C", "D", "C"));
        questions.add(new Question("This is a question3", "A", "B", "C", "D", "D"));
        questions.add(new Question("This is a question4", "A", "B", "C", "D", "A"));

        next = findViewById(R.id.next);
        radioGroup = findViewById(R.id.optionsRadioGroup);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        question = findViewById(R.id.questionTextView);
        answerTextView = findViewById(R.id.answerTextView);
        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(counter - 1);
            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(counter - 1);
            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(counter - 1);
            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(counter - 1);
            }
        });


        getSupportActionBar().setTitle("Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupQuestion();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setupQuestion();

            }
        });
    }

    public void checkAnswer(int index) {
        AppCompatRadioButton options[] = {optionA, optionB, optionC, optionD};

        if (radioGroup.getCheckedRadioButtonId() != -1) {
            AppCompatRadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            Question data = questions.get(index);
            if (checkedRadioButton.getText().equals(data.getAnswer())) {
                answerTextView.setVisibility(View.VISIBLE);
                answerTextView.setBackgroundResource(R.color.colorGreen);
                answerTextView.setText("You are Right");
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(answerTextView);
            } else {

                answerTextView.setVisibility(View.VISIBLE);
                answerTextView.setBackgroundResource(R.color.colorRed);
                answerTextView.setText("You are Wrong");
                YoYo.with(Techniques.SlideInLeft)
                        .duration(500)
                        .playOn(answerTextView);
                for (int i = 0; i < options.length; i++) {
                    String answer = data.getAnswer();
                    if (options[i].getText().equals(answer)) {
                        options[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                    }
                }
            }
        } else {
            Toast.makeText(this, "Select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    public void setupQuestion() {
        AppCompatRadioButton options[] = {optionA, optionB, optionC, optionD};
        for (AppCompatRadioButton option : options) {
            option.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        answerTextView.setVisibility(View.GONE);
        if (next.getText().toString().equals("FINISH")) {
            finish();
        }
        if (questions.size() > counter) {
            radioGroup.clearCheck();
            Question data = questions.get(counter);
            question.setText(data.getQuestion());
            optionA.setText(data.getOptionA());
            optionB.setText(data.getOptionB());
            optionC.setText(data.getOptionC());
            optionD.setText(data.getOptionD());
            counter++;
            if (questions.size() == counter) {
                next.setText("FINISH");
            }
            Log.d("counter", counter + "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        switch (item.getItemId()) {

            case R.id.home:
                startActivity(new Intent(QuizActivity.this, CategoryActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
