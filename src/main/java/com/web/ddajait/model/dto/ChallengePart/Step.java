package com.web.ddajait.model.dto.ChallengePart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    int step;
    boolean complete;
    String partName;
    List<Day> days;
}