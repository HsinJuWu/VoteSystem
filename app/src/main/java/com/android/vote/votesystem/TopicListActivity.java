package com.android.vote.votesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TopicListActivity extends AppCompatActivity {

    private String TAG = "Vote";
    protected ListView mListView = null;
    protected TopicAdapter mAdapter = null;
    protected VoteOnClickListener mVoteOnClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initFloatingButton();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setContactsAdapter();
    }

    private void initView(){
        setContentView(R.layout.activity_topic_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setEmptyView(findViewById(android.R.id.empty));
        mListView.setClickable(false);
        mVoteOnClickListener = new VoteOnClickListener();
    }

    private void initFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostTopicActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void setContactsAdapter(){
        mAdapter = new TopicAdapter(TopTopicListActivity.getTopicUtis().getTopicList());
        mListView.setAdapter(mAdapter);
    }
    protected class TopicAdapter extends BaseAdapter {
        private ArrayList<TopicUtis.Topic> list;


        public TopicAdapter(ArrayList<TopicUtis.Topic> data) {
            list = data;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            Holder holder;
            if(v == null){
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.topic_list_item, null);
                holder = new Holder();
                holder.text = (TextView) v.findViewById(R.id.content);
                holder.up = (Button) v.findViewById(R.id.up);
                holder.up.setOnClickListener(mVoteOnClickListener);
                holder.upNum = (TextView) v.findViewById(R.id.upNum);
                holder.down = (Button) v.findViewById(R.id.down);
                holder.down.setOnClickListener(mVoteOnClickListener);
                holder.downNum = (TextView) v.findViewById(R.id.downNum);
                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            holder.text.setText(list.get(position).content);
            holder.upNum.setText(Integer.toString(list.get(position).upvote));
            holder.downNum.setText(Integer.toString(list.get(position).downvote));


            return v;
        }
        class Holder{
            TextView text;
            Button up;
            TextView upNum;
            Button down;
            TextView downNum;
        }
    }

    class VoteOnClickListener implements View.OnClickListener {
        public void onClick(View v){
            int index =  mListView.getPositionForView((View) v.getParent());
            switch(v.getId()){
                case R.id.up:
                    TopTopicListActivity.getTopicUtis().up(index);
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.down:
                    TopTopicListActivity.getTopicUtis().down(index);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent =null;
        switch (id){
            case R.id.action_top_topic:
                finish();
                return true;
            case R.id.action_post:
                intent = new Intent(TopicListActivity.this, PostTopicActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
