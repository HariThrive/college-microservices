package com.college.result.controller;

import com.college.result.entity.Score;
import com.college.result.repository.ScoreRepository;
import com.college.result.vo.ScoreSubmitRequestVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;
	
	@GetMapping
	public ModelAndView getScoure() {
		ModelAndView mv = new ModelAndView("score");
		return mv;
	}

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<String> saveScores(@RequestBody ScoreSubmitRequestVO request) {
        if (request.getStudentId() == null || request.getScores() == null) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

        List<Score> scores = request.getScores().stream().map(dto -> {
            Score score = new Score();
            score.setStudentId(request.getStudentId());
            score.setCourseId(dto.getCourseId());
            score.setMarks(dto.getMarks());
            return score;
        }).collect(Collectors.toList());

        scoreRepository.saveAll(scores);

        return ResponseEntity.ok("Scores saved successfully");
    }
}
