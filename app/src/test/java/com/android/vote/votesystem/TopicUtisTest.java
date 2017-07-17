package com.android.vote.votesystem;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hnfish on 2017/7/17.
 */
public class TopicUtisTest {

    TopicUtis mTopicUtils;
    Context mMokeContext;
    @Before
    public void setUp() throws Exception {
        mTopicUtils = new TopicUtis(mMokeContext);
    }

    @Test
    public void getTopicList() throws Exception {
        assertNotNull(mTopicUtils.getTopicList());
    }

}