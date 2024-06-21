package com.web.ddajait.model.dto.Calendar;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDto {
    
    private Long certificateId;

    private String title;

    private String startDay;
    
    private String endDay;    

    private List<ExtendedProps> extendedProps;
    
}
