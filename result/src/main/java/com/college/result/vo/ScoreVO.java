package com.college.result.vo;

import lombok.Data;

@Data
public class ScoreVO {
    
    private Long id;
    private Long studentId;
    private Long courseId;
    private Integer marks;

}
