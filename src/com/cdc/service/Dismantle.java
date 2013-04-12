package com.cdc.service;

import java.util.ArrayList;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;
import com.cdc.util.PinyinUtil;

public class Dismantle {
	
	/**
	 * 获取字段集合（版本1）
	 * @param names 名称
	 * @param types 类型
	 * @param needs 必填项
	 * @param areas 取值范围
	 * @return
	 */
	public static List<Item> dispatch(String[] names, String[] types, String[] needs, String[] areas) {
		List<Item> items = new ArrayList<Item>();
		List<Item> baseItems = null;
		List<Item> busItems = null;
		List<Item> bxItems = null;
		List<Item> ewItems = null;
		
		if(names[0]!=null && names[0].trim()!="") {
			baseItems = departStr(names, types, needs, areas, 0);
		}
		if(names[0]!=null && names[1].trim()!="") {
			busItems = departStr(names, types, needs, areas, 1);
		}
		if(names[0]!=null && names[2].trim()!="") {
			bxItems = departStr(names, types, needs, areas, 2);
		}
		if(names[0]!=null && names[3].trim()!="") {
			ewItems = departStr(names, types, needs, areas, 3);
		}
		
		if(baseItems!=null) {
			items.addAll(baseItems);
		}
		if(busItems!=null) {
			items.addAll(busItems);
		}
		if(bxItems!=null) {
			items.addAll(bxItems);
		}
		if(ewItems!=null) {
			items.addAll(ewItems);
		}
		return items;
	}
	
	/**
	 * 分解字符串（版本1）
	 * @param names 名称
	 * @param types 类型
	 * @param needs 必填项
	 * @param areas 取值范围
	 * @param index 属性下标
	 * @return
	 */
	public static List<Item> departStr(String[] names, String[] types, String[] needs, String[] areas, int index) {
		List<Item> items = new ArrayList<Item>();
		String[] baseInfoNames = names[index].split("\n");
		String[] baseInfoViews = null;
		String[] baseInfoReqs = null;
		String[] baseInfoAreas = null;
		if(index==0 || index==1) {
			baseInfoViews = types[index].split("\n");
			baseInfoReqs = needs[index].split("\n");
			baseInfoAreas = areas[index].split("\n");
		}
		for(int i=0; i<baseInfoNames.length; i++) {
			Item item = new Item();
			
			if(index==0) {
				item.setProp("基本属性");
			}else if(index==1) {
				item.setProp("业务属性");
			}else if(index==2) {
				item.setProp("必须材料");
			}else if(index==3) {
				item.setProp("额外材料");
			}
			
			if(index==0 || index==1) {
				item.setTitle(baseInfoNames[i].trim());
				
				String fieldNames = initBaseNames(baseInfoNames[i].trim());
				if(fieldNames!=null) item.setNames(fieldNames);
				
				item.setView(baseInfoViews[i].trim());
				
				if(baseInfoReqs[i]!=null && baseInfoReqs[i].trim()!="") {
					item.setReq(baseInfoReqs[i].trim());
				}else {
					item.setReq("否");
				}
				
				String num = BaseUtil.analyseArea(baseInfoAreas[i]);
				if(num.endsWith("d")) {
					item.setLengths("32");
					item.setTypes("d");
				}else {
					item.setLengths(num);
					item.setTypes("v");
				}
				
				items.add(item);
			}else if(index==2 || index==3){
				
				Item item1 = initFileItem(baseInfoNames[i].trim());
				item1.setTitle(baseInfoNames[i].trim() + "文件名称");
				item1.setNames(PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(baseInfoNames[i])));
				item.setProp(index==2?"必须材料":"额外材料");
				item.setReq(index==2?"是":"否");
				
				item = initFileItem(baseInfoNames[i].trim());
				item.setProp(index==2?"必须材料":"额外材料");
				item.setReq(index==2?"是":"否");
				
				items.add(item1);
				items.add(item);
			}
		}
		
