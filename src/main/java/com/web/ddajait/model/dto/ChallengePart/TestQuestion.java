package com.web.ddajait.model.dto.ChallengePart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestion {
    Long testId;
    int num;
    String question;
    String item1;
    String item2;
    String item3;
    String item4;
    int answer;
}
