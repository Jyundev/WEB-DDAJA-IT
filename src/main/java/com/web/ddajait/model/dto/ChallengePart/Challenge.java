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
public class Challenge {
    String name;
    String start;
    String end;
    String test_date;
    int my_progress;
    int total_progress;
    int total_user;
    List<Step> steps;
}
