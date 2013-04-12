package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class JspCreator {
	private static String title1 = BaseUtil.getTitle();
	private static boolean timeZone = false;

	/**
	 * ����jsp�ļ����ϰ汾��
	 * @param str
	 * @param items
	 * @return
	 * @throws Exception
	 */
	public static String assembleJsp(String str, List<Item> items)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(str);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		while ((s = br.readLine()) != null) {
			boolean flag = true;
			if (flag && s.lastIndexOf("areaControl007") != -1) {
				sb.append(s.replace("areaControl007", title1) + "\n");
				flag = false;
			}
			if (flag && s.lastIndexOf("FormName007") != -1) {
				sb.append(s.replace("FormName007", title1 + "Form") + "\n");
				flag = false;
			}
			if (flag && s.lastIndexOf("saveXXXXXX") != -1) {
				sb.append(s.replace("saveXXXXXX", "save" + BaseUtil.upCase(title1)) + "\n");
				flag = false;
			}
			if (flag && s.trim().equals("//�жϱ�������Ƿ��ϴ�")) {
				List<Item> bxItems = BaseUtil.getBxFile(items);
				if (bxItems.size() >= 0) {
					sb.append(s + "\n");
					sb.append(br.readLine() + "\n");
					
					//var bxFileUpload=$("#bxFileUpload").val();
					String s1 = br.readLine();
					if (s1.lastIndexOf("bxFileUpload") != -1) {
						for (int i = 0; i < bxItems.size(); i = i + 2) {
							sb.append(s1.replace("bxFileUpload", bxItems.get(i)
									.getNames())
									+ "\n");
						}
					}
					String s2 = br.readLine() + "\n" + br.readLine() + "\n"
							+ br.readLine() + "\n" + br.readLine();
					if (s2.lastIndexOf("bxFileUpload") != -1) {
						for (int i = 0; i < bxItems.size(); i = i + 2) {
							sb.append(s2.replace("bxFileUpload",
									bxItems.get(i).getNames()).replace(
									"bxFileAlert", "���ϴ�" +
									bxItems.get(i).getTitle()
											.substring(0, bxItems.get(i).getTitle().length()-4))
									+ "\n");
						}
					}
				}
				flag = false;
			}
			if (flag && s.trim().equals("if(!isUpload()) return false;")) {
				if (BaseUtil.getBxNum(items) >= 0) {
					sb.append(s + "\n");
				}
				String temp = br.readLine();
				sb.append(temp.replace("FormName007", title1 + "Form").replace("XXXXXX", BaseUtil.upCase(title1)) + "\n");
				flag = false;
			}
			
			if (flag && s.trim().equals("<!-- base information -->")) {
				List<Item> list = BaseUtil.getBaseInfo(items);
				for (int j = list.size()-1; j >= 0; j--) {
					Item item = list.get(j);
					if (item.getNames().equals("name")
							|| item.getNames().equals("sex")
							|| item.getNames().equals("idCardNumber")
							|| item.getNames().equals("mz")
							|| item.getNames().equals("csrq")
							|| item.getNames().equals("lxdz")) {
						list.remove(item);
					}
				}
				
				/*StringBuffer sb1 = new StringBuffer();
				for(int i=0; i<list.size(); i++) {
					Item item = list.get(i);
					if(item.getView().equals("�ı���")) {
						sb1.append(BaseUtil.createSpaces(20) + "<tr>" + "\n");
						sb1.append(BaseUtil.createSpaces(24) + "");
					}
				}*/
				
				String s1 = br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine();
				for (int i = 0; i < list.size(); i = i + 2) {
					Item item = list.get(i);
					if (i == list.size() - 1) {
						sb.append(s1.replace("������Ϣ�ֶ�one", item.getNames())
								.replace("������Ϣ�ֶ�two", list.get(i).getNames())
								.replace("FormName007", title1 + "Form")
								.replace("XXXXXXone", list.get(i).getTitle())
								.replace("XXXXXXtwo", list.get(i).getTitle())
								+ "\n");
						break;
					}
					sb.append(s1.replace("������Ϣ�ֶ�one", item.getNames()).replace(
							"������Ϣ�ֶ�two", list.get(i + 1).getNames()).replace(
							"FormName007", title1 + "Form").replace(
							"XXXXXXone", list.get(i).getTitle()).replace(
							"XXXXXXtwo", list.get(i + 1).getTitle())
							+ "\n");
					
				}
				flag = false;
				
			}
			
			if (flag && s.trim().equals("<!-- bus information -->")) {
				List<Item> list = BaseUtil.getBusInfo(items);
				String s1 = br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine();
				for (int i = 0; i < list.size(); i = i + 2) {
					if (i == list.size() - 1) {
						sb.append(s1.replace("ҵ����Ϣone", list.get(i).getNames())
								.replace("ҵ����Ϣtwo", list.get(i).getNames())
								.replace("FormName007", title1 + "Form")
								.replace("XXXXXXone", list.get(i).getTitle())
								.replace("XXXXXXtwo", list.get(i).getTitle())
								+ "\n");
						break;
					}
					sb.append(s1.replace("ҵ����Ϣone", list.get(i).getNames())
							.replace("ҵ����Ϣtwo", list.get(i + 1).getNames())
							.replace("FormName007", title1 + "Form").replace(
									"XXXXXXone", list.get(i).getTitle())
							.replace("XXXXXXtwo", list.get(i + 1).getTitle())
							+ "\n");
					
				}
				flag = false;
			}
			
			if (flag && s.trim().equals("<!-- file uploading -->")) {
				List<Item> list = BaseUtil.getAllFile(items);
				String s1 = br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine();
				for (int i = 0; i < list.size(); i=i+2) {
					sb.append(s1.replace("fileNoticeFlg",
							list.get(i).getNames()).replace(
							"XXXXXXName",
							list.get(i).getTitle().substring(0,
									list.get(i).getTitle().length() - 4))
							+ "\n");
				}
				flag = false;
			}
			if (flag && s.trim().equals("//control of fileUpload start;")) {
				List<Item> list = BaseUtil.getRequire(items);
				String s1 = br.readLine() + "\n" + br.readLine() + "\n"
						+ br.readLine() + "\n" + br.readLine();
				String s2 = s1.substring(0, s1.indexOf("else if"));
				sb.append(s2.replace("applyForm", list.get(0).getNames()));
				String s3 = s1.substring(s1.indexOf("else if")) + "\n" + br.readLine() + "\n" + br.readLine() + "\n" + br.readLine();
				for (int i=1; i<list.size(); i++) {
					sb.append(s3.replace("fileNoticeFlg", list.get(i)
							.getNames()));
				}
				flag = false;
				sb.append("\n");
			}

			if (flag) {
				sb.append(s + "\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * ����jsp�ļ����°汾��
	 * @param items �ֶμ���
	 * @param temp ģ��·��
	 * @return
	 * @throws Exception
	 */
	public static String initJsp(List<Item> items, String temp) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		Map<String, String> viewMap = backUp();
		List<Item> bxItems = BaseUtil.getBxFile(items);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			//js������isUpload();
			if(s.trim().startsWith("var fieldName =")) {
				for(int i=0; i<bxItems.size(); i=i+2) {
					Item item = bxItems.get(i);
					sb.append(s.replace("fieldName", item.getNames()) + "\n");
				}
				String str = br.readLine() + "\n" + br.readLine()+ "\n" + br.readLine()+ "\n" + br.readLine()+ "\n" + br.readLine() + "\n";
				for(int i=0; i<bxItems.size(); i=i+2) {
					Item item = bxItems.get(i);
					sb.append(str.replace("fieldName", item.getNames()).replace("fieldHead", item.getTitle().substring(0, item.getTitle().length()-4)));
				}
				continue;
			}
			//������Ϣ
			if(s.trim().startsWith("<!-- base info end -->")) {
				List<Item> baseItems = BaseUtil.getBaseInfo(items);
				for(int i=baseItems.size()-1; i>=0; i--) {
					Item item = baseItems.get(i);
					String n = item.getNames();
					if(n.equals("name") || n.equals("sex") || n.equals("idCardNumber") || n.equals("mz") || n.equals("email") || n.equals("lxdz") || n.equals("yzbm") || n.equals("yddh")|| n.equals("csrq")) {
						baseItems.remove(item);
					}
				}
				assemble(baseItems, viewMap, sb);
				continue;
			}
			//ҵ����Ϣ
			if(s.trim().equals("<!-- bus info start -->")) {
				List<Item> busItems = BaseUtil.getBusInfo(items);
				assemble(busItems, viewMap, sb);
				continue;
			}
			//�ϴ�����
			if(s.trim().equals("<!-- file upload start -->")) {
				String fileTemp = "";
				String fileTempExt = "";
				for(int x=0; x<11; x++) {
					fileTemp += br.readLine() + "\n";
				}
				for(int y=0; y<14; y++) {
					fileTempExt += br.readLine() + "\n";
				}
				List<Item> fileItems = BaseUtil.getAllFile(items);
				for(int i=0; i<fileItems.size(); i=i+2) {
					Item item = fileItems.get(i);
					if(item.getProp().equals("�������")) {
						if(item.getTitle().length()>=14) {
							int x = item.getTitle().length();
							sb.append(fileTempExt.replace("fieldTitle", item.getTitle().substring(0, item.getTitle().length()-4)).replace("fieldName", item.getNames()) + "\n");
						}else {
							sb.append(fileTemp.replace("fieldTitle", item.getTitle().substring(0, item.getTitle().length()-4)).replace("fieldName", item.getNames()) + "\n");
						}
					}else {
						String fileTemp2 = "";
						if(item.getTitle().length()<14) {
							fileTemp2 = fileTemp.substring(0, fileTemp.indexOf("<label>")) + fileTemp.substring(fileTemp.lastIndexOf("<br />"), fileTemp.length());
						}else {
							int y = item.getTitle().length();
							fileTemp2 = fileTempExt.substring(0, fileTempExt.indexOf("<label>")) + fileTempExt.substring(fileTempExt.lastIndexOf("<br />"), fileTempExt.length());
						}
						sb.append(fileTemp2.replace("fieldTitle", item.getTitle().substring(0, item.getTitle().length()-4)).replace("fieldName", item.getNames()) + "\n");
					}
					
				}
				continue;
			}
			sb.append(s.replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())) + "\n");
		}
		String jsp = sb.toString();
		if(timeZone) {
			String callBack = addCallBack(items);
			jsp = jsp.replace("<!-- callBack area -->", callBack);
		}
		return jsp;
	}
	
	/**
	 * ���callBack����
	 * @param items
	 * @return
	 */
	public static String addCallBack(List<Item> items) {
		String space1 = BaseUtil.createSpaces(4);
		String space2 = BaseUtil.createSpaces(8);
		String space3 = BaseUtil.createSpaces(12);
		String space4 = BaseUtil.createSpaces(16);
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getView().equals("�ı���t")) {
				Item item1 = items.remove(i+1);
				return "\n" + space1 + "function callBack(){" + "\n"
				+ space2 + "var start=$(\"#" + item.getNames() + "\").val();" + "\n"
				+ space2 + "var end=$(\"#" + item1.getNames() + "\").val();" + "\n"
				+ space2 + "if(start!=\"\" && end!=\"\"){" + "\n"
				+ space3 + "var startArr=start.split(\"-\");" + "\n"
				+ space3 + "var endArr=end.split(\"-\");" + "\n"
				+ space3 + "var startDate=new Date(startArr[0],startArr[1],startArr[2]);" + "\n"
				+ space3 + "var endDate=new Date(endArr[0],endArr[1],endArr[2]);" + "\n"
				+ space3 + "if(startDate.getTime()>endDate.getTime()){" + "\n"
				+ space4 + "alert(\"" + item.getTitle().substring(0, item.getTitle().length()-4) + "ʱ�䲻��ȷ\");" + "\n"
				+ space4 + "$(\"#" + item.getNames() + "\").val(\"\");" + "\n"
				+ space4 + "$(\"#" + item1.getNames() + "\").val(\"\");" + "\n"
				+ space4 + "return;" + "\n"
				+ space3 + "}" + "\n"
				+ space2 + "}" + "\n"
				+ space1 + "}" + "\n";
			}
		}
		return null;
	}
	
	/**
	 * �����ֶ���ͼ����ƴ�Ӳ�ͬ��ģ���ַ���
	 * @param baseItems �ֶμ���
	 * @param viewMap ����ģ���ַ�����Map
	 * @param sb �ַ���������
	 */
	public static void assemble(List<Item> baseItems, Map<String, String> viewMap, StringBuffer sb) {
		StringBuffer sb1 = new StringBuffer(BaseUtil.createSpaces(20) + "<tr>" + "\n");
		int index = 0;
		boolean flag = false;
		
		for(int j=0; j<baseItems.size(); j++) {
			Item item = baseItems.get(j);
			if(item.getTypes().equals("v")) {
				if(item.getView().equals("�ı���2") || item.getView().equals("�ı���3") || item.getView().equals("�ı���s")) {
					sb.append(BaseUtil.createSpaces(20) + "<tr>" + "\n" + viewMap.get(item.getView()).replace("fieldTitle", item.getTitle())
							.replace("fieldName", item.getNames()).replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())) + BaseUtil.createSpaces(20) + "</tr>" + "\n");
//					continue;
				}else {
					sb1.append(viewMap.get(item.getView()).replace("fieldTitle", item.getTitle()).replace("fieldName", item.getNames()).replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())));
					index++;
					flag = true;
				}
			}else {
				if(item.getView().equals("�ı���s")) {
					sb.append(BaseUtil.createSpaces(20) + "<tr>" + "\n" + viewMap.get(item.getView()).replace("fieldTitle", item.getTitle())
							.replace("fieldName", item.getNames()).replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())) + BaseUtil.createSpaces(20) + "</tr>" + "\n");
				}else {
					if(item.getView().equals("�ı���t")) {
						timeZone = true;
						Item item2 = baseItems.remove(j+1);
						String str = viewMap.get("��������").replace("fieldTitleTag", item.getTitle().substring(0, item.getTitle().length()-4))
									.replace("fieldTitle", item.getTitle()).replace("fieldName", item.getNames()).replace("titleTag1", BaseUtil.getTitle())
									.replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())).replace("fieldTempName", item2.getNames()).replace("fieldTempTitle", item2.getTitle());
						sb1.append(str);
					}else {
						sb1.append(viewMap.get("���ڿؼ�").replace("fieldTitle", item.getTitle()).replace("fieldName", item.getNames()).replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())));
					}
					index++;
					flag = true;
				}
			}
			
			if(index!=0 && index%2==0 && flag) {
				sb.append(sb1.toString() + BaseUtil.createSpaces(20) + "</tr>" + "\n");
				sb1 = new StringBuffer(BaseUtil.createSpaces(20) + "<tr>" + "\n");
				index = 0;
				continue;
			}
			
			if(j==baseItems.size()-1 && flag) {
				sb.append(sb1.toString() + BaseUtil.createSpaces(20) + "</tr>" + "\n");
			}
		}
	}
	
	/**
	 * ��ȡģ���ַ���
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> backUp() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer ss = new StringBuffer();
		InputStream is = new FileInputStream("E:\\abc\\version2.0\\viewBack.jsp");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String s = null;
		while ((s = br.readLine()) != null) {
			if(s.trim().equals("(�ı���1)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("�ı���", temp);
				map.put("�ı���s", temp);
				continue;
			}
			if(s.trim().equals("(�ı���2)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("�ı���2", temp);
				continue;
			}
			if(s.trim().equals("(�ı���3)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("�ı���3", temp);
				continue;
			}
			if(s.trim().equals("(��ѡ��ť)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("��ѡ��ť", temp);
				continue;
			}
			if(s.trim().equals("(���ڿؼ�)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("���ڿؼ�", temp);
				continue;
			}
			if(s.trim().equals("(�����б�)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("�����б�", temp);
				continue;
			}
			if(s.trim().equals("(��ѡ��)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine() + "\n" + BaseUtil.createSpaces(24) + br.readLine() + "\n";
				map.put("��ѡ��", temp);
				continue;
			}
			if(s.trim().equals("(��������)")) {
				String temp = BaseUtil.createSpaces(24) + br.readLine().trim() + "\n" + BaseUtil.createSpaces(24) + br.readLine().trim() + "\n" 
							+ BaseUtil.createSpaces(28) + br.readLine().trim() + "\n" + BaseUtil.createSpaces(32) + br.readLine().trim() + "\n" 
							+ BaseUtil.createSpaces(28) + br.readLine().trim() + "\n" + BaseUtil.createSpaces(28) + br.readLine().trim() + "\n" 
							+ BaseUtil.createSpaces(32) + br.readLine().trim() + "\n" + BaseUtil.createSpaces(24) + br.readLine().trim() + "\n";
				map.put("��������", temp);
				continue;
			}
		}
		return map;
	}

}
