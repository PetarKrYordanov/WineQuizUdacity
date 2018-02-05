/*
PROJECT LICENSE
This project was submitted by Lara Martín as part of the Nanodegree At Udacity.
As part of Udacity Honor code, your submissions must be your own work, hence
submitting this project as yours will cause you to break the Udacity Honor Code
and the suspension of your account.
Me, the author of the project, allow you to check the code as a reference, but if
you submit it, it's your own responsibility if you get expelled.
Copyright (c) 2017 Lara Martín
Besides the above notice, the following license applies and this license notice
must be included in all works derived from this project.
MIT License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.yordanov.gamesquiz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
