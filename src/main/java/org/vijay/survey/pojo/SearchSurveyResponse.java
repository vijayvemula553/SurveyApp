package org.vijay.survey.pojo;

public class SearchSurveyResponse {

	private String user;

	private String issueType;

	private String servicerating;

	private String servicetimetating;

	private String feedback;

	private String optional;

	private String createdDate;

	public SearchSurveyResponse(String user, String issueType, String servicerating, String servicetimetating,
			String feedback, String optional, String date) {
		super();
		this.user = user;
		this.issueType = issueType;
		this.servicerating = servicerating;
		this.servicetimetating = servicetimetating;
		this.feedback = feedback;
		this.optional = optional;
		this.setCreatedDate(date);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getServicerating() {
		return servicerating;
	}

	public void setServicerating(String servicerating) {
		this.servicerating = servicerating;
	}

	public String getServicetimetating() {
		return servicetimetating;
	}

	public void setServicetimetating(String servicetimetating) {
		this.servicetimetating = servicetimetating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
