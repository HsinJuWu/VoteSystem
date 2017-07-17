package com.android.vote.votesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
public class TopTopicListActivity extends TopicListActivity {

    private static TopicUtis mTopicUtis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopicUtis = new TopicUtis(getApplicationContext());
        setTitle(R.string.title_top_topic_list);

    }

    @Override
    protected void setContactsAdapter() {
        mAdapter = new TopTopicAdapter(getTopicUtis().getTopicList());
        mListView.setAdapter(mAdapter);
    }

    private class TopTopicAdapter extends TopicListActivity.TopicAdapter {
        private ArrayList<TopicUtis.Topic> list;

        public TopTopicAdapter(ArrayList<TopicUtis.Topic> data) {
            super(data);
            list = data;
        }

        @Override
        public int getCount() {
            //This view only show top topic , set the MAX count is TOP_TOPIC_COUNT
            if(list.size() > TopicUtis.TOP_TOPIC_COUNT ) {
                return TopicUtis.TOP_TOPIC_COUNT;
            }
            else {
                return list.size();
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            Holder holder;
            if(v == null){
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.topic_list_item, null);
                holder = new Holder();
                holder.topNum = (TextView) v.findViewById(R.id.topNum);
                holder.topNum.setVisibility(View.VISIBLE);
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
            holder.topNum.setText(getString(R.string.msg_top_number) + (position+1));
            holder.text.setText(list.get(position).content);
            holder.upNum.setText(Integer.toString(list.get(position).upvote));
            holder.downNum.setText(Integer.toString(list.get(position).downvote));

            return v;
        }
        class Holder{
            TextView topNum;
            TextView text;
            Button up;
            TextView upNum;
            Button down;
            TextView downNum;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_topic_list, menu);
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
            case R.id.action_all_post:
                intent = new Intent(this.getApplicationContext(), TopicListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_post:
                intent = new Intent(this.getApplicationContext(), PostTopicActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static TopicUtis getTopicUtis() {
        return mTopicUtis;
    }
}
