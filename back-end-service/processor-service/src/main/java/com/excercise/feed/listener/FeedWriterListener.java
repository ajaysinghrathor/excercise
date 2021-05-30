package com.excercise.feed.listener;

import com.excercise.feed.entity.Portfolio;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FeedWriterListener implements ItemWriteListener<Portfolio> {

    @Override
    public void beforeWrite(List<? extends Portfolio> items) {
        System.out.println("ItemWriteListener - beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends Portfolio> items) {
        System.out.println("ItemWriteListener - afterWrite");
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Portfolio> items) {
        System.out.println("ItemWriteListener - onWriteError");
    }


}
