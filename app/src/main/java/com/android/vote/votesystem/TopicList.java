package com.android.vote.votesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
public class TopicList extends AppCompatActivity {

    private String TAG = "Vote";
    private static TopicUtis mTopicUtis = new TopicUtis();
    private ListView mListView = null;
    private TopicAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicList.this, PostTopicActivity.class);
                startActivity(intent);
            }
        });
        mListView = (ListView) findViewById(R.id.list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContactsAdapter();
    }

    private void setContactsAdapter(){

        mAdapter = new TopicAdapter(mTopicUtis.getTopicList());
        mListView.setAdapter(mAdapter);
        mListView.setClickable(false);
    }
    private class TopicAdapter extends BaseAdapter {
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
                holder.upNum = (TextView) v.findViewById(R.id.upNum);
                holder.down = (Button) v.findViewById(R.id.down);
                holder.downNum = (TextView) v.findViewById(R.id.downNum);

                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            Log.d(TAG,"position = " + position);
            Log.d(TAG,"content = " + list.get(position).content);
            Log.d(TAG,"upvote = " + list.get(position).upvote);
            Log.d(TAG,"downvote = " + list.get(position).downvote);
            holder.text.setText(list.get(position).content);
            holder.up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TopicList.getTopicUtis().up(position);
                }
            });
            holder.upNum.setText(Integer.toString(list.get(position).upvote));
            holder.down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TopicList.getTopicUtis().down(position);
                }
            });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_post) {
            Intent intent = new Intent(TopicList.this, PostTopicActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static TopicUtis getTopicUtis() {
        return mTopicUtis;
    }
}
