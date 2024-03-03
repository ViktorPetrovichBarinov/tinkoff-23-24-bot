package edu.java.bot.requests.requestsToBot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Это контроллер + возвращаемое значение привязано к объекту
@RequestMapping("/updates")//контроллер обрабатывает методы, начинающиеся с "/updates"
public class UpdateController {

    @PostMapping//аннотация для сопоставления http-post запросов с определённым методом
    //@RequestBody говорит, чтобы тело запроса было прочитано и десериализованно в объект
    public ResponseEntity<String> processUpdate(@RequestBody LinkUpdateRequest linkUpdateRequest) {


        //возвращаем ответ от сервера (пока что заглушка на ок(200))
        return ResponseEntity.ok("Обновление обработано");
    }
}
