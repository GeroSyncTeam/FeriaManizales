package com.example.gero.feriapp.Twitter;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by autentia on 16/12/13.
 */
public class TweetRepository {
    private static TweetRepository instance;

    private TweetRepository() {

    }

    public static TweetRepository getInstance() {
        if (instance == null) {
            instance = new TweetRepository();
        }
        return instance;
    }

    private ConfigurationBuilder getConfiguration() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("uIiEVsgEssMmUb4S4zsgyUSMx")
                .setOAuthConsumerSecret("lQsWc9qwTkTO2IAvs2SAl4poKVtywzo4qymTCbOXumhgqs68g9")
                .setOAuthAccessToken("2888931840-xV9N3Il9xZSjcUpTYnO6V99eXxCTxblHRcMNo3b")
                .setOAuthAccessTokenSecret("EZP1zNi7oatrbT8NadvyVxEgF6omlPcqh2UQ4O4ALS0Xu");
        return cb;
    }

    public void getTimelineAsync(TwitterListener listener) {
        AsyncTwitterFactory factory = new AsyncTwitterFactory((getConfiguration().build()));
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);
        asyncTwitter.getHomeTimeline();
    }
}
