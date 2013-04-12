package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.cdc.util.BaseUtil;

public class DaoCreator {
	
	/**
	 * 组装Dao
	 * @param type 类型：dao或者impl
	 * @return
	 */
	public static String assembleDao(String type) {
		String title1 = BaseUtil.getTitle();
		String title2 = BaseUtil.upCase(title1);
		StringBuffer sb = new StringBuffer();
		String space1 = BaseUtil.createSpaces(4);
		String space2 = BaseUtil.createSpaces(8);
		String space3 = BaseUtil.createSpaces(12);
		String space4 = BaseUtil.createSpaces(16);
		String space5 = BaseUtil.createSpaces(20);
		if(type.equals("impl")) {
			sb.append("package com.gdcn.cms.wsbs." + title1 + ".dao.impl;" + "\n");
		}else {
			sb.append("package com.gdcn.cms.wsbs." + title1 + ".dao;" + "\n");
		}
		sb.append("\n");
		
		if(type.equals("impl")) {
			sb.append("import java.util.Iterator;" + "\n");
			sb.append("import java.util.List;" + "\n");
			sb.append("import java.util.Map;" + "\n");
			sb.append("\n");
			sb.append("import com.gdcn.bpaf.common.base.BaseDAOHibernate;" + "\n");
			sb.append("import com.gdcn.bpaf.common.helper.Pagelet;" + "\n");
			sb.append("import com.gdcn.cms.wsbs." + title1 + ".dao.I" + title2 + "DAO;" + "\n");
		}
		
		sb.append("import com.gdcn.cms.wsbs." + title1 + ".model." + title2 + ";" + "\n");
		sb.append("/**" + "\n"
				+ "* " + BaseUtil.getHead() + "\n" 
				+ "* @author yaoguoc" + "\n"
				+ "*" + "\n"
				+ "*/" + "\n");
		if(type.equals("impl")) {
			sb.append("public class " + title2 + "DAOImpl extends BaseDAOHibernate implements I" + title2 + "DAO {" + "\n");
		}else {
			sb.append("public interface I" + title2 + "DAO {" + "\n");
		}
		sb.append("\n");
		
		if(type.equals("impl")) {
			//createEntity
			sb.append(space1 + "@Override" + "\n");
			sb.append(space1 + "public String createEntity(" + title2 + " entity) {" + "\n");
			sb.append(space2 + "super.doCreateObject(entity);" + "\n");
			sb.append(space2 + "return entity.getResourceId();" + "\n");
			sb.append(space1 + "}" + "\n");
			sb.append("\n");
			
			//Pagelet listAll
			sb.append(space1 + "@Override" + "\n");
			sb.append(space1 + "public Pagelet listAll(Map<Object, Object> propMap," + "\n");
			sb.append(space3 + "Map<Object, Object> orderMap, int pageIndex, int pageSize) {" + "\n");
			sb.append(space2 + "Pagelet pagelet = new Pagelet();" + "\n");
			sb.append(space2 + "String hql = \"from " + title2 + "\";" + "\n");
			sb.append(space2 + "String filterHql = getWhere(propMap);" + "\n");
			sb.append(space2 + "int count = super.countRows(hql + filterHql);" + "\n");
			sb.append(space2 + "String orderByHql = getOrderBy(orderMap);" + "\n");
			sb.append(space2 + "List pageList = super.doFind(hql + filterHql + orderByHql, pageIndex, pageSize);" + "\n");
			sb.append(space2 + "pagelet.setPageList(pageList);" + "\n");
			sb.append(space2 + "pagelet.setTotalRecord(count);" + "\n");
			sb.append(space2 + "pagelet.setPageName(\"listAll" + title2 + "\");" + "\n");
			sb.append(space2 + "return pagelet;" + "\n");
			sb.append(space1 + "}" + "\n");
			sb.append("\n");
			
			//getEntity
			sb.append(space1 + "@Override" + "\n");
			sb.append(space1 + "public " + title2 + " getEntity(String resourceId) {" + "\n");
			sb.append(space2 + "return (" + title2 + ")super.doFindObjectById(resourceId);" + "\n");
			sb.append(space1 + "}" + "\n");
			sb.append("\n");
			
			//updateEntity
			sb.append(space1 + "@Override" + "\n");
			sb.append(space1 + "public void updateEntity(" + title2 + " entity) {" + "\n");
			sb.append(space2 + "super.doUpdateObject(entity);" + "\n");
			sb.append(space1 + "}" + "\n");
			sb.append("\n");
			
			//getModelClass
			sb.append(space1 + "@Override" + "\n");
			sb.append(space1 + "protected Class getModelClass() {" + "\n");
			sb.append(space2 + "return " + title2 + ".class;" + "\n");
			sb.append(space1 + "}" + "\n");
			sb.append("\n");
			
			//getWhere
			sb.append(space1 + "public String getWhere(Map filterPropertyMap) {" + "\n"
					+ space2 + "StringBuffer rs = new StringBuffer();" + "\n"
					+ space2 + "if (filterPropertyMap != null && !filterPropertyMap.isEmpty()) {" + "\n"
					+ space3 + "rs.append(\" where 1=1 \");" + "\n"
					+ space3 + "for (Iterator itor = filterPropertyMap.keySet().iterator(); itor.hasNext();) {" + "\n"
					+ space4 + "String field = (String)itor.next();" + "\n"
					+ space4 + "Object value =  filterPropertyMap.get(field);" + "\n"
					+ space4 + "if(value  instanceof String) {" + "\n"			
					+ space5 + "rs.append(\" and \").append(field).append(\" \"+value).append(\" \");" + "\n"
					+ space4 + "}else if(value  instanceof Integer) {" + "\n"
					+ space5 + "rs.append(\" and \").append(field).append(\" \"+value).append(\" \");" + "\n"
					+ space4 + "}" + "\n"
					+ space3 + "}" + "\n"
					+ space3 + "return rs.substring(0, rs.length() - 1);" + "\n"
					+ space2 + "}" + "\n"
					+ space2 + "return \"\";" + "\n"
					+ space1 + "}" + "\n");
			sb.append("\n");
			
			//getOrderBy
			sb.append(space1 + "public String getOrderBy(Map orderByPerpertyMap) {" + "\n"
					+ space2 + "StringBuffer rs = new StringBuffer();" + "\n"
					+ space2 + "if (orderByPerpertyMap != null && !orderByPerpertyMap.isEmpty()) {" + "\n"
					+ space3 + "rs.append(\" ORDER BY \");" + "\n"
					+ space3 + "for (Iterator itor = orderByPerpertyMap.keySet().iterator(); itor.hasNext();) {" + "\n"
					+ space4 + "Object field = (String) itor.next();" + "\n"
					+ space4 + "String ord = (String) orderByPerpertyMap.get(field);" + "\n"
					+ space4 + "rs.append(field).append(\" \").append(ord).append(\",\");" + "\n"
					+ space3 + "}" + "\n"
					+ space3 + "return rs.substring(0, rs.length() - 1);" + "\n"
					+ space2 + "}" + "\n"
					+ space2 + "return rs.toString();" + "\n"
					+ space1 + "}" + "\n");
		}else {
			sb.append(space1 + "public String createEntity(" + title2 + " entity);" + "\n");
			sb.append("\n");
			sb.append(space1 + "public com.gdcn.bpaf.common.helper.Pagelet listAll(java.util.Map<Object, Object> propMap, java.util.Map<Object, Object> orderMap, int pageIndex, int pageSize);" + "\n");
			sb.append("\n");
			sb.append(space1 + "public " + title2 + " getEntity(String resourceId);" + "\n");
			sb.append("\n");
			sb.append(space1 + "public void updateEntity(" + title2 + " entity);" + "\n");
			sb.append("\n");
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static String initDao(String temp) throws Exception {
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
