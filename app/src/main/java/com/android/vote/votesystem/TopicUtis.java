package com.android.vote.votesystem;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TopicUtis {

    private String TAG = "Vote";
    private static Context mContext = null;
    public ArrayList<Topic> mTopicList = new ArrayList<Topic>();
    public static int TOP_TOPIC_COUNT ;
    public static int LIMIT_TOPIC_CHARACTERS;

    public TopicUtis(Context context){
        mContext = context;
        if(mContext != null) {
            TOP_TOPIC_COUNT = mContext.getResources().getInteger(R.integer.int_top_topic_count);
            LIMIT_TOPIC_CHARACTERS = mContext.getResources().getInteger(R.integer.int_topic_limit);
        }
    }

    public static class Topic{
        String content;
        int upvote;
        int downvote;

        public Topic(String content , int upvote , int downvote ,int vote) {
            this.content = content;
            this.upvote = upvote;
            this.downvote = downvote;
        }
    }

    public ArrayList<Topic> getTopicList(){
        return mTopicList;
    }


    public void addTopic(String context){
        Topic topic = new Topic(context,0,0,0);
        mTopicList.add(topic);
        Log.d(TAG,"mTopicList size = " + mTopicList.size());
    }

    public void up(int index){
        mTopicList.get(index).upvote++;
        sort();
    }

    public void down(int index){
        mTopicList.get(index).downvote++;
        sort();
    }


    private void sort(){
        Collections.sort(mTopicList,new Comparator<Topic>()
                {
                    @Override
                    public int compare (Topic o1, Topic o2){
                        return o2.upvote - o1.upvote;
                    }
                }
        );
    }
}
