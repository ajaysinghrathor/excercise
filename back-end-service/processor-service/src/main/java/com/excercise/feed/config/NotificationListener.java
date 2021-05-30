package com.excercise.feed.config;


import com.excercise.feed.constant.Status;
import com.excercise.feed.entity.FeedMetaData;
import com.excercise.feed.entity.Portfolio;
import com.excercise.feed.repository.FeedMetaDataRepository;
import com.excercise.feed.repository.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;


@Component
@JobScope
public class NotificationListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);
    @Value("#{jobParameters['input.file.name']}")
    private String feedName;
    @Autowired private FeedMetaDataRepository feedMetaDataRepository;
    @Autowired private PortfolioRepository portfolioRepository;

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }


    @Override
    public void afterJob(final JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
            File file = new File(feedName);
            List<Portfolio> feedData = portfolioRepository.findByFeed(file.getName());
            if (feedData != null && !feedData.isEmpty()){
                updateFeedMeetaDataStatus(Status.SUCCESS.toString());
            }else{
                updateFeedMeetaDataStatus(Status.FAIL.toString());
            }
        }
    }

    private void updateFeedMeetaDataStatus(String status) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + feedName);
        File file = new File(feedName);
        FeedMetaData feed = feedMetaDataRepository.findByFeed(file.getName());
        if(feed != null) {
            feed.setStatus(status);
            feedMetaDataRepository.save(feed);
        }
    }
}

