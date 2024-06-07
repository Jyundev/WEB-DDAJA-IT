package com.web.ddajait.model.dto.ChallengePart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Certificate {
    private String name;
    private String start_day;
    private String end_day;
    private String test_day;
    private int my_progress;
    private int total_progress;
    private int total_user;
    private List<Step> steps;


}
