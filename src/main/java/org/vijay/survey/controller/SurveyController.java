package org.vijay.survey.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.vijay.survey.pojo.Response;
import org.vijay.survey.pojo.SurveyPojo;
import org.vijay.survey.service.AdminService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

	@Inject
	@Named(value = "adminServiceImpl")
	private AdminService adminServiceImpl;

	@RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
	public Response addSurvey(@RequestBody SurveyPojo surveyPojo) {

		Response response = null;

		try {
			adminServiceImpl.saveSurveyDetails(surveyPojo);
			response = new Response("message", "Success");
		} catch (Exception e) {
			response = new Response("message", "Error");
		}

		return response;
	}
}
