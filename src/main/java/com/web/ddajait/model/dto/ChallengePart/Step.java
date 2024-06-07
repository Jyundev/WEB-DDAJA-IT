package com.web.ddajait.model.dto.ChallengePart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Step {
    private int step;
    private boolean complete;
    private List<Day> days;
}
