package cc.foxtail.foxtail_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by andong-won on 2017. 10. 14..
 */
public class CheatActivity extends AppCompatActivity {

    private boolean isAnswerTrue;
    private boolean isShown;

    private TextView answerTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerTextView = findViewById(R.id.answer_text_view);

        Button showAnswerButton =  findViewById(R.id.show_answer_button);

        isAnswerTrue = getIntent().getBooleanExtra("ANSWER", false);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerText();

                isShown = true;
                setShownResult();
            }
        });


        if (savedInstanceState != null) {
            isShown = savedInstanceState.getBoolean("ISSHOWN", false);
        }
        if (isShown){
            setAnswerText();
        }
        setShownResult();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("ISSHOWN", isShown);
    }

    private void setShownResult(){
        Intent intent = new Intent();
        intent.putExtra("SHOWN", isShown);
        setResult(RESULT_OK, intent);
    }

    private void setAnswerText(){
        if(isAnswerTrue){
            answerTextView.setText(R.string.correct_toast);
        }else{
            answerTextView.setText(R.string.incorrect_toast);
        }
    }

}