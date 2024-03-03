package edu.java.requests.requestsToScrapper;

import java.net.URI;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveLinkRequest {
    private URI link; //format: uri
}
