package com.web.ddajait.model.dto.CertificateInfo.ExamContent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @JsonProperty("type")
    private String type;

    @JsonProperty("chapter")
    private String chapter;

    @JsonProperty("count")
    private String count;

    @JsonProperty("score")
    private String score;

    @JsonProperty("time")
    private String time;
}
