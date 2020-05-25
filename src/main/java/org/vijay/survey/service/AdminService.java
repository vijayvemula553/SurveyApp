package org.vijay.survey.service;

import java.util.List;

import org.vijay.survey.pojo.AdminUserSearchRequest;
import org.vijay.survey.pojo.RoleMasterPojo;
import org.vijay.survey.pojo.SearchSurveyRequest;
import org.vijay.survey.pojo.SearchSurveyResponse;
import org.vijay.survey.pojo.SurveyPojo;
import org.vijay.survey.pojo.UserPojo;

public interface AdminService {

	public List<UserPojo> loadUsersByRole(
			AdminUserSearchRequest adminUserSearchRequest);

	public boolean loadUsersByRoleName(String userName, Long roleId);

	public void deleteUser(List<Long> ids);

	public void saveSurveyDetails(SurveyPojo surveyPojo);

	public void saveUserDetails(UserPojo userPojo);

	public List<RoleMasterPojo> fetchRoles();

	public boolean validateSuperAdmin(UserPojo userPojo);

	public List<SearchSurveyResponse> fetchSurveyDataFilterBased(
			SearchSurveyRequest searchSurveyRequest);

	public RoleMasterPojo fetchRoles(String roleName);

	public int fetchSurveyDataCount(SearchSurveyRequest searchSurveyRequest);

	public List<SearchSurveyResponse> fetchSurveyDataFilterBasedPaginated(
			SearchSurveyRequest searchSurveyRequest);

	public int fetchUserDataCount(AdminUserSearchRequest adminUserSearchRequest);

}
