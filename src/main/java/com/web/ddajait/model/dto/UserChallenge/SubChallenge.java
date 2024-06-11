package com.web.ddajait.model.dto.UserChallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubChallenge {

    private int step = 1;

    private int day = 1;

    private String memo = "";

}
