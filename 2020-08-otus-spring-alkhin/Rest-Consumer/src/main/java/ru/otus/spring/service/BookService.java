package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${spring.security.user.name}")
    private String username;

    @HystrixCommand(fallbackMethod = "emptyBook")
    public String getBooks() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        return restTemplate
                .getForObject("http://localhost:9090/api/books",
                        String.class);
    }

    private String defaultGreeting(String username) {
        return "Hello User!";
    }

    @HystrixCommand(fallbackMethod = "emptyBook")
    public String getBook(Long id) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        return restTemplate
                .getForObject("http://localhost:9090/api/books/" + id,
                        String.class);
    }

    private String emptyBook() {
        String json = new StringBuilder()
                .append("{")
                .append("\"id\":\"" + "id" + "\",")
                .append("\"title\":\"" + "title" + "\",")
                .append("\"description\":\"" + "description" + "\",")
                .append("\"comments\":\"" + "comments" + "\"")
                .append("}")
                .toString();
        return json;
    }

}