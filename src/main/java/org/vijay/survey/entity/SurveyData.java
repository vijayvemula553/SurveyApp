package org.vijay.survey.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "survey_data")
public class SurveyData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String issue;

	private String servicerating;

	private String servicetimetating;

	@Column(length = 1000)
	private String feedback;

	private String optional;

	private String username;

	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@PrePersist
	protected void onCreate() {
		setCreated(new Date());
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
