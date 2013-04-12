package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class JsCreator {
	
	public static String assembleJs(List<Item> items) throws Exception {
		List<Item> list = new ArrayList<Item>();
		list.addAll(BaseUtil.getBaseInfo(items));
		list.addAll(BaseUtil.getBusInfo(items));
		String space1 = BaseUtil.createSpaces(4);
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
		sb.append("<form>" + "\n");
		
		for(int i=0; i<list.size(); i++) {
			Item item = list.get(i);
			if(item.getNames().equals("name")) {
				sb.append(space1 + "<field name=\"name\" text=\"姓名\" validateType=\"Require,Limit\" max=\"30\"/>" + "\n");
				continue;
			}else if(item.getNames().equals("sex")) {
				sb.append(space1 + "<field name=\"sex\" text=\"性别\" validateType=\"Require\"/>" + "\n");
				continue;
			}else if(item.getNames().equals("idCardNumber")) {
				sb.append(space1 + "<field name=\"idCardNumber\" text=\"身份证号码\" validateType=\"Require,IdCard\"/>" + "\n");
				continue;
			}else if(item.getNames().equals("email")) {
				sb.append(space1 + "<field name=\"email\" text=\"电子邮件\" validateType=\"Require,Email\"/>" + "\n");
				continue;
			}else if(item.getNames().equals("csrq")) {
				sb.append(space1 + "<field name=\"csrq\" text=\"出生日期\" validateType=\"Require\"/>" + "\n");
				continue;
			}
			
			if(item.getReq().equals("是")) {
				if(item.getTitle().indexOf("手机")!=-1) {
					sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Require,Mobile\"/>" + "\n");
					continue;
				}
				if(item.getTitle().equals("联系电话")) {
					sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Require,Limit\" max=\"30\"/>" + "\n");
					continue;
				}
				if(item.getTitle().indexOf("电话")!=-1 || item.getTitle().indexOf("传真")!=-1) {
					sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Require,Phone\"/>" + "\n");
					continue;
				}
				if(item.getTitle().indexOf("邮编")!=-1 || item.getTitle().indexOf("邮政编码")!=-1) {
					sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Require,Zip\"/>" + "\n");
					continue;
				}
				if(item.getTypes().equals("d") || item.getView().equals("下拉列表") || item.getView().equals("单选按钮")) {
					sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Require\"/>" + "\n");
					continue;
				}else {
					sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Require,Limit\" max=\"" + item.getLengths() + "\"/>" + "\n");
				}
			}else {
				sb.append(space1 + "<field name=\"" + item.getNames() + "\" text=\"" + item.getTitle() + "\" validateType=\"Limit\" max=\"" + item.getLengths() + "\"/>" + "\n");
			}
		}
		sb.append(space1 + "<field name=\"yddh\" text=\"手机号码\" validateType=\"Require,Mobile\"/>" + "\n");
		sb.append(space1 + "<field name=\"yzbm\" text=\"邮政编码\" validateType=\"Require,Zip\"/>" + "\n");
		sb.append("</form>");
		return sb.toString();
	}
	
	public static String initJs(List<Item> items, String temp) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			
		}
		return sb.toString();
	}

}
