package com.android.vote.votesystem;


import android.util.Log;

import java.util.ArrayList;

public class TopicUtis {

    private String TAG = "Vote";
    public ArrayList<Topic> mTopicList = new ArrayList<Topic>();

    public static class Topic{
        String content;
        int upvote;
        int downvote;

        public Topic(String content , int upvote , int downvote ) {
            this.content = content;
            this.upvote = upvote;
            this.downvote = downvote;
        }
    }

    public ArrayList<Topic> getTopicList(){
        return mTopicList;
    }


    public void addTopic(String context){
        Topic topic = new Topic(context,0,0);
        mTopicList.add(topic);
        Log.d(TAG,"mTopicList size = " + mTopicList.size());
    }

    public void up(int index){
        mTopicList.get(index).upvote++;
    }

    public void down(int index){
        mTopicList.get(index).downvote++;
    }
}
