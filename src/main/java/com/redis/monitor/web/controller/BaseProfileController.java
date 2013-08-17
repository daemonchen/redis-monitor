package com.redis.monitor.web.controller;

import org.springframework.web.servlet.ModelAndView;

import com.redis.monitor.Constants;


public class BaseProfileController extends AbstractController {
	
	protected ModelAndView getJsonModelAndView() {
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	protected ModelAndView putModel(Object data) {
		ModelAndView mv = getJsonModelAndView();
		mv.addObject("data", data);
		return mv;
	}
}
