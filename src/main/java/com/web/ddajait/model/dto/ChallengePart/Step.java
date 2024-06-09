package com.web.ddajait.model.dto.ChallengePart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    int step;
    boolean complete;
    String partName;
    String chapterName;
    String sectionName;
    List<TestQuestion> test;
    List<Day> days;
}