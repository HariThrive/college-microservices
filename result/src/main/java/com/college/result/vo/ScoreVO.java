package com.college.result.vo;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class ScoreVO {
    private Long studentId;
    private Long courseId;
    private Integer marks;

}
