package com.web.ddajait.model.dto.CertificateInfo.ExamStandard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonWrapper {
    @JsonProperty("subject")
    private List<Chapter> subject;

    @JsonProperty("subject")
    private String simpleSubject;

    @JsonProperty("link")
    private String link;

}

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
class Chapter {

    @JsonProperty("chapter")
    private String chapter;

    @JsonProperty("unit")
    private List<Unit> unit;

}

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
class Unit {

    @JsonProperty("name")
    private String name;

    @JsonProperty("detail")
    private List<String> detail;

}