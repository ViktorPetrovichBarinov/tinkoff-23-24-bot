package edu.java.responses.stackoverflow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


public record StackOverflowQuestionsResponse(
    List<QuestionInformation> items) {
    @Getter
    @Setter
    public static class QuestionInformation {
        @JsonProperty("owner") private Owner owner;

        @JsonProperty("last_activity_date")
        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        private OffsetDateTime lastActivityDate;

        @JsonProperty("accepted_answer_id")
        private long answerId;

        @JsonProperty("question_id")
        private long questionId;
    }

}

