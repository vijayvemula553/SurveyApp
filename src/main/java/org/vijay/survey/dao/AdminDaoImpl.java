package org.vijay.survey.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import org.vijay.survey.entity.RoleMaster;
import org.vijay.survey.entity.SurveyData;
import org.vijay.survey.entity.User;
import org.vijay.survey.pojo.AdminUserSearchRequest;
import org.vijay.survey.pojo.SearchSurveyRequest;
import org.vijay.survey.utils.DateHelper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Named(value = "adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

	@Override
	public List<User> loadUsersByRole(
			AdminUserSearchRequest adminUserSearchRequest) {

		String queryStr = "SELECT u FROM User u where u.roleId = "
				+ adminUserSearchRequest.getRoleId() + " AND u.active = true ";

		if (!StringUtils.isEmpty(adminUserSearchRequest.getUserName())) {
			queryStr = queryStr + " AND u.userName like '%"
					+ adminUserSearchRequest.getUserName() + "%' ";
		}

		// order by
		queryStr = queryStr + " order by u.userName asc";

		Query query = em.createQuery(queryStr);

		int firstResult = 0;
		if (adminUserSearchRequest.getPage() == 1) {
			firstResult = 0;
		} else {
			firstResult = (adminUserSearchRequest.getPage() * adminUserSearchRequest
					.getPageSize()) - adminUserSearchRequest.getPageSize();
		}

		query.setFirstResult(firstResult);
		query.setMaxResults(adminUserSearchRequest.getPageSize());

		List<User> userDataList = query.getResultList();

		return userDataList;
	}

	@Override
	public void saveSurveyDetails(SurveyData surveyData) {
		em.persist(surveyData);
	}

	@Override
	public void saveUserDetails(User user) {
		em.persist(user);

	}

	@Override
	public List<RoleMaster> fetchRoles() {
		Query query = em.createQuery("SELECT rm FROM RoleMaster rm");
		return (List<RoleMaster>) query.getResultList();
	}

	@Override
	public boolean validateSuperAdmin(String userName, Long roleId) {
		Query query = em
				.createQuery("SELECT u FROM User u where u.userName ='"
						+ userName + "'"
						+ " AND u.roleId = roleId AND u.active = true");

		List<User> userList = query.getResultList();

		if (!(CollectionUtils.isEmpty(userList)) && userList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<SurveyData> fetchSurveyDataFilterBased(
			SearchSurveyRequest searchSurveyRequest) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date frmDate = null;
		Date enDate = null;
		try {
			frmDate = format.parse(searchSurveyRequest.getFromDate());
			enDate = format.parse(searchSurveyRequest.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String frmDateTemp = DateHelper.getFormattedFromDateTime(frmDate);
		String endDateTemp = DateHelper.getFormattedToDateTime(enDate);

		String queryStr = "SELECT sd FROM SurveyData sd where sd.created BETWEEN '"
				+ frmDateTemp + "' AND '" + endDateTemp + "'";

		if (!(StringUtils.isEmpty(searchSurveyRequest.getServicerating()))) {
			queryStr = queryStr + " AND sd.servicerating = '"
					+ searchSurveyRequest.getServicerating() + "'";
		}

		if (!(StringUtils.isEmpty(searchSurveyRequest.getServicetimetating()))) {
			queryStr = queryStr + " AND sd.servicetimetating = '"
					+ searchSurveyRequest.getServicetimetating() + "'";
		}
		// order by
		queryStr = queryStr + " order by sd.created desc";

		Query query = em.createQuery(queryStr);

		List<SurveyData> surveyDataList = query.getResultList();
		return surveyDataList;
	}

	@Override
	public List<SurveyData> fetchSurveyDataFilterBasedPaginated(
			SearchSurveyRequest searchSurveyRequest) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date frmDate = null;
		Date enDate = null;
		try {
			frmDate = format.parse(searchSurveyRequest.getFromDate());
			enDate = format.parse(searchSurveyRequest.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String frmDateTemp = DateHelper.getFormattedFromDateTime(frmDate);
		String endDateTemp = DateHelper.getFormattedToDateTime(enDate);

		String queryStr = "SELECT sd FROM SurveyData sd where sd.created BETWEEN '"
				+ frmDateTemp + "' AND '" + endDateTemp + "'";

		if (!(StringUtils.isEmpty(searchSurveyRequest.getServicerating()))) {
			queryStr = queryStr + " AND sd.servicerating = '"
					+ searchSurveyRequest.getServicerating() + "'";
		}

		if (!(StringUtils.isEmpty(searchSurveyRequest.getServicetimetating()))) {
			queryStr = queryStr + " AND sd.servicetimetating = '"
					+ searchSurveyRequest.getServicetimetating() + "'";
		}
		// order by
		queryStr = queryStr + " order by sd.created desc";

		Query query = em.createQuery(queryStr);

		int firstResult = 0;
		if (searchSurveyRequest.getPage() == 1) {
			firstResult = 0;
		} else {
			firstResult = (searchSurveyRequest.getPage() * searchSurveyRequest
					.getPageSize()) - searchSurveyRequest.getPageSize();
		}

		query.setFirstResult(firstResult);
		query.setMaxResults(searchSurveyRequest.getPageSize());

		List<SurveyData> surveyDataList = query.getResultList();
		return surveyDataList;
	}

	@Override
	public int fetchSurveyDataCount(SearchSurveyRequest searchSurveyRequest) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date frmDate = null;
		Date enDate = null;
		try {
			frmDate = format.parse(searchSurveyRequest.getFromDate());
			enDate = format.parse(searchSurveyRequest.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String frmDateTemp = DateHelper.getFormattedFromDateTime(frmDate);
		String endDateTemp = DateHelper.getFormattedToDateTime(enDate);

		String queryStr = "SELECT count(sd) FROM SurveyData sd where sd.created BETWEEN '"
				+ frmDateTemp + "' AND '" + endDateTemp + "'";

		if (!(StringUtils.isEmpty(searchSurveyRequest.getServicerating()))) {
			queryStr = queryStr + " AND sd.servicerating = '"
					+ searchSurveyRequest.getServicerating() + "'";
		}

		if (!(StringUtils.isEmpty(searchSurveyRequest.getServicetimetating()))) {
			queryStr = queryStr + " AND sd.servicetimetating = '"
					+ searchSurveyRequest.getServicetimetating() + "'";
		}

		Query query = em.createQuery(queryStr);

		int count = ((Long) query.getSingleResult()).intValue();
		return count;
	}

	@Override
	public RoleMaster fetchRoles(String roleName) {
		Query query = em
				.createQuery("SELECT rm FROM RoleMaster rm where rm.roleName = '"
						+ roleName + "'");
		return (RoleMaster) query.getSingleResult();
	}

	@Override
	public int loadUsersByRoleName(String userName, Long roleId) {
		Query query = em
				.createQuery("SELECT u FROM User u where u.userName = '"
						+ userName + "' AND u.roleId = " + roleId
						+ "AND u.active = true");
		return query.getResultList().size();
	}

	@Override
	public void deleteUser(List<Long> ids) {
		Query query = em
				.createQuery("UPDATE User u set u.active = false where u.id IN (:ids)");
		query.setParameter("ids", ids);
		query.executeUpdate();

	}

	@Override
	public int fetchUserDataCount(AdminUserSearchRequest adminUserSearchRequest) {
		String queryStr = "SELECT count(u) FROM User u where u.roleId = "
				+ adminUserSearchRequest.getRoleId() + " AND u.active = true ";

		if (!StringUtils.isEmpty(adminUserSearchRequest.getUserName())) {
			queryStr = queryStr + " AND u.userName like '%"
					+ adminUserSearchRequest.getUserName() + "%' ";
		}

		Query query = em.createQuery(queryStr);
		int count = ((Long) query.getSingleResult()).intValue();
		return count;
	}
}
