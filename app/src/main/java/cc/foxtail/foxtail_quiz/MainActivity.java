package cc.foxtail.foxtail_quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {
    private Quiz[] quizArray = new Quiz[]{
            new Quiz(R.string.question_iu,true),
            new Quiz(R.string.question_americas,true),
            new Quiz(R.string.question_asia,true),
            new Quiz(R.string.question_constraint,true),
            new Quiz(R.string.question_life_cycle,true),
    };

    private int currentIndex = 0;
    private boolean isCheat = false;

    private TextView questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = (TextView)findViewById(R.id.question_text_view);
        Button trueButton = (Button)findViewById(R.id.true_button);
        Button falseButton = (Button)findViewById(R.id.false_button);
        Button nextButton = (Button)findViewById(R.id.next_button);
        Button preButton = (Button)findViewById(R.id.pre_button);

        Button cheatButton = (Button) findViewById(R.id.cheat_button);


        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % quizArray.length;
                updateQuestion();
            }
        });

        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex - 1) % quizArray.length;

                if(currentIndex < 0){
                    currentIndex = quizArray.length + currentIndex;
                }
                updateQuestion();
            }
        });

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("INDEX", 0);
            isCheat = savedInstanceState.getBoolean("ISCHEAT", false);
        }

        updateQuestion();

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
                intent.putExtra("ANSWER", quizArray[currentIndex].isAnswer());
                startActivityForResult(intent, 999);

            }
        });
    }

    private void updateQuestion(){
        questionText.setText(quizArray[currentIndex].getResourceId());
    }

    private void checkAnswer(boolean answer){
        boolean answerIsTrue = quizArray[currentIndex].isAnswer();
        if(isCheat){
            Toast.makeText(MainActivity.this, "컨닝 ㅗ", Toast.LENGTH_SHORT).show();
        } else {
            int toastMessageId = answer == answerIsTrue ? R.string.correct_toast : R.string.incorrect_toast;
            Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
        }
        System.out.println(isCheat);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("INDEX",currentIndex);
        outState.putBoolean("ISCHEAT", isCheat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == 999) {
            if (data == null)
                return;
            isCheat = data.getBooleanExtra("SHOWN", false);
        }
    }

}
