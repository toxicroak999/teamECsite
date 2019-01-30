package com.internousdev.gerbera.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//文字列がnullの場合でもNullPointerExceptionの例外を発生させない
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.gerbera.dao.ProductInfoDAO;
import com.internousdev.gerbera.dto.ProductInfoDTO;
import com.internousdev.gerbera.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String keywords;
	private List<String> keywordsErrorMessageList = new ArrayList<String>();
	private Map<String, Object> session;
	public String execute(){
		if(!session.containsKey("mCategoryList")) {
			return "sessionTimeOut";
		}
		String result = ERROR;
		InputChecker inputChecker = new InputChecker();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		String tempkeywords=null;

		if(StringUtils.isBlank(keywords)){
			tempkeywords="";
		}else{
			//replaceAllで（）内の先の文字を後ろの文字に置換する。
			//一個目の\で2個目の\を表記
			//\sで半角スペース、タブ、改行のどれか1文字
			//{2,}で直前の文字の2以上の繰り返し
			tempkeywords = keywords.replaceAll("　", " ").replaceAll("\\s{2,}", " ");
			tempkeywords = tempkeywords.trim();
		}
		if(!(tempkeywords.equals(""))){
			/*半角英字、漢字、ひらがな、半角数字、カタカナ、半角スペースを許容
			（上で全角スペースは半角スペースで変換済み)*/
			keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords, 0, 16, true, true, true, true, false, true, false, true, true);
			Iterator<String> iterator = keywordsErrorMessageList.iterator();
			if(iterator.hasNext()){
				return SUCCESS;
			}
		}

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		if (categoryId == null) {
			categoryId = "1";
		}

		switch(categoryId){
			case "1":
				productInfoDtoList = productInfoDAO.getProductInfoListAll(tempkeywords.split(" "));
				result = SUCCESS;
				break;

			default:
				productInfoDtoList = productInfoDAO.getProductInfoListByKeywords(tempkeywords.split(" "), categoryId);
				result = SUCCESS;
				break;
		}
		Iterator<ProductInfoDTO> iterator= productInfoDtoList.iterator();
		if(!(iterator.hasNext())){
			productInfoDtoList = null;
		}
		session.put("productInfoDtoList", productInfoDtoList);
		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public List<String> getKeywordsErrorMessageList() {
		return keywordsErrorMessageList;
	}
	public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList) {
		this.keywordsErrorMessageList = keywordsErrorMessageList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
