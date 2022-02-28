Jsonplaceholder API Tests (Cucumber - Rest Assured) 
========================================

This automated test suite has been designed to test the API hosted as https://jsonplaceholder.typicode.com/

## Features
The project has total 12 scenarios listed under 3 feature files to support following operations:

 - GET	/posts
 - GET	/posts/1
 - GET	/posts/1/comments
 - GET	/comments?postId=1
 - POST	/posts
 - PUT	/posts/1
 - PATCH	/posts/1
 - DELETE	/posts/1

## Execution
To execute the tests, navigate to the root directory of project (where build.gradle exist) and run the command:

```sh
gradle clean test
```

## Report

The output will be printed on console and will also get reported at _target/reports/test-report/index.html_


