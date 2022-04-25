# BMW Java Project - Test Task

### This software implementation for testing REST endpoint

### Tools & Technologies stack

* Java 8;
* Spring;
* JDBC API;
* Flyway;
* PostgreSQL;

### Project description

The implemented project automatically performs a http request once a minute. Checks the number of
users and the status of the request, if the number differs from those in the database, then
overwrites.

Example code:

```java
@Service
public class HttpService {

    public <T> ResponseEntity<T> performGetRequest(Class<T> responseClass, String URI) {
        return new RestTemplate().getForEntity(URI, responseClass);
    }
}
```


All performed actions and statuses are written to a log file

Example of entries in the file.log
```
23:19:41.191 INFO  - HTTP method GET. Endpoint URI - https://jsonplaceholder.typicode.com/users
23:19:41.191 INFO  - Http call with status 200 OK
23:19:41.354 INFO  - Resave users
23:20:41.473 INFO  - HTTP method GET. Endpoint URI - https://jsonplaceholder.typicode.com/users
23:20:41.474 INFO  - Http call with status 200 OK
23:20:41.481 INFO  - Received correct response with number of users 10
23:21:41.585 INFO  - HTTP method GET. Endpoint URI - https://jsonplaceholder.typicode.com/users
23:21:41.585 INFO  - Http call with status 200 OK
23:21:41.597 INFO  - Received correct response with number of users 10
23:22:41.684 INFO  - HTTP method GET. Endpoint URI - https://jsonplaceholder.typicode.com/users
23:22:41.684 INFO  - Http call with status 200 OK
```