package com.web.ddajait.model.dto.UserChallenge;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Memo {
    @JsonProperty("day")
    private int day;

    @JsonProperty("memo")
    private String memo;

}