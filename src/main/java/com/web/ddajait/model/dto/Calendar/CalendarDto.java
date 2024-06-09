package com.web.ddajait.model.dto.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDto {
    
    private String title;

    private String start;
    
    private String end;

    private List<ExtendedProps> extendedProps;
    
}
