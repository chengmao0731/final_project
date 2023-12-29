package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.FrameLayout;
        import android.widget.TextView;
        import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Start extends AppCompatActivity {

    // 先宣告 View 的變數
    Button submitButton;
    Button restartButton;
    EditText inputNumber;
    TextView historyInput;
    TextView historyResult;
    // Toast 是畫面下面會跳出來的小提示框
    Toast toast;
    FrameLayout cover;
    int counter;

    class Game {
        //    data
        private String answer;
        private boolean win = false;
        //    getter
        String getAnswer() {
            return answer;
        }
        boolean isWin() {
            return win;
        }
        //    function
        public void generateAnswer() {
            ArrayList<Character> list = new ArrayList<>();
            for (char digit = '0'; digit <= '9'; digit++) {
                list.add(digit);
            }

            StringBuilder result = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < 4; i++) {
                int index = random.nextInt(list.size());
                char selectedDigit = list.remove(index);
                result.append(selectedDigit);
            }

            answer = result.toString();
        }
        String checkAnswer(String guess){
            int a = 0, b = 0;
            boolean[] answerUsed = {false,false,false,false};
            boolean[] guessUsed = {false,false,false,false};
            for(int i = 0; i < 4; i++)
            {
                if(guess.charAt(i)==answer.charAt(i)) {
                    answerUsed[i]=true;
                    guessUsed[i]=true;
                    a++;
                }
                System.out.println();
            }
            for(int i = 0; i < 4; i++)
            {
                for(int j = 0; j < 4; j++) {
                    if (!guessUsed[i] && !answerUsed[j] && guess.charAt(i) == answer.charAt(j)) {
                        answerUsed[j] = true;
                        guessUsed[i] = true;
                        b++;
                        break;
                    }
                }
            }
            String result = a + "A" + b + "B";
            if(a==4)
            {
                win = true;
            }
            return result;
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // 找到畫面中的 View
        submitButton = (Button) findViewById(R.id.submitButton);
        inputNumber = (EditText) findViewById(R.id.inputNumber);
        historyInput = (TextView) findViewById(R.id.history_input);
        historyResult = (TextView) findViewById(R.id.history_result);
        cover = (FrameLayout) findViewById(R.id.cover);
        counter = 0;

        // 宣告一個 Game 實體
        final Game game = new Game();
        // 產生一個隨機的答案
        game.generateAnswer();

        final Context that = this;
        // 設定送出按鈕的點擊事件
        submitButton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v){
                // 一定要輸入四個數字才有反應
                if(inputNumber.getText().length() == 4) {
                    // 將使用者輸入的數字跟幾 A 幾 B 放入文字框框中
                    historyInput.setText((inputNumber.getText() + "\n") + historyInput.getText());
                    historyResult.setText((game.checkAnswer(inputNumber.getText().toString()) + "\n") + historyResult.getText());
                    // 清空輸入框
                    inputNumber.setText("");
                    // 如果猜中了
                    if (game.isWin()) {
                        // 跳出獲勝的訊息
                        Toast.makeText(that, "You win", Toast.LENGTH_LONG).show();
                                // 當按鈕被點擊時，視為訓練完成，呼叫 onTrainingCompleted()
                                Intent intent = new Intent(Start.this, EndActivity.class);
                                startActivity(intent);

                                // 結束當前 Activity
                                finish();
                        inputNumber.setEnabled(false);
                        submitButton.setEnabled(false);
                        cover.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        // 設定重新開始按鈕的點擊事件
    }
}