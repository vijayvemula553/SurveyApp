package org.vijay.survey.pojo;

import java.util.List;

public class SearchUserResponseWrapper {
	private List<UserPojo> userPojoList;

	private int count;

	public List<UserPojo> getUserPojoList() {
		return userPojoList;
	}

	public void setUserPojoList(List<UserPojo> userPojoList) {
		this.userPojoList = userPojoList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
