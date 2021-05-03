package com.khil.pembelajaranjurumiyah.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khil.pembelajaranjurumiyah.R;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal1;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal2;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal3;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal4;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal5;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal6;
import com.khil.pembelajaranjurumiyah.banksoal.DbBankSoal7;
import com.khil.pembelajaranjurumiyah.dialog.CorrectDialog;
import com.khil.pembelajaranjurumiyah.dialog.TimerDialog;
import com.khil.pembelajaranjurumiyah.dialog.WrongDialog;
import com.khil.pembelajaranjurumiyah.menu.PlayActivity;
import com.khil.pembelajaranjurumiyah.model.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;

    private ArrayList<Questions> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Questions currentQuestions;
    private boolean answerd;

    private final Handler handler = new Handler();

    private int correctAns = 0, wrongAns = 0;

    private TimerDialog timerDialog;
    private CorrectDialog correctDialog;
    private WrongDialog wrongDialog;

    private PlayAudioForAnswers playAudioForAnswers;

    int FLAG = 0;

    int score = 0;

    // variable jumlah soal
    int x = 10;

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeleftinMillis;

    private long backPressedTime;
    public int nomorMateri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupUI();
        getData();

        Log.i("BUGBUG", "onCreate() in QuizActivity");

        timerDialog = new TimerDialog(this);
        correctDialog = new CorrectDialog(this);
        wrongDialog = new WrongDialog(this);
        playAudioForAnswers = new PlayAudioForAnswers(this);
    }

    private void getData() {
        nomorMateri = getIntent().getIntExtra("nomorMateri", 0);
        if (nomorMateri == 1) {
            bankSoal1();
        } else if (nomorMateri == 2) {
            bankSoal2();
        }else if (nomorMateri == 3){
            bankSoal3();
        }else if (nomorMateri == 4){
            bankSoal4();
        }else if (nomorMateri == 5){
            bankSoal5();
        }else if (nomorMateri == 6){
            bankSoal6();
        }else if (nomorMateri == 7){
            bankSoal7();
        }
    }


    private void setupUI() {
        textViewQuestion = findViewById(R.id.txtQuestionContainer);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        textViewCountDown = findViewById(R.id.txtViewTimer);

        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button);
    }

    public void bankSoal1() {
        DbBankSoal1 dbBankSoal1 = new DbBankSoal1(this);
        questionList = dbBankSoal1.getAllQuestions();
        startQuiz();
    }


    public void bankSoal2() {
        DbBankSoal2 dbBankSoal2 = new DbBankSoal2(this);
        questionList = dbBankSoal2.getAllQuestions();
        startQuiz();
    }

    public void bankSoal3() {
        DbBankSoal3 dbBankSoal3 = new DbBankSoal3(this);
        questionList = dbBankSoal3.getAllQuestions();
        startQuiz();
    }

    public void bankSoal4() {
        DbBankSoal4 dbBankSoal4 = new DbBankSoal4(this);
        questionList = dbBankSoal4.getAllQuestions();
        startQuiz();
    }

    public void bankSoal5() {
        DbBankSoal5 dbBankSoal5 = new DbBankSoal5(this);
        questionList = dbBankSoal5.getAllQuestions();
        startQuiz();
    }

    public void bankSoal6(){
        DbBankSoal6 dbBankSoal6 = new DbBankSoal6(this);
        questionList = dbBankSoal6.getAllQuestions();
        startQuiz();
    }

    public void bankSoal7(){
        DbBankSoal7 dbBankSoal7 = new DbBankSoal7(this);
        questionList = dbBankSoal7.getAllQuestions();
        startQuiz();
    }

    public void startQuiz() {
        questionTotalCount = x;
        Collections.shuffle(questionList);

        showQuestions();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.radio_button1:

                        rb1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));

                        break;
                    case R.id.radio_button2:
                        rb2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        rb1.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));

                        break;

                    case R.id.radio_button3:
                        rb3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb1.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));

                        break;

                    case R.id.radio_button4:
                        rb4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb1.setTextColor(Color.BLACK);

                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));

                        break;
                }

            }
        });

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                        quizOperation();
                    } else {

                        Toast.makeText(QuizActivity.this, "Please Select the Answer ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    public void showQuestions() {

        rbGroup.clearCheck();

        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_default_background));

        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);

        if (questionCounter < x) {
            currentQuestions = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());

            questionCounter++;
            answerd = false;
            buttonConfirmNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter + "/" + questionTotalCount);

            timeleftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {

            Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonConfirmNext.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finalResult();

                }
            }, 2000);
        }
    }

    private void quizOperation() {
        answerd = true;

        countDownTimer.cancel();

        RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbselected) + 1;

        checkSolution(answerNr, rbselected);

    }


    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQuestions.getAnswerNr()) {
            case 1:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb1.setBackground(ContextCompat.getDrawable(
                            this, R.drawable.correct_option_background));
                    rb1.setTextColor(Color.BLACK);

                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);

                    wrongAns++;

                    String correctAnswer = (String) rb1.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                }
                break;
            case 2:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb2.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_background));
                    rb2.setTextColor(Color.BLACK);

                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;

                    String correctAnswer = (String) rb2.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                }
                break;
            case 3:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb3.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_background));
                    rb3.setTextColor(Color.BLACK);

                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;

                    String correctAnswer = (String) rb3.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                }
                break;
            case 4:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb4.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_background));
                    rb4.setTextColor(Color.BLACK);

                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score, this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;

                    String correctAnswer = (String) rb4.getText();
                    wrongDialog.wrongDialog(correctAnswer, this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                }
                break;
        }
        if (questionCounter == x) {
            buttonConfirmNext.setText("Confirm and Finish");
        }
    }

    void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_answer_background));

        rbselected.setTextColor(Color.BLACK);
    }


    //  the timer code

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeleftinMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinMillis = millisUntilFinished;

                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeleftinMillis = 0;
                updateCountDownText();

            }
        }.start();

    }


    private void updateCountDownText() {

        int minutes = (int) (timeleftinMillis / 1000) / 60;
        int seconds = (int) (timeleftinMillis / 1000) % 60;

        //  String timeFormatted = String.format(Locale.getDefault(),"02d:%02d",minutes,seconds);

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);

        if (timeleftinMillis < 10000) {

            textViewCountDown.setTextColor(ContextCompat.getColor(this, R.color.red));

            FLAG = 3;
            playAudioForAnswers.setAudioforAnswer(FLAG);


        } else {
            textViewCountDown.setTextColor(ContextCompat.getColor(this, R.color.white));
        }


        if (timeleftinMillis == 0) {


            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    timerDialog.timerDialog();

                }
            }, 2000);


        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("BUGBUG", "onRestart() in QuizActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG", "onStop() in QuizActivity");
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("BUGBUG", "onPause() in QuizActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BUGBUG", "onResume() in QuizActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("BUGBUG", "onStart() in QuizActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        Log.i("BUGBUG", "onDestroy() in QuizActivity");
    }


    private void finalResult() {

        Intent resultData = new Intent(QuizActivity.this, ResultActivity.class);
        resultData.putExtra("nomorMateri", nomorMateri);
        resultData.putExtra("UserScore", score);
        resultData.putExtra("TotalQuestion", questionTotalCount);
        resultData.putExtra("CorrectQues", correctAns);
        resultData.putExtra("WrongQues", wrongAns);
        startActivity(resultData);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            Intent intent = new Intent(QuizActivity.this, PlayActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }
}
