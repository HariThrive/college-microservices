package com.college.result.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScoreController {
	
	@GetMapping()
	public ModelAndView getScoure() {
		ModelAndView mv = new ModelAndView("score");
		
		return mv;
	}
}
