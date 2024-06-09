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
    int id;
    String question;
    String item1;
    String item2;
    String item3;
    String item4;
    int answer;
}
