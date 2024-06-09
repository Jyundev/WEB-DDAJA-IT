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
public class StandardDto {
    private String qualification;
    private List<String> condition;
}
