package com.excercise.feed.poller;

import com.excercise.feed.config.BatchConfiguration;
import com.excercise.feed.constant.Status;
import com.excercise.feed.entity.FeedMetaData;
import com.excercise.feed.repository.FeedMetaDataRepository;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Component
public class FeedDirectoryScanner {
    @Value("${feed.src.dir.location}")
    private String feedPath;
    @Autowired private BatchConfiguration batchConfiguration;
    @Autowired private JobLauncher jobLauncher;
    @Autowired private FeedMetaDataRepository feedMetaDataRepository;

    @Bean
    public MessageChannel feedInputChannel(){
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "feedInputChannel", poller = @Poller(cron = "1 * * * * *"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(feedPath));
        source.setFilter(new SimplePatternFileListFilter("*.csv"));
        source.setScanEachPoll(true);
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "feedInputChannel")
    public MessageHandler handler() {
        File file = new File(feedPath);
        final FileWritingMessageHandler handler = new FileWritingMessageHandler(file);
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(true);
        handler.setOutputChannelName("parse-csv-channel");
        return handler;
    }

    @ServiceActivator(inputChannel = "parse-csv-channel", outputChannel = "job-channel")
    public JobLaunchRequest adapt(final File file) throws Exception {
        final JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addLong("time",System.currentTimeMillis())
                .addString("input.file.name", file.getAbsolutePath())
                .toJobParameters();
        FeedMetaData data = feedMetaDataRepository.findByFeed(file.getName());
        if(data == null){
            data = new FeedMetaData();
            data.setDate(new Date());
            data.setFeed(file.getName());
            data.setSize(file.length());
            data.setStatus(Status.IN_PROCESS.toString());
            feedMetaDataRepository.save(data);
            return new JobLaunchRequest(batchConfiguration.importPortfolioJob(), jobParameters);
        }else{
            return null;
        }
    }

    @Bean
    @ServiceActivator(inputChannel = "job-channel")
    public JobLaunchingGateway jobHandler() {
        JobLaunchingGateway jobLaunchingGateway = new JobLaunchingGateway(jobLauncher);
        jobLaunchingGateway.setOutputChannelName("finish");
        return jobLaunchingGateway;
    }
    @ServiceActivator(inputChannel = "finish")
    public void finish() {
        System.out.println("+++++++++++++++++++++++END++++++++++++++++++++");
    }

    //TODO: Write Outbound endpoint to trigger job via rest service.

}
