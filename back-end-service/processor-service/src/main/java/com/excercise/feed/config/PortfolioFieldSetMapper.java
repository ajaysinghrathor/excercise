package com.excercise.feed.config;

import com.excercise.feed.constant.Status;
import com.excercise.feed.entity.Portfolio;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PortfolioFieldSetMapper implements FieldSetMapper<Portfolio> {

    @Override
    public Portfolio mapFieldSet(FieldSet fieldSet) {
        final Portfolio portfolio = new Portfolio();
        portfolio.setDate(fieldSet.readString("date"));
        portfolio.setType(fieldSet.readString("type"));
        portfolio.setSymbol(fieldSet.readString("symbol"));
        portfolio.setShares(fieldSet.readString("shares"));
        portfolio.setPrice(fieldSet.readString("price"));
        portfolio.setCosts(fieldSet.readString("costs"));
        portfolio.setFees(fieldSet.readString("fees"));
        portfolio.setAmount(fieldSet.readString("amount"));
        portfolio.setStatus(Status.IN_PROCESS.toString());
        return portfolio;
    }
}
