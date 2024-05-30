package com.web.ddajait.model.dto.CertificateInfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Eligibility {
    private String type;
    private List<String> condition;
}
