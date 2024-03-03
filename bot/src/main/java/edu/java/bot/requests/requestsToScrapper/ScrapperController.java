package edu.java.bot.requests.requestsToScrapper;


import edu.java.bot.response.scrapper.LinkResponse;
import edu.java.bot.response.scrapper.ListLinkResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController//Это контроллер + возвращаемое значение привязано к объекту
@RequestMapping("/scrapper")//контроллер обрабатывает методы, начинающиеся с "/scrapper"
public class ScrapperController {


    @ResponseStatus
    @PostMapping("/tg-chat/{id}")//Аннотация для сопоставления http-post запросов с определённым методом
    //@RequestBody говорит, чтобы тело запроса было прочитано и десериализованно в объект
    //@PathVariable аннотация говорит, что переменная привязана к запросу т.е. берётся из URI
    public ResponseEntity<String> registerChat(@PathVariable Long id) {


        //возвращаем ответ от сервера (пока что заглушка на ок(200))
        return ResponseEntity.ok("Чат зарегистрирован");
    }

    @DeleteMapping("/tg-chat/{id}")//Аннотация для сопоставления http-delete запросов с определённым методом
    public ResponseEntity<String> deleteChat(@PathVariable Long id) {


        //возвращаем ответ от сервера (пока что заглушка на ок(200))
        return ResponseEntity.ok("Чат успешно удалён");
    }


    @GetMapping("/links")//Аннотация для сопоставления http-delete запросов с определённым методом
    public ResponseEntity<ListLinkResponse> getAllLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId) {

        ListLinkResponse links = new ListLinkResponse();

        //возвращаем ответ от сервера (пока что заглушка на ок(200))
        return ResponseEntity.ok(links);
    }

    @PostMapping("/links")//Аннотация для сопоставления http-delete запросов с определённым методом
    public ResponseEntity<LinkResponse> addLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId,
                                                 @RequestBody AddLinkRequest addLinkRequest) {

        LinkResponse linkResponse = new LinkResponse();

        //возвращаем ответ от сервера (пока что заглушка на ок(200))
        return ResponseEntity.ok(linkResponse);
    }


    @DeleteMapping("/links")//Аннотация для сопоставления http-delete запросов с определённым методом
    public ResponseEntity<LinkResponse> addLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId,
                                                 @RequestBody RemoveLinkRequest request) {

        LinkResponse linkResponse = new LinkResponse();

        //возвращаем ответ от сервера (пока что заглушка на ок(200))
        return ResponseEntity.ok(linkResponse);
    }
}