		return items;
	}
	
	/**
	 * 赋值材料字段属性
	 * @param title
	 * @return
	 */
	public static Item initFileItem(String title) {
		Item item = new Item();
		item.setTitle(title + "上传路径");
		item.setNames(PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(title)) + "Path");
		item.setView("文本框");
		item.setLengths("512");
		item.setTypes("v");
		return item;
	}
	
	/**
	 * 生成字段英文名称
	 * @param str 字段中文名称
	 * @return
	 */
	public static String initBaseNames(String str) {
		if(str.equals("姓名")) {
			return"name";
		}else if(str.equals("性别")) {
			return"sex";
		}else if(str.equals("身份证号码")) {
			return"idCardNumber";
		}else if(str.equals("电子邮件")) {
			return"email";
		}else if(str.equals("民族")) {
			return"mz";
		}else if(str.equals("出生日期")) {
			return"csrq";
		}else if(str.equals("手机号码")) {
			return null;
		}else if(str.equals("邮政编码")) {
			return null;
		}else {
			return PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(str));
		}
	}
	
	public static List<Item> convert(String base) {
		List<Item> list = new ArrayList<Item>();
		
		String baseInfo = base.substring(base.indexOf("姓名"), base.indexOf("在线申请_第二步"));
		String fileInfo = base.substring(base.indexOf("在线申请_第二步\r\n")+"在线申请_第二步\r\n".length(), base.length());
		
		String[] baseInfos = baseInfo.split("\r\n\r\n");
		String[] fileInfos = fileInfo.split("\r\n\r\n");
		
		String[] personals = baseInfos[0].split("\r\n");
		String[] businesses = baseInfos[1].split("\r\n");
		String[] requireFiles = fileInfos[0].split("\r\n");
		String[] extraFiles = fileInfos[1].split("\r\n");
		
		List<Item> list1 = isolate(personals, "personal");
		List<Item> list2 = isolate(businesses, "business");
		List<Item> list3 = isolate(requireFiles, "require");
		List<Item> list4 = isolate(extraFiles, "extra");
		
		if(list1!=null) list.addAll(list1);
		if(list2!=null) list.addAll(list2);
		if(list3!=null) list.addAll(list3);
		if(list4!=null) list.addAll(list4);
		
		return list;
	}
	
	/**
	 * 获取字段集合
	 * @param strs
	 * @param type
	 * @return
	 */
	public static List<Item> isolate(String[] strs, String type) {
		List<Item> list = new ArrayList<Item>();
		for(int i=0; i<strs.length; i++) {
			Item item = new Item();
			
			if(type.equals("personal") || type.equals("business")) {
				
				item.setTitle(strs[i].split("\t")[0].trim());
				
				String fieldNames = initBaseNames(strs[i].split("\t")[0].trim());
				if(fieldNames!=null) { 
					item.setNames(fieldNames);
				}else {
					continue;
				}
				
				item.setView(strs[i].split("\t")[1].trim());
				
				if(strs[i].split("\t")[2]!=null && strs[i].split("\t")[2].trim()!="") {
					item.setReq(strs[i].split("\t")[2].trim());
				}else {
					item.setReq("否");
				}
				
				String num = BaseUtil.analyseArea(strs[i].split("\t")[3].trim());
				if(num.endsWith("d")) {
					item.setLengths("32");
					item.setTypes("d");
				}else {
					item.setLengths(num);
					item.setTypes("v");
				}
				
				item.setProp(type.equals("personal")?"基本属性":"业务属性");
				
				list.add(item);
				
			}else {
				Item item1 = initFileItem(strs[i].split("\t")[0].replace("：", "").trim());
				
				item1.setTitle(strs[i].split("\t")[0].replace("：", "").trim() + "文件名称");
				item1.setNames(PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(strs[i].split("\t")[0])));
				item1.setProp(type.equals("require")?"必须材料":"额外材料");
				item1.setReq(type.equals("require")?"是":"否");
				
				item = initFileItem(strs[i].split("\t")[0].replace("：", "").trim());
				item.setProp(type.equals("require")?"必须材料":"额外材料");
				item.setReq(type.equals("require")?"是":"否");
				
				list.add(item1);
				list.add(item);
			}
			
		}
		return list;
	}

}
