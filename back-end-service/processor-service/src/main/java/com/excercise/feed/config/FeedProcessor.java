package com.excercise.feed.config;

import com.excercise.feed.constant.Status;
import com.excercise.feed.entity.Portfolio;
import com.excercise.feed.validation.DataValidationService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@StepScope
public class FeedProcessor implements ItemProcessor<Portfolio, Portfolio> {
    @Autowired private DataValidationService validationService;

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    @Value("#{jobParameters['input.file.name']}")
    private String feedName;

    @Override
    public Portfolio process(final Portfolio portfolio) {
        final Portfolio processedPortfolio = new Portfolio();
        processedPortfolio.setDate(portfolio.getDate());
        processedPortfolio.setType(portfolio.getType());
        processedPortfolio.setSymbol(portfolio.getSymbol());
        processedPortfolio.setShares(portfolio.getShares());
        processedPortfolio.setPrice(portfolio.getPrice());
        processedPortfolio.setCosts(portfolio.getCosts());
        processedPortfolio.setFees(portfolio.getFees());
        processedPortfolio.setAmount(portfolio.getAmount());
        boolean result = validationService.validate(processedPortfolio);
        if(result) {
            processedPortfolio.setStatus(Status.SUCCESS.toString());
        }else{
            processedPortfolio.setStatus(Status.FAIL.toString());
        }
        File file = new File(feedName);
        processedPortfolio.setFeed(file.getName());
        return processedPortfolio;
    }
}
