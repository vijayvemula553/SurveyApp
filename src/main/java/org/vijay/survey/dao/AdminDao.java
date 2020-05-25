package org.vijay.survey.dao;

import java.util.List;

import org.vijay.survey.entity.RoleMaster;
import org.vijay.survey.entity.SurveyData;
import org.vijay.survey.entity.User;
import org.vijay.survey.pojo.AdminUserSearchRequest;
import org.vijay.survey.pojo.SearchSurveyRequest;

public interface AdminDao {

	public List<User> loadUsersByRole(
			AdminUserSearchRequest adminUserSearchRequest);

	public void saveSurveyDetails(SurveyData surveyData);

	public void saveUserDetails(User user);

	public List<RoleMaster> fetchRoles();

	public boolean validateSuperAdmin(String userName, Long roleId);

	public List<SurveyData> fetchSurveyDataFilterBased(
			SearchSurveyRequest searchSurveyRequest);

	public RoleMaster fetchRoles(String roleName);

	public int fetchSurveyDataCount(SearchSurveyRequest searchSurveyRequest);

	public List<SurveyData> fetchSurveyDataFilterBasedPaginated(
			SearchSurveyRequest searchSurveyRequest);

	public int loadUsersByRoleName(String userName, Long roleId);

	public void deleteUser(List<Long> ids);

	public int fetchUserDataCount(AdminUserSearchRequest adminUserSearchRequest);

}
