package com.android.vote.votesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PostTopicActivity extends AppCompatActivity {

    private EditText mTopicEditText = null;
    private TextView mShowLimitTextView = null;
    private Button mPostButton = null;
    private int limit = TopicUtis.LIMIT_TOPIC_CHARACTERS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_topic);

        mTopicEditText = (EditText) findViewById(R.id.topic);
        mShowLimitTextView = (TextView) findViewById(R.id.msg);
        mPostButton = (Button) findViewById(R.id.post);

        mShowLimitTextView.setText(limit + getString(R.string.msg_limit_chars));
        mTopicEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                int rest = limit - mTopicEditText.getText().length();
                if(rest < 2 ){
                    mShowLimitTextView.setText(rest + getString(R.string.msg_limit_char));
                } else{
                    mShowLimitTextView.setText(rest + getString(R.string.msg_limit_chars));
                }
            }
        });

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopTopicListActivity.getTopicUtis().addTopic(mTopicEditText.getText().toString());
                finish();
            }
        });
    }
}
