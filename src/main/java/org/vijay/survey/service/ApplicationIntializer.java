package org.vijay.survey.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.vijay.survey.pojo.RoleMasterPojo;

@Named(value = "applicationIntializer")
public class ApplicationIntializer {

	@Inject
	@Named(value = "adminServiceImpl")
	private AdminService adminServiceImpl;

	public static Map<String, Long> roleNameIdMap = new HashMap<String, Long>();

	public static Map<Long, String> roleIdNameMap = new HashMap<Long, String>();

	public static List<RoleMasterPojo> roleMasterPojoList;

	@PostConstruct
	public void loadDate() {

		// Fetch all roles
		roleMasterPojoList = adminServiceImpl.fetchRoles();

		for (RoleMasterPojo roleMasterPojo : roleMasterPojoList) {
			roleNameIdMap.put(roleMasterPojo.getRoleName(),
					roleMasterPojo.getId());
			
			roleIdNameMap.put(roleMasterPojo.getId(),
					roleMasterPojo.getRoleName());
		}

	}
}
