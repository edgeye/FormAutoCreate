package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.cdc.util.BaseUtil;

public class ConfigCreator {
	
	public static String assembleConfig() {
		String head = BaseUtil.getHead();
		String title1 = BaseUtil.getTitle();
		String title2 = BaseUtil.upCase(title1);
		StringBuffer sb = new StringBuffer();
		
		sb.append("<!--" + head + "-->" + "\n");
		sb.append("<form-bean name=\"" + title1 + "Form\" type=\"com.gdcn.cms.wsbs." + title1 + ".web.form." + title2 + "Form\"></form-bean>" + "\n");

		sb.append("\n");
		sb.append("<!--" + head + "Start-->" + "\n");
		sb.append("<action name=\"" + title1 + "Form\" path=\"/list" + title2 + "\" parameter=\"list" + title2 + "\" scope=\"request\" type=\"org.springframework.web.struts.DelegatingActionProxy\"></action>" + "\n"
				+ "<action name=\"" + title1 + "Form\" path=\"/edit" + title2 + "\" parameter=\"edit" + title2 + "\" scope=\"request\" type=\"org.springframework.web.struts.DelegatingActionProxy\"></action>" + "\n"
				+ "<action name=\"" + title1 + "Form\" path=\"/save" + title2 + "\" parameter=\"save" + title2 + "\" scope=\"request\" type=\"org.springframework.web.struts.DelegatingActionProxy\"></action>" + "\n");
		sb.append("<!--" + head + "End-->" + "\n");
		sb.append("\n");
		
		sb.append("<!--" + head + "Start-->" + "\n");
		sb.append("<bean id=\"" + title1 + "DAO\" class=\"com.gdcn.cms.wsbs." + title1 + ".dao.impl." + title2 + "DAOImpl\" parent=\"baseDAO\"></bean>" + "\n"
				+ "<bean id=\"" + title1 + "Service\" parent=\"txProxyTemplate\">" + "\n"
				+ "<property name=\"target\">" + "\n"
				+ "<bean class=\"com.gdcn.cms.wsbs." + title1 + ".service.impl." + title2 + "ServiceImpl\">" + "\n"
				+ "<property name=\"" + title1 + "DAO\"><ref bean=\"" + title1 + "DAO\"/></property>" + "\n"
				+ "</bean>" + "\n"
				+ "</property>" + "\n"
    			+ "</bean>" + "\n");
		sb.append("<!--" + head + "End-->" + "\n");
		sb.append("\n");
		
		sb.append("<!--" + head + "Start-->" + "\n");
		sb.append("<bean name=\"" + title1 + "Action\" class=\"com.gdcn.cms.wsbs." + title1 + ".web.action." + title2 + "Action\" singleton=\"false\" abstract=\"true\">" + "\n"
				+ "<property name=\"" + title1 + "Service\"><ref bean=\"" + title1 + "Service\"/></property>" + "\n"
				+ "<property name=\"cmsBsdtWSService\"><ref bean=\"cmsBsdtWSService\"/></property>" + "\n"
				+ "<property name=\"sxglService\"><ref bean=\"sxglService\"/></property>" + "\n"	
				+ "</bean>" + "\n"
				+ "<bean name=\"/list" + title2 + "\" parent=\"" + title1 + "Action\"></bean>" + "\n"
				+ "<bean name=\"/edit" + title2 + "\" parent=\"" + title1 + "Action\"></bean>" + "\n"
				+ "<bean name=\"/save" + title2 + "\" parent=\"" + title1 + "Action\"></bean>" + "\n");
		sb.append("<!--" + head + "End-->" + "\n");
		
		return sb.toString();
	}
	
	public static String initConfig(String temp) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			sb.append(s.replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())).replace("headTag", BaseUtil.getHead()) + "\n");
		}
		return sb.toString();
	}

}
