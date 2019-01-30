package com.internousdev.gerbera.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.gerbera.dao.MCategoryDAO;
import com.internousdev.gerbera.dto.MCategoryDTO;
import com.internousdev.gerbera.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	public String execute() {

		if (!(session.containsKey("loginId")) && !(session.containsKey("tempUserId"))) {
			CommonUtility common = new CommonUtility();
			session.put("tempUserId", common.getRamdomValue());
		}

		List<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();

		if (!session.containsKey("logined")) {
			session.put("logined", 0);
		}

		if (!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryList = mCategoryDao.getMCategoryList();
			session.put("mCategoryList", mCategoryList);
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}