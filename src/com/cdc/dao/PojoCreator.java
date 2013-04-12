package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class PojoCreator {
	
	/**
	 * 组装bean（老版本）
	 * @param items 字段
	 * @param type 类型：form或者model
	 * @return
	 */
	public static String assemblePojo(List<Item> items, String type) {
		StringBuffer sb = new StringBuffer();
		String space1 = BaseUtil.createSpaces(4);
		String space2 = BaseUtil.createSpaces(8);
		if(type.equals("model")) {
			sb.append("package com.gdcn.cms.wsbs." + BaseUtil.getTitle() + ".model;" + "\n");
		}else {
			sb.append("package com.gdcn.cms.wsbs." + BaseUtil.getTitle() + ".web.form;" + "\n");
		}
		sb.append("\n");
		sb.append("import java.util.Date;" + "\n");
		sb.append("\n");
		if(type.equals("model")) {
			sb.append("import com.gdcn.cms.wsbs.common.ZhjBaseModel;" + "\n");
		}else {
			sb.append("import com.gdcn.cms.wsbs.common.ZhjBaseForm;" + "\n");
		}
		sb.append("/**" + "\n"
				+ "* " + BaseUtil.getHead() + "\n" 
				+ "* @author yaoguoc" + "\n"
				+ "*" + "\n"
				+ "*/" + "\n");
		if(type.equals("model")) {
			sb.append("public class " + BaseUtil.upCase(BaseUtil.getTitle()) + " extends ZhjBaseModel implements java.io.Serializable {" + "\n");
		}else {
			sb.append("public class " + BaseUtil.upCase(BaseUtil.getTitle()) + "Form extends ZhjBaseForm implements java.io.Serializable {" + "\n");
		}
		sb.append("\n");
		sb.append(space1 + "private static final long serialVersionUID = 1L;" + "\n");
		sb.append("\n");
		
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getTypes().equals("v")) {
				sb.append(space1 + "private String " + item.getNames() + ";//" + item.getTitle() + "\n");
			}else if(item.getTypes().equals("d")) {
				sb.append(space1 + "private Date " + item.getNames() + ";//" + item.getTitle() + "\n");
			}else {
				sb.append(space1 + "private String " + item.getNames() + ";//" +item.getTitle() + "\n");
			}
		}
		sb.append("\n");
		
		if(type.equals("model")) {
			sb.append(space1 + "public " + BaseUtil.upCase(BaseUtil.getTitle()) + "() {" + "\n");
			sb.append("\n");
			sb.append(space1 + "}" + "\n");
			sb.append("\n");
		}
		
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getTypes().equals("v")) {
				sb.append(space1 + "public String get" + BaseUtil.upCase(item.getNames()) + "() {" + "\n");
				sb.append(space2 + "return " + item.getNames() + ";" + "\n");
				sb.append(space1 + "}" + "\n");
				sb.append("\n");
				
				sb.append(space1 + "public void set" + BaseUtil.upCase(item.getNames()) + "(String " + item.getNames() + ") {" + "\n");
				sb.append(space2 + "this." + item.getNames() + " = " + item.getNames() + ";" + "\n");
				sb.append(space1 + "}" + "\n");
				sb.append("\n");
			}else if(item.getTypes().equals("d")) {
				sb.append(space1 + "public Date get" + BaseUtil.upCase(item.getNames()) + "() {" + "\n");
				sb.append(space2 + "return " + item.getNames() + ";" + "\n");
				sb.append(space1 + "}" + "\n");
				sb.append("\n");
				
				sb.append(space1 + "public void set" + BaseUtil.upCase(item.getNames()) + "(Date " + item.getNames() + ") {" + "\n");
				sb.append(space2 + "this." + item.getNames() + " = " + item.getNames() + ";" + "\n");
				sb.append(space1 + "}" + "\n");
				sb.append("\n");
			}else {
				sb.append(space1 + "public String get" + BaseUtil.upCase(item.getNames()) + "() {" + "\n");
				sb.append(space2 + "return " + item.getNames() + ";" + "\n");
				sb.append(space1 + "}" + "\n");
				sb.append("\n");
				
				sb.append(space1 + "public void set" + BaseUtil.upCase(item.getNames()) + "(String " + item.getNames() + ") {" + "\n");
				sb.append(space2 + "this." + item.getNames() + " = " + item.getNames() + ";" + "\n");
				sb.append(space1 + "}" + "\n");
				sb.append("\n");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 生成Model或Form
	 * @param items 字段集合
	 * @param temp 模板路径
	 * @param type 类型：model或form
	 * @return
	 * @throws Exception
	 */
	public static String initPojo(List<Item> items, String temp, String type) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			
			if(s.trim().startsWith("package com.gdcn")) {
				if(type.equals("model")) {
					sb.append("package com.gdcn.cms.wsbs2." + BaseUtil.getTitle() + ".model;" + "\n");
				}else {
					sb.append("package com.gdcn.cms.wsbs2." + BaseUtil.getTitle() + ".web.form;");
				}
				continue;
			}
			//字段声明
			if(s.trim().equals("private fieldType fieldName;")) {
				for(int i=0; i<items.size(); i++) {
					Item item = items.get(i);
					sb.append(s.replace("fieldType", item.getTypes().equals("v")?"java.lang.String":"java.util.Date").replace("fieldName", item.getNames()) + "    //" + item.getTitle() + "\n");
				}
				continue;
			}
			//构造函数参数
			if(s.trim().equals("fieldType fieldName,")) {
				for(int i=0; i<items.size(); i++) {
					Item item = items.get(i);
					if(i==items.size()-1) {
						String s1 = s.substring(0, s.lastIndexOf(","));
						sb.append(s1.replace("fieldType", item.getTypes().equals("v")?"java.lang.String":"java.util.Date").replace("fieldName", item.getNames()) + "\n");
					}else {
						sb.append(s.replace("fieldType", item.getTypes().equals("v")?"java.lang.String":"java.util.Date").replace("fieldName", item.getNames()) + "\n");
					}
				}
				continue;
			}
			//构造函数内容
			if(s.trim().equals("this.fieldName = fieldName;")) {
				for(int i=0; i<items.size(); i++) {
					Item item = items.get(i);
					sb.append(s.replace("fieldName", item.getNames()) + "\n");
				}
				continue;
			}
			//getter和setter方法
			if(s.trim().startsWith("public fieldType getfieldName")) {
				s += "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine() + "\n";
				for(int i=0; i<items.size(); i++) {
					Item item = items.get(i);
					sb.append(s.replace("fieldName1", BaseUtil.upCase(item.getNames())).replace("fieldType", item.getTypes().equals("v")?"java.lang.String":"java.util.Date").replace("fieldName", item.getNames()) + "\n");
				}
				continue;
			}
			sb.append(s.replace("headTag", BaseUtil.getHead()).replace("titleTag1", BaseUtil.getTitle()).replace("pojoType1", type).replace("titleTag2", type.equals("model")?BaseUtil.upCase(BaseUtil.getTitle()):BaseUtil.upCase(BaseUtil.getTitle())+"Form").replace("pojoType2", BaseUtil.upCase(type)) + "\n");
		}
		return sb.toString();
	}
	
}
