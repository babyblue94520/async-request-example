# Async Request Double Error Result

## Overview

I customized [CustomAsyncTaskExecutor](src/main/java/com/example/asyncrequestexample/infrastructure/support/concurrent/CustomAsyncTaskExecutor.java) to throw RejectedExecutionException when the queue is full, but I got twice the error
message.

## Demo

- Throw exception in callable

  - request

    ```
    http://127.0.0.1:8080/test
    ```

  - response

    ```json
    {
      "status": false,
      "message": "get test"
    }
    ```

- Throw RejectedExecutionException in CustomAsyncTaskExecutor

  [ControllerExceptionAdvice](src/main/java/com/example/asyncrequestexample/infrastructure/support/concurrent/ControllerExceptionAdvice.java) will be called twice.

  - json

    - request

      ```
      http://127.0.0.1:8080/test/full
      ```

    - response

      ```json
      {"status":false,"message":"Queue full"}{"status":false,"message":"Queue full"}
      ```

  - string

    - request

      ```
      http://127.0.0.1:8080/test/full/string
      ```

    - response

      ```text
      Queue full
      ```

- Throw other exception in CustomAsyncTaskExecutor

  Must wait for the request to time out before returning all results.

  - request

    ```
    http://127.0.0.1:8080/test/timeout
    ```

  - response

    - first

      ```json
      { "status": false, "message": "Exception" }
      ```

    - final

      Waiting for request timeout.

      ```json
      {"status":false,"message":"Exception"}{"status":false,"message":"timeout"}
      ```
