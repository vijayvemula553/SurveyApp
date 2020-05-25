package org.vijay.survey.pojo;

public class SurveyPojo {

	private String issue;

	private String servicerating;

	private String servicetimetating;

	private String feedback;

	private String optional;

	private String username;

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Issue = " + this.issue + " , servicerating = " + this.servicerating + " , servicetimetating = "
				+ this.servicetimetating + " , Feedback = " + this.feedback + " , optional = " + this.optional
				+ ", username = " + username;

	}
}
