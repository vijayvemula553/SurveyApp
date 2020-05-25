package org.vijay.survey.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.vijay.survey.dao.AdminDao;
import org.vijay.survey.entity.RoleMaster;
import org.vijay.survey.entity.SurveyData;
import org.vijay.survey.entity.User;
import org.vijay.survey.pojo.AdminUserSearchRequest;
import org.vijay.survey.pojo.RoleMasterPojo;
import org.vijay.survey.pojo.SearchSurveyRequest;
import org.vijay.survey.pojo.SearchSurveyResponse;
import org.vijay.survey.pojo.SurveyPojo;
import org.vijay.survey.pojo.UserPojo;
import org.vijay.survey.service.AdminService;
import org.springframework.util.CollectionUtils;

@Named(value = "adminServiceImpl")
public class AdminServiceImpl implements AdminService {

	@Inject
	@Named(value = "adminDaoImpl")
	private AdminDao adminDaoImpl;

	@Inject
	@Named(value = "applicationIntializer")
	private ApplicationIntializer applicationIntializer;

	@Override
	public List<UserPojo> loadUsersByRole(
			AdminUserSearchRequest adminUserSearchRequest) {
		List<UserPojo> userPojoList = new ArrayList<UserPojo>();
		List<User> userList = adminDaoImpl
				.loadUsersByRole(adminUserSearchRequest);

		if (!CollectionUtils.isEmpty(userList)) {
			for (User user : userList) {
				UserPojo userPojo = new UserPojo();
				userPojo.setActive(user.getActive());
				userPojo.setRoleId(user.getRoleId());
				userPojo.setUserName(user.getUserName());
				userPojo.setId(user.getId());
				userPojo.setRoleName(applicationIntializer.roleIdNameMap
						.get(user.getRoleId()));

				userPojoList.add(userPojo);
			}
		}
		return userPojoList;
	}

	@Override
	public void saveSurveyDetails(SurveyPojo surveyPojo) {
		SurveyData surveyData = new SurveyData();

		surveyData.setFeedback(surveyPojo.getFeedback());
		surveyData.setIssue(surveyPojo.getIssue());
		surveyData.setOptional(surveyPojo.getOptional());
		surveyData.setServicerating(surveyPojo.getServicerating());
		surveyData.setServicetimetating(surveyPojo.getServicetimetating());
		surveyData.setUsername(surveyPojo.getUsername());

		adminDaoImpl.saveSurveyDetails(surveyData);
	}

	@Override
	public void saveUserDetails(UserPojo userPojo) {
		User user = new User();
		user.setRoleId(userPojo.getRoleId());
		user.setUserName(userPojo.getUserName());
		user.setActive(userPojo.getActive());

		adminDaoImpl.saveUserDetails(user);

	}

	@Override
	public List<RoleMasterPojo> fetchRoles() {
		List<RoleMaster> roleMasterList = adminDaoImpl.fetchRoles();

		List<RoleMasterPojo> roleMasterPojoList = new ArrayList<RoleMasterPojo>();

		if (!CollectionUtils.isEmpty(roleMasterList)) {
			for (RoleMaster rm : roleMasterList) {

				RoleMasterPojo roleMasterPojo = new RoleMasterPojo();
				roleMasterPojo.setId(rm.getId());
				roleMasterPojo.setRoleName(rm.getRoleName());

				roleMasterPojoList.add(roleMasterPojo);
			}
		}

		return roleMasterPojoList;
	}

	@Override
	public boolean validateSuperAdmin(UserPojo userPojo) {
		return adminDaoImpl.validateSuperAdmin(userPojo.getUserName(),
				userPojo.getRoleId());
	}

	@Override
	public List<SearchSurveyResponse> fetchSurveyDataFilterBased(
			SearchSurveyRequest searchSurveyRequest) {
		List<SearchSurveyResponse> searchSurveyResponses = new ArrayList<SearchSurveyResponse>();

		List<SurveyData> surveyDataList = adminDaoImpl
				.fetchSurveyDataFilterBased(searchSurveyRequest);
		if (!(CollectionUtils.isEmpty(surveyDataList))) {
			for (SurveyData surveyData : surveyDataList) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = surveyData.getCreated();

				SearchSurveyResponse searchSurveyResponse = new SearchSurveyResponse(
						surveyData.getUsername(), surveyData.getIssue(),
						surveyData.getServicerating(),
						surveyData.getServicetimetating(),
						surveyData.getFeedback(), surveyData.getOptional(),
						df.format(date));

				searchSurveyResponses.add(searchSurveyResponse);
			}
		}
		return searchSurveyResponses;
	}

	@Override
	public List<SearchSurveyResponse> fetchSurveyDataFilterBasedPaginated(
			SearchSurveyRequest searchSurveyRequest) {
		List<SearchSurveyResponse> searchSurveyResponses = new ArrayList<SearchSurveyResponse>();

		List<SurveyData> surveyDataList = adminDaoImpl
				.fetchSurveyDataFilterBasedPaginated(searchSurveyRequest);
		if (!(CollectionUtils.isEmpty(surveyDataList))) {
			for (SurveyData surveyData : surveyDataList) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date = surveyData.getCreated();

				SearchSurveyResponse searchSurveyResponse = new SearchSurveyResponse(
						surveyData.getUsername(), surveyData.getIssue(),
						surveyData.getServicerating(),
						surveyData.getServicetimetating(),
						surveyData.getFeedback(), surveyData.getOptional(),
						df.format(date));

				searchSurveyResponses.add(searchSurveyResponse);
			}
		}
		return searchSurveyResponses;
	}

	@Override
	public int fetchSurveyDataCount(SearchSurveyRequest searchSurveyRequest) {
		return adminDaoImpl.fetchSurveyDataCount(searchSurveyRequest);
	}

	@Override
	public RoleMasterPojo fetchRoles(String roleName) {
		RoleMaster roleMaster = adminDaoImpl.fetchRoles(roleName);

		RoleMasterPojo roleMasterPojo = new RoleMasterPojo();
		roleMasterPojo.setId(roleMaster.getId());
		roleMasterPojo.setRoleName(roleMaster.getRoleName());

		return roleMasterPojo;
	}

	@Override
	public boolean loadUsersByRoleName(String userName, Long roleId) {
		int count = adminDaoImpl.loadUsersByRoleName(userName, roleId);
		if (count == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteUser(List<Long> ids) {
		adminDaoImpl.deleteUser(ids);
	}

	@Override
	public int fetchUserDataCount(AdminUserSearchRequest adminUserSearchRequest) {
		return adminDaoImpl.fetchUserDataCount(adminUserSearchRequest);
	}
}
