package com.cdc.util;

import com.cdc.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author Administrator
 *
 */
public class BaseUtil {
	
	private static String head;//事项中文件名称
	private static String title;//事项编码
	
	/**
	 * 生成指定位数的空格
	 * @param index 位数
	 * @return
	 */
	public static String createSpaces(int index) {
		StringBuffer spaces = new StringBuffer();
		String space = " ";
		for(int i=0; i<index; i++) {
			spaces.append(space);
		}
		return spaces.toString();
	}
	
	
	public static String analyseArea(String str) {
		String num = "";
		if(str.indexOf("男")!=-1 && str.indexOf("女")!=-1) {
			num = "32";
		}else if(str.indexOf("身份证")!=-1) {
			num = "32";
		}else if(str.indexOf("民族")!=-1) {
			num = "32";
		}else if(str.indexOf("时间控件")!=-1) {
			num = "32d";
		}else if(str.startsWith("字符，")) {
			num = str.substring(str.indexOf("字符，")+3, str.indexOf("个字"));
		}else if(str.startsWith("数字，")) {
			num = str.substring(str.indexOf("数字，")+3, str.indexOf("个字"));
		}
		return num;
	}
	
	/**
	 * 根据字段长度获取数据库对应长度
	 * @param lengths
	 * @return
	 */
	public static String getExtraLen(String lengths) {
		if(lengths.equals("32")) return "32";
		if(lengths.equals("30")) return "80";
		if(lengths.equals("100")) return "240";
		if(lengths.equals("512")) return "512";
		if(lengths.equals("1000")) return "2400";
		return String.valueOf((Integer.parseInt(lengths)*2 + Integer.parseInt(lengths)/3));
	}
	
	/**
	 * 大写首字母
	 * @param str 字符串
	 * @return
	 */
	public static String upCase(String str) {
		char[] cs = str.toCharArray();
		String letter = cs[0] + "";
		String upper = letter.toUpperCase();
		return upper + str.substring(1);
	}
	
	/**
	 * 小写首字母
	 * @param str
	 * @return
	 */
	public static String lowCase(String str) {
		char[] cs = str.toCharArray();
		String letter = cs[0] + "";
		String upper = letter.toLowerCase();
		return upper + str.substring(1);
	}
	
	/**
	 * 获取其他字段
	 * @param items
	 * @return
	 */
	public static List<Item> getOtherItems(List<Item> items) {
		List<Item> list = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			Item it = new Item();
			if(item.getLengths().equals("512")) {
				it.setNames("fj" + upCase(item.getNames()));
			}else {
				it.setNames(item.getNames());
			}
			it.setTitle(item.getTitle());
			it.setLengths(item.getLengths());
			it.setProp(item.getProp());
			it.setReq(item.getReq());
			it.setTypes(item.getTypes());
			it.setView(item.getView());
			
			list.add(it);
		}
		return list;
	}
	
	/**
	 * 获取sql字段
	 * @param items
	 * @return
	 */
	public static List<Item> getSqlItems(List<Item> items) {
		List<Item> list = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			Item it = new Item();
			if(item.getLengths().equals("512")) {
				it.setNames("fj_" + item.getNames());
			}else {
				it.setNames(item.getNames());
			}
			it.setTitle(item.getTitle());
			it.setLengths(item.getLengths());
			it.setProp(item.getProp());
			it.setReq(item.getReq());
			it.setTypes(item.getTypes());
			it.setView(item.getView());
			
			list.add(it);
		}
		return list;
	}
	
	/**
	 * 获得所有材料字段（路径集合）
	 * @param items
	 * @return
	 */
	public static List<Item> getRequire(List<Item> items) {
		List<Item> require = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getLengths().equals("512") && item.getNames().endsWith("Path")) {
				require.add(item);
			}
		}
		return require;
	}
	
	/**
	 * 获取基本信息字段集合
	 * @param items
	 * @return
	 */
	public static List<Item> getBaseInfo(List<Item> items) {
		List<Item> base = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getProp().equals("基本属性")) {
				base.add(item);
			}
		}
		return base;
	}
	
	/**
	 * 获取业务信息字段集合
	 * @param items
	 * @return
	 */
	public static List<Item> getBusInfo(List<Item> items) {
		List<Item> bus = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getProp().equals("业务属性")) {
				bus.add(item);
			}
		}
		return bus;
	}
	
	/**
	 * 获取必须材料字段（名称加路径）
	 * @param items
	 * @return
	 */
	public static List<Item> getBxFile(List<Item> items) {
		List<Item> list = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getProp().equals("必须材料")) {
				list.add(item);
			}
		}
		return list;
	}
	
	/**
	 * 获取所有材料字段（包括名称加路径）
	 * @param items
	 * @return
	 */
	public static List<Item> getAllFile(List<Item> items) {
		List<Item> list = new ArrayList<Item>();
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getLengths().equals("512")) {
				list.add(item);
			}
		}
		return list;
	}
	
	public static String removeBrace(String str) {
		if(str!=null && str.indexOf("（")!=-1 && str.indexOf("）")!=-1) {
			return str.substring(0, str.indexOf("（")) + str.substring(str.indexOf("）")+1, str.length());
		}else if(str!=null && str.indexOf("(")!=-1 && str.indexOf(")")!=-1) {
			return str.substring(0, str.indexOf("(")) + str.substring(str.indexOf(")")+1, str.length());
		}
		return str.replace("：", "");
	}
	
	/**
	 * 输出文件
	 * @param file
	 * @param str
	 * @throws Exception
	 */
	public static void write(File file, String str) throws Exception {
		OutputStream os = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(osw);
		pw.write(str);
		pw.flush();
		pw.close();
	}
	
	public static void writeUtf(File file, String str) throws Exception {
		OutputStream os = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
		PrintWriter pw = new PrintWriter(osw);
		pw.write(str);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 读取模板
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String temp) throws Exception {
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		while ((s = br.readLine()) != null) {
			return s;
		}
		return null;
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getTitle() {
		return title;
	}

	public static void setTitle(String title) {
		BaseUtil.title = title;
	}

	public static int getBxNum(List<Item> items) {
		return getBxFile(items).size();
	}

	public static String getHead() {
		return head;
	}

	public static void setHead(String head) {
		BaseUtil.head = head;
	}

}
