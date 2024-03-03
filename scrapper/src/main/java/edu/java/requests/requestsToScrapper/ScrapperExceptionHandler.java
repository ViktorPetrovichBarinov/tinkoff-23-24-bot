package edu.java.requests.requestsToScrapper;

import edu.java.requests.requestsToScrapper.exceptions.ChatArentExists;
import edu.java.requests.requestsToScrapper.exceptions.IncorrectRequestParameters;
import edu.java.responses.scrapper.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SuppressWarnings("MultipleStringLiterals")
@RestControllerAdvice
public class ScrapperExceptionHandler {
    private static final String ERROR_DESCRIPTION = "Incorrect request's parameters";

    @ExceptionHandler(IncorrectRequestParameters.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleExceptionIncorrectRequestParameters(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setDescription(ERROR_DESCRIPTION);
        errorResponse.setCode("400");
        errorResponse.setExceptionName(ex.getClass().getName());
        errorResponse.setExceptionMessage(ex.getMessage());

        // Здесь можно добавить логирование или другие действия при ошибке

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LinkageError.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleExceptionLinkNotFound(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setDescription(ERROR_DESCRIPTION);
        errorResponse.setCode("404");
        errorResponse.setExceptionName(ex.getClass().getName());
        errorResponse.setExceptionMessage(ex.getMessage());

        // Здесь можно добавить логирование или другие действия при ошибке

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ChatArentExists.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleExceptionChatArentExists(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setDescription(ERROR_DESCRIPTION);
        errorResponse.setCode("404");
        errorResponse.setExceptionName(ex.getClass().getName());
        errorResponse.setExceptionMessage(ex.getMessage());

        // Здесь можно добавить логирование или другие действия при ошибке

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
