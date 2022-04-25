package com.knubisoft.services;

import com.knubisoft.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@EnableScheduling
@RequiredArgsConstructor
@Service
@Slf4j(topic = "loggerBmw")
public class UserChecker {

    private static final String URI = "https://jsonplaceholder.typicode.com/users";

    private final UserService userService;
    private final HttpService httpService;

    @SneakyThrows
    @Scheduled(fixedDelayString = "PT1M")
    public void checkAndUpdateUsers() {
        ResponseEntity<UserDTO[]> response = httpService.performGetRequest(UserDTO[].class, URI);
        log.info("HTTP method {}. Endpoint URI - {}", HttpMethod.GET, URI);

        log.info("Http call with status {}", response.getStatusCode());
        if (response.getStatusCode().is2xxSuccessful()) {
            if (response.getBody() != null) {
                UserDTO[] responseBody = response.getBody();
                if (responseBody.length == userService.getUsers().size()) {
                    log.info("Received correct response with number of users {}",
                            responseBody.length);
                } else {
                    userService.removeUsersAndSave(Arrays.asList(responseBody));
                    log.info("Resave users");
                }
            } else {
                log.info("No users in response body");
            }
        }
    }

}
