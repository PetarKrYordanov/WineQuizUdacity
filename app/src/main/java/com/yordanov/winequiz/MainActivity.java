

package com.yordanov.winequiz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity {

    Button submit;
    int correctAnswers = 0;
    String answerQuestOne = "36";
    String answerQuestTwo = "12";
    String answerQuestThree = "2";
    String answerQuestFour = "martinborough";
    String answerQuestFive = "3";
    String answerQuestSix = "23";
    final View.OnClickListener submitButtonOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            checkAllQuestions();
            Toasty.normal(MainActivity.this,
                    "Correct Answers: " + correctAnswers + "/6", R.drawable.ic_icon).show();

            resetCounterCorrectAnswers();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView cardView = findViewById(R.id.card_view);
//cardView.setBackground(R.drawable.background_crop);
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(submitButtonOnClickListener);
    }

    private void checkAllQuestions() {
        String userInputQuestionOne = getUserInputAnswer((EditText)
                findViewById(R.id.question_one_answer));
        checkOpenQuestion(answerQuestOne, userInputQuestionOne);

        String userInputQuestionFour = getUserInputAnswer((EditText) findViewById(R.id.answer_input_question_four));
        checkOpenQuestion(answerQuestFour, userInputQuestionFour);

        String userAnswerQuestionTwo = checkUserAnswer((CheckBox) findViewById(R.id.chk_one_quest_two),
                (CheckBox) findViewById(R.id.chk_two_quest_two),
                (CheckBox) findViewById(R.id.chk_three_quest_two));
        checkAnswer(answerQuestTwo, userAnswerQuestionTwo);

        String userAnswerQuestionSix = checkUserAnswer((CheckBox) findViewById(R.id.chk_one_quest_six),
                (CheckBox) findViewById(R.id.chk_two_quest_six),
                (CheckBox) findViewById(R.id.chk_three_quest_six));
        checkAnswer(answerQuestSix, userAnswerQuestionSix);

        checkMultipleChoiceQuestion(answerQuestThree, (RadioButton) findViewById(R.id.radio_one_quest_three),
                (RadioButton) findViewById(R.id.radio_two_quest_three),
                (RadioButton) findViewById(R.id.radio_three_quest_three));
        checkMultipleChoiceQuestion(answerQuestFive, (RadioButton) findViewById(R.id.radio_one_quest_five),
                (RadioButton) findViewById(R.id.radio_two_quest_five),
                (RadioButton) findViewById(R.id.radio_three_quest_five));
    }

    private void checkOpenQuestion(String correctAnswer, String answerUser) {
        if (answerUser.trim().equalsIgnoreCase(correctAnswer)) {
            correctAnswers += 1;
        }
    }

    private void resetCounterCorrectAnswers() {
        correctAnswers = 0;
    }

    private String getUserInputAnswer(EditText editText) {
        return editText.getText().toString();
    }

    private String checkUserAnswer(CheckBox checkBoxOne, CheckBox checkBoxTwo, CheckBox checkBoxThree) {
        String answerCode = "";
        if (checkBoxOne.isChecked()) {
            answerCode += "1";
        }
        if (checkBoxTwo.isChecked()) {
            answerCode += "2";
        }
        if (checkBoxThree.isChecked()) {
            answerCode += "3";
        }
        return answerCode;
    }

    private void checkAnswer(String answer, String userAnswer) {
        if (answer.equals(userAnswer)) {
            correctAnswers += 1;
        }
    }

    private void checkMultipleChoiceQuestion(String answer, RadioButton radioButtonOne,
                                             RadioButton radioButtonTwo, RadioButton radioButtonThree) {
        String answerCode;
        if (radioButtonOne.isChecked()) {
            answerCode = "1";
        } else if (radioButtonTwo.isChecked()) {
            answerCode = "2";
        } else if (radioButtonThree.isChecked()) {
            answerCode = "3";
        } else {
            answerCode = "0";
        }
        if (answerCode.equals(answer)) {
            correctAnswers += 1;
        }
    }
}
