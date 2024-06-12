package com.web.ddajait.model.dto.ChallengePart;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Day {
    int day;
    Map<String, List<String>> chapterMap;
    boolean complete;
    String memo;
    List<TestQuestion> test;

}
