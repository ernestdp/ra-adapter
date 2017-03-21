package com.ernest.reefangel.domain;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ernest on 2017/03/20.
 */
public class LastReplies {

    public static TreeSet<String> replies = new TreeSet<String>();

    public static void add(String reply){
        if(replies.size()>=10)
        {
            //get remove the first object
            replies.remove(replies.first());
        }
        replies.add(reply);
    }

    public static String getLatestReply()
    {
        return replies.last();
    }


    public static String[] getLast2Replies()
    {
        String[] last2Replies = new String[2];
        Iterator<String> stringIterator = replies.descendingIterator();
        int count =0;
        while(stringIterator.hasNext() && count <2)
        {
            last2Replies[count]= stringIterator.next();
            count++;
        }
        return last2Replies;
    }

    public Set repliesLatestFirst()
    {
        return replies.descendingSet();
    }
}
