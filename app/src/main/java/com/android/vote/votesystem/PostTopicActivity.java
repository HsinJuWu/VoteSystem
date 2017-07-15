package com.android.vote.votesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PostTopicActivity extends AppCompatActivity {

    private EditText editText = null;
    private TextView textView = null;
    private Button button = null;
    private int limit = 255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_topic);

        editText = (EditText) findViewById(R.id.topic);
        textView = (TextView) findViewById(R.id.msg);
        button = (Button) findViewById(R.id.post);

        textView.setText(limit+" chars");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int rest = limit -editText.getText().length();
                textView.setText(rest + " chars");

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopTopicListActivity.getTopicUtis().addTopic(editText.getText().toString());
                finish();
            }
        });
    }
}
