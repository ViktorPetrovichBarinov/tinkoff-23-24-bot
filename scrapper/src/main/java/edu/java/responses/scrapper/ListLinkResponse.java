package edu.java.responses.scrapper;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListLinkResponse {
    private List<LinkResponse> links; // items: $ref: '#/components/schemas/LinkResponse'
    private Integer size;
}
