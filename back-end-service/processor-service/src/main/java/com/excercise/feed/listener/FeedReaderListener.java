package com.excercise.feed.listener;

import com.excercise.feed.entity.Portfolio;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class FeedReaderListener implements ItemReadListener<Portfolio> {

    @Override
    public void beforeRead() {
        //TODO: Integrate service activator to notify
    }

    @Override
    public void onReadError(Exception e) {
        //TODO: Integrate service activator to notify
    }

    @Override
    public void afterRead(Portfolio portfolio) {
        //TODO: Integrate service activator to notify
    }
}



