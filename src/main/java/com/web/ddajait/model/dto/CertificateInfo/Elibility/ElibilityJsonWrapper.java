package com.web.ddajait.model.dto.CertificateInfo.Elibility;

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
public class ElibilityJsonWrapper {
    @JsonProperty("standard")
    private List<Standard> standard;

    @JsonProperty("Simplestandard")
    private String Simplestandard;

}

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
class Standard {
    @JsonProperty("qualification")
    private String qualification;

    @JsonProperty("condition")
    private List<String> condition;
}