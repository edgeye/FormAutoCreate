package com.cdc.service;

import java.util.ArrayList;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;
import com.cdc.util.PinyinUtil;

public class Dismantle {
	
	/**
	 * ��ȡ�ֶμ��ϣ��汾1��
	 * @param names ����
	 * @param types ����
	 * @param needs ������
	 * @param areas ȡֵ��Χ
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
	 * �ֽ��ַ������汾1��
	 * @param names ����
	 * @param types ����
	 * @param needs ������
	 * @param areas ȡֵ��Χ
	 * @param index �����±�
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
				item.setProp("��������");
			}else if(index==1) {
				item.setProp("ҵ������");
			}else if(index==2) {
				item.setProp("�������");
			}else if(index==3) {
				item.setProp("�������");
			}
			
			if(index==0 || index==1) {
				item.setTitle(baseInfoNames[i].trim());
				
				String fieldNames = initBaseNames(baseInfoNames[i].trim());
				if(fieldNames!=null) item.setNames(fieldNames);
				
				item.setView(baseInfoViews[i].trim());
				
				if(baseInfoReqs[i]!=null && baseInfoReqs[i].trim()!="") {
					item.setReq(baseInfoReqs[i].trim());
				}else {
					item.setReq("��");
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
				item1.setTitle(baseInfoNames[i].trim() + "�ļ�����");
				item1.setNames(PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(baseInfoNames[i])));
				item.setProp(index==2?"�������":"�������");
				item.setReq(index==2?"��":"��");
				
				item = initFileItem(baseInfoNames[i].trim());
				item.setProp(index==2?"�������":"�������");
				item.setReq(index==2?"��":"��");
				
				items.add(item1);
				items.add(item);
			}
		}
		
		return items;
	}
	
	/**
	 * ��ֵ�����ֶ�����
	 * @param title
	 * @return
	 */
	public static Item initFileItem(String title) {
		Item item = new Item();
		item.setTitle(title + "�ϴ�·��");
		item.setNames(PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(title)) + "Path");
		item.setView("�ı���");
		item.setLengths("512");
		item.setTypes("v");
		return item;
	}
	
	/**
	 * �����ֶ�Ӣ������
	 * @param str �ֶ���������
	 * @return
	 */
	public static String initBaseNames(String str) {
		if(str.equals("����")) {
			return"name";
		}else if(str.equals("�Ա�")) {
			return"sex";
		}else if(str.equals("���֤����")) {
			return"idCardNumber";
		}else if(str.equals("�����ʼ�")) {
			return"email";
		}else if(str.equals("����")) {
			return"mz";
		}else if(str.equals("��������")) {
			return"csrq";
		}else if(str.equals("�ֻ�����")) {
			return null;
		}else if(str.equals("��������")) {
			return null;
		}else {
			return PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(str));
		}
	}
	
	public static List<Item> convert(String base) {
		List<Item> list = new ArrayList<Item>();
		
		String baseInfo = base.substring(base.indexOf("����"), base.indexOf("��������_�ڶ���"));
		String fileInfo = base.substring(base.indexOf("��������_�ڶ���\r\n")+"��������_�ڶ���\r\n".length(), base.length());
		
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
	 * ��ȡ�ֶμ���
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
					item.setReq("��");
				}
				
				String num = BaseUtil.analyseArea(strs[i].split("\t")[3].trim());
				if(num.endsWith("d")) {
					item.setLengths("32");
					item.setTypes("d");
				}else {
					item.setLengths(num);
					item.setTypes("v");
				}
				
				item.setProp(type.equals("personal")?"��������":"ҵ������");
				
				list.add(item);
				
			}else {
				Item item1 = initFileItem(strs[i].split("\t")[0].replace("��", "").trim());
				
				item1.setTitle(strs[i].split("\t")[0].replace("��", "").trim() + "�ļ�����");
				item1.setNames(PinyinUtil.getPinYinHeadChar(BaseUtil.removeBrace(strs[i].split("\t")[0])));
				item1.setProp(type.equals("require")?"�������":"�������");
				item1.setReq(type.equals("require")?"��":"��");
				
				item = initFileItem(strs[i].split("\t")[0].replace("��", "").trim());
				item.setProp(type.equals("require")?"�������":"�������");
				item.setReq(type.equals("require")?"��":"��");
				
				list.add(item1);
				list.add(item);
			}
			
		}
		return list;
	}

}
