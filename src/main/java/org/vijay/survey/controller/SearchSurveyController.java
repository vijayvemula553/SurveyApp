package org.vijay.survey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.vijay.survey.pojo.PieChartPojo;
import org.vijay.survey.pojo.PieWrapperPojo;
import org.vijay.survey.pojo.SearchSurveyRequest;
import org.vijay.survey.pojo.SearchSurveyResponse;
import org.vijay.survey.pojo.SearchSurveyResponseWrapper;
import org.vijay.survey.service.AdminService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSurveyController {

	@Inject
	@Named(value = "adminServiceImpl")
	private AdminService adminServiceImpl;

	@RequestMapping(value = "/searchSurvey", method = RequestMethod.POST)
	public SearchSurveyResponseWrapper addSurvey(@RequestBody SearchSurveyRequest searchSurveyRequest) {

		Map<String, Integer> serviceRatingCountMap = new TreeMap<String, Integer>();
		serviceRatingCountMap.put("1", 0);
		serviceRatingCountMap.put("2", 0);
		serviceRatingCountMap.put("3", 0);
		serviceRatingCountMap.put("4", 0);

		Map<String, Integer> serviceTimeRatingCountMap = new TreeMap<String, Integer>();
		serviceTimeRatingCountMap.put("1", 0);
		serviceTimeRatingCountMap.put("2", 0);
		serviceTimeRatingCountMap.put("3", 0);
		serviceTimeRatingCountMap.put("4", 0);

		SearchSurveyResponseWrapper searchSurveyResponseWrapper = new SearchSurveyResponseWrapper();

		List<SearchSurveyResponse> searchSurveyResponses = adminServiceImpl
				.fetchSurveyDataFilterBased(searchSurveyRequest);

		List<SearchSurveyResponse> searchSurveyResponsesPaginated = adminServiceImpl
				.fetchSurveyDataFilterBasedPaginated(searchSurveyRequest);

		int totalCount = adminServiceImpl.fetchSurveyDataCount(searchSurveyRequest);
		searchSurveyResponseWrapper.setCount(totalCount);

		searchSurveyResponseWrapper.setSearchSurveyResponseList(searchSurveyResponsesPaginated);

		if (!(CollectionUtils.isEmpty(searchSurveyResponses))) {
			for (SearchSurveyResponse searchSurveyResponse : searchSurveyResponses) {

				int serviceRatingCount = 0;
				int serviceTimeRatingCount = 0;

				if (serviceRatingCountMap.containsKey(searchSurveyResponse.getServicerating())) {
					serviceRatingCount = serviceRatingCountMap.get(searchSurveyResponse.getServicerating());
					serviceRatingCount = serviceRatingCount + 1;
				} else {
					serviceRatingCount = 1;
				}

				serviceRatingCountMap.put(searchSurveyResponse.getServicerating(), serviceRatingCount);

				if (serviceTimeRatingCountMap.containsKey(searchSurveyResponse.getServicetimetating())) {
					serviceTimeRatingCount = serviceTimeRatingCountMap.get(searchSurveyResponse.getServicetimetating());
					serviceTimeRatingCount = serviceTimeRatingCount + 1;
				} else {
					serviceTimeRatingCount = 1;
				}

				serviceTimeRatingCountMap.put(searchSurveyResponse.getServicetimetating(), serviceTimeRatingCount);
			}
		}

		convertToPieChartDataSet(serviceRatingCountMap, searchSurveyResponseWrapper, true);

		convertToPieChartDataSet(serviceTimeRatingCountMap, searchSurveyResponseWrapper, false);

		return searchSurveyResponseWrapper;
	}

	@RequestMapping(value = "/searchSurveyPieClick", method = RequestMethod.POST)
	public SearchSurveyResponseWrapper searchSurveyPieClick(@RequestBody SearchSurveyRequest searchSurveyRequest) {

		SearchSurveyResponseWrapper searchSurveyResponseWrapper = new SearchSurveyResponseWrapper();

		List<SearchSurveyResponse> searchSurveyResponses = adminServiceImpl
				.fetchSurveyDataFilterBasedPaginated(searchSurveyRequest);
		int totalCount = adminServiceImpl.fetchSurveyDataCount(searchSurveyRequest);
		searchSurveyResponseWrapper.setCount(totalCount);

		searchSurveyResponseWrapper.setSearchSurveyResponseList(searchSurveyResponses);

		return searchSurveyResponseWrapper;
	}

	private void convertToPieChartDataSet(Map<String, Integer> serviceRatingCountMap,
			SearchSurveyResponseWrapper searchSurveyResponseWrapper, boolean isServiceRating) {
		PieWrapperPojo pieWrapper = new PieWrapperPojo();
		List<PieChartPojo> pieChartPojos = new ArrayList<PieChartPojo>();

		for (Entry<String, Integer> entrySet : serviceRatingCountMap.entrySet()) {
			PieChartPojo chartPojo = new PieChartPojo();

			if (entrySet.getKey().equalsIgnoreCase("1")) {
				chartPojo.setV("Worse");
			} else if (entrySet.getKey().equalsIgnoreCase("2")) {
				chartPojo.setV("Bad");
			} else if (entrySet.getKey().equalsIgnoreCase("3")) {
				chartPojo.setV("Good");
			} else if (entrySet.getKey().equalsIgnoreCase("4")) {
				chartPojo.setV("VGood");
			}

			pieChartPojos.add(chartPojo);

			PieChartPojo chartPojo1 = new PieChartPojo();
			chartPojo1.setV(entrySet.getValue());
			pieChartPojos.add(chartPojo1);

		}

		pieWrapper.setC(pieChartPojos);

		if (isServiceRating) {
			searchSurveyResponseWrapper.setServiceRating(pieWrapper);
		} else {
			searchSurveyResponseWrapper.setServiceTimeRating(pieWrapper);
		}
	}

}
