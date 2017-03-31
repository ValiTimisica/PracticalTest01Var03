package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    private TextView editText1 = null;
    private TextView editText2 = null;
    private Button okButton = null;
    private Button cancelButton = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_ok:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.button_cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        editText1 = (TextView) findViewById(R.id.second_edit1);
        editText2 = (TextView) findViewById(R.id.second_edit2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("key")) {
            int numberOfClicks = intent.getIntExtra("key", -1);
            editText1.setText(String.valueOf(numberOfClicks));
            editText2.setText(String.valueOf(numberOfClicks));
        }

        okButton = (Button) findViewById(R.id.button_ok);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button) findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}
