# excercise: Run Instructions

##### How to Run processor-service & data-service

1. checkout and import both project as Maven project
2. run mvn clean install
3. run the ProcessorService.java (main)
4. place the sample feed file sbi-mf-data.csv into /data/in/
5. access and login h2 im-memory database console with credential:
	http://<host>:8080/h2-console
	spring.datasource.url=jdbc:h2:mem:batchdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username=sa
	spring.datasource.password=password
	
6. run following queries to check feed data:
	select * from FeedMetaData; //this table will store feed meta data.
	select * from portfolio; // this table will store feed file data.
7. feed data can be access with following url:
	http://<host>:8080/api/data/feeds

##### How to Run front-end-service:
1. after checking project
2. run following commands:
	npm install
	ng run build
	ng server --open
3. access ui with url:
	http://<host>:4200/

