package edu.cnm.deepdive.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class QuizActivity extends AppCompatActivity {

  private Button mTrueButton;
  private Button mFalseButton;
  private Button mNextButton;
  private TextView mQuestionTextView;
  private TextView correctTally;
  private TextView incorrectTally;
  private int correct;
  private int incorrect;

private Question [] mQuestionBank = new Question[]{
    new Question(R.string.question_australia, true),
    new Question(R.string.question_oceans, true),
    new Question(R.string.question_mideast, false),
    new Question(R.string.question_africa, false),
    new Question(R.string.question_americas, true),
    new Question(R.string.question_asia, true),

};

private int mCurrentIndex = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);
    correctTally = findViewById(R.id.correct_tally);
    incorrectTally = findViewById(R.id.incorrect_tally);
    mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


    mTrueButton = (Button) findViewById(R.id.true_button);
    mTrueButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        checkAnswer(true);
      }

    });

    mFalseButton = (Button) findViewById(R.id.false_button);
    mFalseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        checkAnswer(false);
      }
    });

    mNextButton = (Button) findViewById(R.id.next_button);
    mNextButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();

      }
    });
    updateQuestion();
  }
  private void updateQuestion(){
    int question = mQuestionBank[mCurrentIndex].getTextResId();
    mQuestionTextView.setText(question);
    correctTally.setText(getString(R.string.correct_tally_format, correct));
    incorrectTally.setText(getString(R.string.incorrect_tally_format, incorrect));
  }

  private void checkAnswer(boolean userPressedTrue) {
    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

    int messageResId = 0;

    if (userPressedTrue == answerIsTrue) {
      messageResId = R.string.correct_toast;
      correct++;

    }else {
      messageResId = R.string.incorrect_toast;
      incorrect++;

    }
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        .show();


  }

}
