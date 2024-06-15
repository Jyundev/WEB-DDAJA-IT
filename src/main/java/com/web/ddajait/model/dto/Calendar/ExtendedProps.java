package com.web.ddajait.model.dto.Calendar;

import java.util.List;

import com.web.ddajait.model.dto.CertificateInfo.Elibility.ElibilityStandard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedProps {

    private String round;

    private String type;

    private String testDay;

    private String overView;

    private List<ElibilityStandard> standards;

}
