package edu.java.requests.requestsToScrapper;

import java.net.URI;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddLinkRequest {
    private URI link;
}
