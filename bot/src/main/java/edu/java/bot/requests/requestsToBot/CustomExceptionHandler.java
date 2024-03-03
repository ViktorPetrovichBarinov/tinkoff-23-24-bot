package edu.java.bot.requests.requestsToBot;

import edu.java.bot.response.bot.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice//Показывает что класс для обработки исключений
public class CustomExceptionHandler {

    private static final String ERROR_DESCRIPTION = "Incorrect request's parameters";


    /**
     * Данный метод будет вызван при возникновении исключения Exception в любом из контроллеров
     *
     * @param ex - ошибка
     * @return - ответ сервера
     */
    @ExceptionHandler(Exception.class)//Будет обрабатывать исключения типа Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)//Устанавливает HTTP статус
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        //создаём объект для ошибки
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setDescription(ERROR_DESCRIPTION);
        errorResponse.setCode("400");
        errorResponse.setExceptionName(ex.getClass().getName()); //получаем имя ошибки
        errorResponse.setExceptionMessage(ex.getMessage());

        // тут может быть будет логирование или какая-либо другая логика

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
