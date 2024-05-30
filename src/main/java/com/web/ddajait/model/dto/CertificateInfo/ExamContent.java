package com.web.ddajait.model.dto.CertificateInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamContent {
    private String type;
    private String subject;
    private int count;
    private String score;
    private String exam_time;
}
