package com.excercise.feed.listener;

import com.excercise.feed.constant.Status;
import com.excercise.feed.entity.Portfolio;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
public class FeedProcessorListener implements ItemProcessListener<Portfolio, Portfolio> {

    @Override
    public void onProcessError(Portfolio portfolio, Exception e) {
        portfolio.setStatus(Status.FAIL.toString());
    }

    @Override
    public void afterProcess(Portfolio portfolio, Portfolio portfolio2) {
        portfolio.setStatus(Status.FAIL.toString());
        portfolio2.setStatus(Status.SUCCESS.toString());
    }

    @Override
    public void beforeProcess(Portfolio portfolio) {

    }
}
