package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class MapCreator {
	
	/**
	 * 生成Struts和Spring配置文件（老版本）
	 * @param items 字段集合
	 * @return
	 */
	public static String assembleMap(List<Item> items) {
		String title1 = BaseUtil.getTitle();
		String title2 = BaseUtil.upCase(title1);
		StringBuffer sb = new StringBuffer();
		String space1 = BaseUtil.createSpaces(4);
		String space2 = BaseUtil.createSpaces(8);
		String space3 = BaseUtil.createSpaces(12);
		
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n"
				+ "<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"" + "\n"
				+ "\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">" + "\n");
		sb.append("\n");
		sb.append("<hibernate-mapping>" + "\n");
		
		sb.append(space1 + "<class name=\"com.gdcn.cms.wsbs." + title1 + ".model." + title2 + "\" table=\"zhj_" + title1 + "\" schema=\"dbo\">" + "\n");
		sb.append(space2 + "<id name=\"resourceId\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"resourceId\" length=\"32\" />" + "\n"
				+ space3 + "<generator class=\"uuid.hex\" />" + "\n"
				+ space2 + "</id>" + "\n");
		
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getTypes().equals("v")) {
				if(item.getProp().equals("必须材料") || item.getProp().equals("额外材料")) {
					sb.append(space2 + "<property name=\"" + item.getNames() + "\" type=\"java.lang.String\">" + "\n"
							+ space3 + "<column name=\"" + item.getNames() + "\" length=\"512\" />" + "\n"
							+ space2 + "</property>" + "\n");
				}else {
					sb.append(space2 + "<property name=\"" + item.getNames() + "\" type=\"java.lang.String\">" + "\n"
							+ space3 + "<column name=\"" + item.getNames() + "\" length=\"" + BaseUtil.getExtraLen(item.getLengths()) + "\" />" + "\n"
							+ space2 + "</property>" + "\n");
				}
			}else if(item.getTypes().equals("d")){
				sb.append(space2 + "<property name=\"" + item.getNames() + "\" type=\"java.util.Date\">" + "\n"
						+ space3 + "<column name=\"" + item.getNames() + "\" length=\"" + item.getLengths() + "\" />" + "\n"
						+ space2 + "</property>" + "\n");
			}else {
				sb.append(space2 + "<property name=\"" + item.getNames() + "\" type=\"java.lang.String\">" + "\n"
						+ space3 + "<column name=\"" + item.getNames() + "\" length=\"" + BaseUtil.getExtraLen(item.getLengths()) + "\" />" + "\n"
						+ space2 + "</property>" + "\n");
			}
		}
		
		sb.append(space2 + "<property name=\"creatorId\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"creatorId\" length=\"32\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"modifyTime\" type=\"java.util.Date\">" + "\n"
				+ space3 + "<column name=\"modifyTime\" length=\"23\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"modifyId\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"modifyId\" length=\"32\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"flagDelete\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"flagDelete\" length=\"32\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"sysCode\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"syscode\" length=\"32\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"createTime\" type=\"java.util.Date\">" + "\n"
				+ space3 + "<column name=\"createTime\" length=\"23\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"applicant\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"applicant\" length=\"100\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"company\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"company\" length=\"200\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"deptid\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"deptid\" length=\"254\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"itemid\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"itemid\" length=\"254\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"pid\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"pid\" length=\"254\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"wbsum\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"wbsum\" length=\"254\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"bxclId\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"bxcl_id\" length=\"2048\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"bxclName\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"bxcl_name\" length=\"2048\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"ewclId\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"ewcl_id\" length=\"2048\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"ewclName\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"ewcl_name\" length=\"2048\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"lxr\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"lxr\" length=\"20\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"yddh\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"yddh\" length=\"11\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"zjlx\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"zjlx\" length=\"30\" />" + "\n"
				+ space2 + "</property>" + "\n"
				+ space2 + "<property name=\"sfzjhm\" type=\"java.lang.String\">" + "\n"
				+ space3 + "<column name=\"sfzjhm\" length=\"30\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"dzxx\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"dzxx\" length=\"50\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"lxdh\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"lxdh\" length=\"32\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"jgmc\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"jgmc\" length=\"20\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"bz\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"bz\" length=\"1024\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"xmmc\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"xmmc\" length=\"20\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"xb\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"xb\" length=\"10\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"yzbm\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"yzbm\" length=\"10\" />" + "\n"
            	+ space2 + "</property>" + "\n"
            	+ space2 + "<property name=\"dz\" type=\"java.lang.String\">" + "\n"
            	+ space3 + "<column name=\"dz\" length=\"200\" />" + "\n"
            	+ space2 + "</property>" + "\n");
		
        sb.append(space1 + "</class>" + "\n");
		sb.append("</hibernate-mapping>" + "\n");
		return sb.toString();
	}
	
	/**
	 * 生成Struts和Spring配置文件
	 * @param items 字段集合
	 * @param temp 模板路径
	 * @return
	 * @throws Exception
	 */
	public static String initMap(List<Item> items, String temp) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			if(s.trim().equals("<!-- fieldTitle -->")) {
				//获取所有材料字段
				List<Item> fileItems = BaseUtil.getAllFile(items);
				//读取模板字符串
				s += "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n";
				//拼接基本信息字段
				for(int i=0; i<items.size()-fileItems.size(); i++) {
					Item item = items.get(i);
					sb.append(s.replace("fieldTitle", item.getTitle()).replace("fieldName", item.getNames()).replace("fieldType", item.getTypes().equals("v")?"java.lang.String":"java.util.Date").replace("fieldLength", BaseUtil.getExtraLen(item.getLengths())) + "\n");
				}
				//拼接材料信息字段
				for(int i=0; i<fileItems.size(); i++) {
					Item item = fileItems.get(i);
					sb.append(s.replace("fieldTitle", item.getTitle()).replace("column=\"fieldName\"", "column=\"fj_" + BaseUtil.lowCase(item.getNames().substring(2)) + "\"").replace("name=\"fieldName\"", "name=\"" + item.getNames() + "\"").replace("fieldType", item.getTypes().equals("v")?"java.lang.String":"java.util.Date").replace("fieldLength", BaseUtil.getExtraLen(item.getLengths())) + "\n");
				}
				continue;
			}
			sb.append(s.replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())) + "\n");
		}
		return sb.toString();
	}

}
