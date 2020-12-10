//package ru.otus.spring.config;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.batch.test.JobLauncherTestUtils;
//import org.springframework.batch.test.JobRepositoryTestUtils;
//import org.springframework.batch.test.context.SpringBatchTest;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
////@SpringBootTest
//@SpringBatchTest
//class JobConfigTest {
//
//    @Autowired
//    private JobLauncherTestUtils jobLauncherTestUtils;
//
//    @Autowired
//    private JobRepositoryTestUtils jobRepositoryTestUtils;
//
//    @Autowired
//    private BatchConfig batchConfig;
//
//    @Autowired
//    private JobConfig jobConfig;
//
////    @After
////    public void cleanUp() {
////        jobRepositoryTestUtils.removeJobExecutions();
////    }
//
//
//    @Test
//    void step1(EntityManagerFactory entityManagerFactory, EntityManager entityManager) throws Exception {
//
////        JobLauncherTestUtils jobL = new JobLauncherTestUtils();
////        jobL.launchJob()
////        Job job = jobLauncherTestUtils.getJob();
////        assertThat(job).isNotNull()
////                .extracting(Job::getName)
////                .isEqualTo(IMPORT_USER_JOB_NAME);
//
////        JpaItemWriter<Book> itemWriter = new JpaItemWriterBuilder<Book>()
////                .entityManagerFactory(entityManagerFactory)
////                .build();
////        itemWriter.afterPropertiesSet();
////        Book book1 = new Book("b1","b1", new ArrayList<Author>(), new ArrayList<Genre>());
////        Book book2 = new Book("b2","b2", new ArrayList<Author>(), new ArrayList<Genre>());
////        List<Book> books = Arrays.asList(book1, book2);//Collections.list(book1, book2);
////        itemWriter.write(books);
////        verify(entityManager).merge(books.get(0));
////        verify(entityManager).merge(books.get(1));
////
////    }
////
////    private EntityManager verify(EntityManager entityManager) {
////
////        return entityManager;
//    }
//}