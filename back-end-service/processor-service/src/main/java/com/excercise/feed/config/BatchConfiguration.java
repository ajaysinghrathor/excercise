package com.excercise.feed.config;

import com.excercise.feed.constant.Fields;
import com.excercise.feed.entity.Portfolio;
import com.excercise.feed.listener.FeedProcessorListener;
import com.excercise.feed.listener.FeedReaderListener;
import com.excercise.feed.listener.FeedWriterListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;
import java.io.File;


//@Scope("step")
//@JobScope
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final String INSERT = "INSERT INTO Portfolio (date, type,symbol,shares,price,costs,fees,amount,status,feed)" +
            " VALUES (:date, :type, :symbol, :shares, :price, :costs, :fees, :amount, :status, :feed)";
    private static final String CLASSPATH_PORTFOLIOS_CSV = "data/sbi-mf-data.csv";
    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    @Autowired private FeedProcessorListener feedProcessorListener;
    @Autowired private FeedReaderListener feedReaderListener;
    @Autowired private FeedWriterListener feedWriterListener;
    @Autowired private DataSource dataSource;
    @Autowired private NotificationListener notificationListener;
    @Autowired private FeedProcessor processor;


    public String getFileAbsPath() {
        return fileAbsPath;
    }

    private String fileAbsPath;

    @StepScope
    @Bean
    public FlatFileItemReader<Portfolio> reader(@Value("#{jobParameters['input.file.name']}") String name) {
          setFileAbsPath(name);
          return new FlatFileItemReaderBuilder<Portfolio>()
                .name("portfolioItemReader")
                .resource(new FileSystemResource(new File(name)))
                .delimited()
                .names(fields())
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Portfolio>() {{

                    setTargetType(Portfolio.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<Portfolio> lineMapper() {

        final DefaultLineMapper<Portfolio> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(fields());

        final PortfolioFieldSetMapper fieldSetMapper = new PortfolioFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }


    /*@Bean
    public FeedProcessor processor() {
        return new FeedProcessor();

    }*/

    public Job importPortfolioJob() {
        return jobBuilderFactory.get("importPortfolioJob")
                .incrementer(new RunIdIncrementer())
                .listener(notificationListener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Portfolio> writer() {
        return new JdbcBatchItemWriterBuilder<Portfolio>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(INSERT.toLowerCase())
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Portfolio, Portfolio>chunk(10)
                .reader(reader(""))
                .processor(processor)
                .writer(writer())
                .allowStartIfComplete(true)
                //.listener(feedProcessorListener)
                //.listener(feedReaderListener)
                //.listener(feedWriterListener)
                .taskExecutor(executor())
                .build();
    }
    //@StepScope
    //@Value("#{jobParameters['fileAbsPath']}")
    public void setFileAbsPath(String fileAbsPath) {
        this.fileAbsPath = fileAbsPath;
    }

    @Bean
    public TaskExecutor executor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    private String[] fields(){
        String[] array = new String[Fields.values().length];
        for (int i = 0; i < Fields.values().length; i++) {
            array[i] = Fields.values()[i].name().toString().toLowerCase();
        }
        return array;
    }

}
