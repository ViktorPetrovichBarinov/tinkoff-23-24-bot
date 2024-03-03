package edu.java.responses.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitHubRepositoryResponse {
    private Long id;
    private String name;
    @JsonProperty("full_name") private String fullName;
    @JsonProperty("html_url") private String htmlUrl;
    @JsonProperty("url") private String apiUrl;
    @JsonProperty("created_at") private OffsetDateTime createdAt;
    @JsonProperty("pushed_at") private OffsetDateTime pushedAt;
    @JsonProperty("updated_at") private OffsetDateTime updatedAt;
}
