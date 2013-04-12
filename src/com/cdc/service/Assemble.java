package com.cdc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cdc.dao.ActionCreator;
import com.cdc.dao.ConfigCreator;
import com.cdc.dao.DaoCreator;
import com.cdc.dao.JsCreator;
import com.cdc.dao.JspCreator;
import com.cdc.dao.MapCreator;
import com.cdc.dao.PojoCreator;
import com.cdc.dao.ServiceCreator;
import com.cdc.dao.SqlCreator;
import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class Assemble {

	public static boolean create(String[] titles, String[] names, String[] types,
			String[] lengths, String[] reqs, String[] props, String[] views) {
		List<Item> items = new ArrayList<Item>();
		for(int i=0; i<titles.length; i++) {
			Item item = new Item();
			item.setTitle(titles[i].trim());
			item.setNames(names[i].trim());
			item.setTypes(types[i].trim());
			if(views[i].indexOf("下拉列表")!=-1 || views[i].indexOf("单选按钮")!=-1 || views[i].indexOf("多选框")!=-1) {
				item.setLengths("30");
			}else {
				item.setLengths(lengths[i].trim());
			}
			item.setReq(reqs[i].trim());
			item.setProp(props[i].trim());
			item.setView(views[i].trim());
			items.add(item);
		}
		
		List<Item> otherItems = BaseUtil.getOtherItems(items);
		
		createService("service");
		createService("impl");
		createDao("dao");
		createDao("impl");
		createMap(otherItems);
		createPojo(otherItems, "form");
		createPojo(otherItems, "model");
		createConfig();
		createJs(otherItems);
		createJsp(otherItems);
		createAction(otherItems);
		
		createSql(BaseUtil.getSqlItems(items));
		
		return true;
	}
	
	public static void createSql(List<Item> items) {
		File sqlDir = new File("E:\\abc\\sql");
		if(!sqlDir.exists()) sqlDir.mkdir();
		File sqlFile = new File("E:\\abc\\sql\\" + BaseUtil.getTitle() + "Sql.sql");
		try {
			String pro = SqlCreator.initSql(items, "E:\\abc\\version2.0\\sqlTemp.sql");
			BaseUtil.write(sqlFile, pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("+++++++++ sql file created ++++++++");
	}
	
	public static void createService(String type) {
		if(type.equals("service")) {
//			File serviceDir = new File("E:\\abc\\temp\\service");
			File serviceDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\service");
			if(!serviceDir.exists()) serviceDir.mkdirs();
//			File serviceFile = new File("E:\\abc\\temp\\service\\I" + BaseUtil.upCase(BaseUtil.getTitle()) + "Service.java");
			File serviceFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\service\\I" + BaseUtil.upCase(BaseUtil.getTitle()) + "Service.java");
			try {
				String service = ServiceCreator.initService("E:\\abc\\version2.0\\serviceTemp.java");
				BaseUtil.write(serviceFile, service);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("+++++++++ service created ++++++++");
		}else if(type.equals("impl")) {
//			File serviceImplDir = new File("E:\\abc\\temp\\service\\impl");
			File serviceImplDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\service\\impl");
			if(!serviceImplDir.exists()) serviceImplDir.mkdirs();
//			File serviceImplFile = new File("E:\\abc\\temp\\service\\impl\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "ServiceImpl.java");
			File serviceImplFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\service\\impl\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "ServiceImpl.java");
			
			try {
				String serviceImpl = ServiceCreator.initService("E:\\abc\\version2.0\\serviceImplTemp.java");
				BaseUtil.write(serviceImplFile, serviceImpl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("+++++++++ service implementation created ++++++++");
		}
	}
	
	public static void createDao(String type) {
		if(type.equals("dao")) {
//			File daoDir = new File("E:\\abc\\temp\\dao");
			File daoDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\dao");
			if(!daoDir.exists()) daoDir.mkdirs();
//			File daoFile = new File("E:\\abc\\temp\\dao\\I" + BaseUtil.upCase(BaseUtil.getTitle()) + "Dao.java");
			File daoFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\dao\\I" + BaseUtil.upCase(BaseUtil.getTitle()) + "Dao.java");
			try {
				String dao = DaoCreator.initDao("E:\\abc\\version2.0\\daoTemp.java");
				BaseUtil.write(daoFile, dao);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("+++++++++ dao created ++++++++");
		}else if(type.equals("impl")) {
//			File daoImplDir = new File("E:\\abc\\temp\\dao\\impl");
			File daoImplDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\dao\\impl");
			if(!daoImplDir.exists()) daoImplDir.mkdirs();
//			File daoImplFile = new File("E:\\abc\\temp\\dao\\impl\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "DaoImpl.java");
			File daoImplFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\dao\\impl\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "DaoImpl.java");
			try {
				String daoImpl = DaoCreator.initDao("E:\\abc\\version2.0\\daoImplTemp.java");
				BaseUtil.write(daoImplFile, daoImpl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("+++++++++ dao implementation created ++++++++");
		}
	}
	
	public static void createPojo(List<Item> items, String type) {
		if(type.equals("form")) {
//			File formDir = new File("E:\\abc\\temp\\web\\form");
			File formDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\"+ BaseUtil.getTitle() + "\\web\\form");
			if(!formDir.exists()) formDir.mkdirs();
//			File formFile = new File("E:\\abc\\temp\\web\\form\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "Form.java");
			File formFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\"+ BaseUtil.getTitle() + "\\web\\form\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "Form.java");
			try {
				String form = PojoCreator.initPojo(items, "E:\\abc\\version2.0\\formTemp.java", "form");
				BaseUtil.write(formFile, form);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("+++++++++ form created ++++++++");
		}else if(type.equals("model")) {
//			File modelDir = new File("E:\\abc\\temp\\web\\model");
			File modelDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\model");
			modelDir.mkdirs();
//			File modelFile = new File("E:\\abc\\temp\\web\\model\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "Model.java");
			File modelFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\model\\" + BaseUtil.upCase(BaseUtil.getTitle()) + ".java");
			
			try {
				String model = PojoCreator.initPojo(items, "E:\\abc\\version2.0\\formTemp.java", "model");
				BaseUtil.write(modelFile, model);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("+++++++++ model created ++++++++");
		}
	}
	
	public static void createMap(List<Item> items) {
//		File mappingDir = new File("E:\\abc\\temp\\mapping");
		File mappingDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\WebRoot\\WEB-INF\\mappings\\bpaf\\cmszhj");
		if(!mappingDir.exists()) mappingDir.mkdirs();
//		File mappingFile = new File("E:\\abc\\temp\\mapping\\Zhj" + BaseUtil.upCase(BaseUtil.getTitle()) + ".hbm.xml");
		File mappingFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\WebRoot\\WEB-INF\\mappings\\bpaf\\cmszhj\\zhj_" + BaseUtil.getTitle() + ".hbm.xml");
		try {
			String mapping = MapCreator.initMap(items, "E:\\abc\\version2.0\\mapTemp.xml");
			BaseUtil.writeUtf(mappingFile, mapping);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("+++++++++ mapping created ++++++++");
	}
	
	public static void createConfig() {
//		File configDir = new File("E:\\abc\\temp\\config");
		File configDir = new File("E:\\abc\\config");
		if(!configDir.exists()) configDir.mkdirs();
//		File configFile = new File("E:\\abc\\temp\\config\\" + BaseUtil.getTitle() + "Config.xml");
		File configFile = new File("E:\\abc\\config\\" + BaseUtil.getTitle() + "Config.xml");
		try {
			String config = ConfigCreator.initConfig("E:\\abc\\version2.0\\configTemp.xml");
			BaseUtil.write(configFile, config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("+++++++++ config created ++++++++");
	}
	
	public static void createJs(List<Item> items) {
//		File jsDir = new File("E:\\abc\\temp\\js");
		File jsDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\etc\\field\\bpaf\\cmszhj");
		if(!jsDir.exists()) jsDir.mkdirs();
//		File jsFile = new File("E:\\abc\\temp\\js\\" + BaseUtil.getTitle() + ".xml");
		File jsFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\etc\\field\\bpaf\\cmszhj\\zhj_" + BaseUtil.getTitle() + ".xml");
		try {
			String js = JsCreator.assembleJs(items);
			BaseUtil.writeUtf(jsFile, js);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("+++++++++ js file created ++++++++");
	}
	
	public static void createJsp(List<Item> items) {
//		File jspDir = new File("E:\\abc\\temp\\jsp");
		File jspDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\WebRoot\\bpaf\\cms_view\\template\\zjbs\\themeA\\biz2");
		if(!jspDir.exists()) jspDir.mkdirs();
//		File jspFile = new File("E:\\abc\\temp\\jsp\\" + BaseUtil.getTitle() + "_form.jsp");
		File jspFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\WebRoot\\bpaf\\cms_view\\template\\zjbs\\themeA\\biz2\\" + BaseUtil.getTitle() + "_form.jsp");
		try {
			String jsp = JspCreator.initJsp(items, "E:\\abc\\version2.0\\viewForm2.jsp");
			BaseUtil.write(jspFile, jsp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("+++++++++ jsp created ++++++++");
	}
	
	public static void createAction(List<Item> items) {
//		File actionDir = new File("E:\\abc\\temp\\action");
		File actionDir = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\web\\action");
		actionDir.mkdirs();
//		File actionFile = new File("E:\\abc\\temp\\action\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "Action.java");
		File actionFile = new File("E:\\cndatacom\\projectforcms\\cms_zhj\\src\\com\\gdcn\\cms\\wsbs2\\" + BaseUtil.getTitle() + "\\web\\action\\" + BaseUtil.upCase(BaseUtil.getTitle()) + "Action.java");
		try {
			String action = ActionCreator.initAction(items, "E:\\abc\\version2.0\\actionTemp.java");
			BaseUtil.write(actionFile, action);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("+++++++++ action created ++++++++");
	}

}
