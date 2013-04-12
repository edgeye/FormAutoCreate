package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.cdc.util.BaseUtil;

public class ServiceCreator {
	
	/**
	 * 组装service
	 * @param type 类型：service或者impl
	 * @return
	 */
	public static String assembleService(String type) {
		String title1 = BaseUtil.getTitle();
		String title2 = BaseUtil.upCase(title1);
		StringBuffer sb = new StringBuffer();
		String space1 = BaseUtil.createSpaces(4);
		String space2 = BaseUtil.createSpaces(8);
		String space3 = BaseUtil.createSpaces(12);
		
		if(type.equals("impl")) {
			sb.append("package com.gdcn.cms.wsbs." + title1 + ".service.impl;" + "\n");
		}else {
			sb.append("package com.gdcn.cms.wsbs." + title1 + ".service;" + "\n");
		}
		sb.append("\n");
		
		if(type.equals("impl")) {
			sb.append("import com.gdcn.bpaf.common.base.BaseServiceImpl;" + "\n");
			sb.append("import com.gdcn.bpaf.common.helper.Pagelet;" + "\n");
			sb.append("import com.gdcn.cms.wsbs." + title1 + ".dao.I" + title2 + "DAO;" + "\n");
			sb.append("import com.gdcn.cms.wsbs." + title1 + ".service.I" + title2 + "Service;" + "\n");
		}
		sb.append("import com.gdcn.cms.wsbs." + title1 + ".model." + title2 + ";" + "\n");
		sb.append("import com.gdcn.cms.wsbs." + title1 + ".web.form." + title2 + "Form;" + "\n");
		sb.append("/**" + "\n"
				+ "* " + BaseUtil.getHead() + "\n" 
				+ "* @author yaoguoc" + "\n"
				+ "*" + "\n"
				+ "*/" + "\n");
		
		if(type.equals("impl")) {
			sb.append("public class " + title2 + "ServiceImpl extends BaseServiceImpl implements I" + title2 + "Service{" + "\n");
		}else {
			sb.append("public interface I" + title2 + "Service {" + "\n");
		}
		sb.append("\n");
		
		if(type.equals("impl")) {
			sb.append(space1 + "private I" + title2 + "DAO " + title1 + "DAO;" + "\n");
			sb.append("\n");
			
			//getXgsyryjyDAO
			sb.append(space1 + "public I" + title2 + "DAO get" + title2 + "DAO() {" + "\n"
					+ space2 + "return " + title1 + "DAO;" + "\n"
					+ space1 + "}" + "\n");
			sb.append("\n");
			
			//setXgsyryjyDAO
			sb.append(space1 + "public void set" + title2 + "DAO(I" + title2 + "DAO " + title1 + "DAO) {" + "\n"
					+ space2 + "this." + title1 + "DAO = " + title1 + "DAO;" + "\n"
					+ space1 + "}" + "\n");
			sb.append("\n");
			
			//createEntity
			sb.append(space1 + "@Override" + "\n"
					+ space1 + "public String createEntity(" + title2 + " entity) {" + "\n"
					+ space2 + "return " + title1 + "DAO.createEntity(entity);" + "\n"
					+ space1 + "}" + "\n");
			sb.append("\n");
			
			//listAll
			sb.append(space1 + "@Override" + "\n"
					+ space1 + "public Pagelet listAll(" + title2 + "Form " + title1 + "Form, int pageIndex," + "\n"
					+ space3 + "int pageSize) {" + "\n"
					+ space2 + "java.util.Map<Object, Object> propMap=new java.util.HashMap<Object, Object>();" + "\n"
					+ space2 + "java.util.Map<Object, Object> orderMap=new java.util.HashMap<Object, Object>();" + "\n"
					+ space2 + "orderMap.put(\"createTime\", \"DESC\");" + "\n"
					+ space2 + "return " + title1 + "DAO.listAll(propMap, orderMap, pageIndex, pageSize);" + "\n"
					+ space1 + "}" + "\n");
			sb.append("\n");
			
			//getEntity
			sb.append(space1 + "@Override" + "\n"
					+ space1 + "public " + title2 + " getEntity(String resourceId) {" + "\n"
					+ space2 + "return " + title1 + "DAO.getEntity(resourceId);" + "\n"
					+ space1 + "}" + "\n");
			sb.append("\n");
			
			//updateEntity
			sb.append(space1 + "@Override" + "\n"
					+ space1 + "public void updateEntity(" + title2 + " entity) {" + "\n"
					+ space2 + "" + title1 + "DAO.updateEntity(entity);" + "\n"
					+ space1 + "}" + "\n");
		}else {
			sb.append(space1 + "public String createEntity(" + title2 + " entity);" + "\n");
			sb.append("\n");
			sb.append(space1 + "public com.gdcn.bpaf.common.helper.Pagelet listAll(" + title2 + "Form form, int pageIndex, int pageSize);" + "\n");
			sb.append("\n");
			sb.append(space1 + "public " + title2 + " getEntity(String resourceId);" + "\n");
			sb.append("\n");
			sb.append(space1 + "public void updateEntity(" + title2 + " entity);" + "\n");
			sb.append("\n");
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 生成service及impl类
	 * @param temp 模板路径
	 * @return
	 * @throws Exception
	 */
	public static String initService(String temp) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			sb.append(s.replace("headTag", BaseUtil.getHead()).replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())) + "\n");
		}
		return sb.toString();
	}

}
