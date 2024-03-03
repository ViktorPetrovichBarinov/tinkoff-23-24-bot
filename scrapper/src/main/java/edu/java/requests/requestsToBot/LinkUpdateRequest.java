package edu.java.requests.requestsToBot;

import java.net.URI;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Данный класс представляет объект запроса, который ожидается в теле
 * HTTP-post запроса в нашем API
 */

@Getter
@Setter
public class LinkUpdateRequest {
    private Long id; //type - int64
    private URI url; //type - uri
    private String description;
    private List<Long> tgChatIds; // items format - int64
}
