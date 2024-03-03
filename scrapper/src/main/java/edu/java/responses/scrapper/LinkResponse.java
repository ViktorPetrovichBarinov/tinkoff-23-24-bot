package edu.java.responses.scrapper;

import java.net.URI;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkResponse {
    private Long id; //format: int64
    private URI url; //format uri
}
