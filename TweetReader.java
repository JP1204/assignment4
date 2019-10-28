package assignment4;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.File;
import java.io.IOException;


/**
 * TweetReader contains method used to return tweets from method
 * Do not change the method header
 */
public class TweetReader {
    /**
     * Find tweets written by a particular user.
     *
     * @param url
     *            url used to query a GET Request from the server
     * @return return list of tweets from the server
     *
     */

    public static List<Tweets> readTweetsFromWeb(String url) throws Exception
    {
        List<Tweets> tweetList = new ArrayList<>();

        // get request
        HttpClient obj = new HttpClient();
        String json_str = null;

        try {
            json_str = obj.sendGet(url);
        } finally {
            obj.close();
            if(json_str == null){
                System.out.println("Could not retrieve tweets");
                return tweetList;
            }
        }

        // convert long json string to individual json tweets

        /*
        int json_len = json_str.length();
        json_str = json_str.substring(1, json_len-1);
        // System.out.println("json str:\n" + json_str);

        String[] json_tweets = json_str.split("},");
        for(int i = 0; i < json_tweets.length-1; i++){
            json_tweets[i] += '}';
            System.out.println(json_tweets[i]);
        }*/

        // parse json tweets into tweet list
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

        tweetList = mapper.readValue(json_str, new TypeReference<List<Tweets>>(){});

        for(int i = 0; i < tweetList.size(); i++){
            Tweets tweet = tweetList.get(i);

            System.out.println(tweet.toString());
            if(!isValidTweet(tweet)) {
                System.out.println("not valid tweet");
                tweetList.remove(i);    // remove invalid tweet
            }
        }
        return tweetList;
    }

    private static boolean isValidTweet(Tweets tweet){

        return true;
    }
}
