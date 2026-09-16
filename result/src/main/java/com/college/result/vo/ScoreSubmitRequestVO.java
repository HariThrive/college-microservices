package com.college.result.vo;

import java.util.List;

import lombok.Data;

@Data
public class ScoreSubmitRequestVO {
    private Long studentId;
    private List<ScoreVO> scores;

}
