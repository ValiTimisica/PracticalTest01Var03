package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private EditText leftEditText = null;
    private EditText rightEditText = null;
    private Button displayButton = null;
    private EditText displayEditText = null;
    private CheckBox leftcheckBox = null;
    private CheckBox rightcheckBox = null;
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        leftEditText = (EditText)findViewById(R.id.edit1);
        rightEditText = (EditText)findViewById(R.id.edit2);
        displayButton = (Button)findViewById(R.id.display_button);
        leftcheckBox = (CheckBox)findViewById(R.id.check1);
        rightcheckBox = (CheckBox)findViewById(R.id.check2);
        displayEditText = (EditText)findViewById(R.id.left_edit_text);

        displayButton.setOnClickListener(buttonClickListener);
        ((Button)findViewById(R.id.second_activity_button)).setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("left")) {
                leftEditText.setText(savedInstanceState.getString("left"));
            }
            if (savedInstanceState.containsKey("right")) {
                rightEditText.setText(savedInstanceState.getString("right"));
            }
        }

    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        String edit1, edit2;

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.display_button:

                    edit1 = leftEditText.getText().toString();
                    edit2 = rightEditText.getText().toString();

                    if(leftcheckBox.isChecked() && !rightcheckBox.isChecked() && edit1 != null) {
                        displayEditText.setText(edit1);
                    } else if(!leftcheckBox.isChecked() && rightcheckBox.isChecked() && edit2 != null) {
                        displayEditText.setText(edit2);
                    } else if(leftcheckBox.isChecked() && rightcheckBox.isChecked() && edit1 != null && edit2 != null) {
                        displayEditText.setText(edit1 + edit2);
                    } else {
                        Toast.makeText(PracticalTest01Var03MainActivity.this, "Error inserting", Toast.LENGTH_LONG).show();
                    }

                    break;
                case R.id.second_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
                    intent.putExtra("key", "s");
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("left", leftEditText.getText().toString());
        savedInstanceState.putString("right", rightEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("left")) {
            leftEditText.setText(savedInstanceState.getString("left"));
        }
        if (savedInstanceState.containsKey("right")) {
            rightEditText.setText(savedInstanceState.getString("right"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
