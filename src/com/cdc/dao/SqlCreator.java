package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class SqlCreator {

	/**
	 * ��װ���ݿ�ű����ϰ汾��
	 * 
	 * @param items
	 * @return
	 */
	public static String assembleSql(List<Item> items) {
		StringBuffer sb = new StringBuffer();
		String space1 = BaseUtil.createSpaces(4);

		sb.append("--" + BaseUtil.getHead() + "\n");
		sb.append("create table zhj_");
		sb.append(BaseUtil.getTitle() + " (" + "\n");
		sb.append(space1 + "--���ݱ�����" + "\n");
		sb.append(space1 + "resourceId" + BaseUtil.createSpaces(10)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "not null, "
				+ "\n");
		sb.append(space1 + "--������Ϣ" + "\n");

		String note = space1 + "--�ϴ�����" + "\n";
		for (int i = 0; i < items.size(); i++) {
			boolean flag = true;
			Item item = items.get(i);
			if (item.getTypes().equals("v")) {
				if (item.getProp().equals("�������")
						|| item.getProp().equals("�������")) {
					sb.append(note);
					sb.append(space1
							+ item.getNames()
							+ BaseUtil.createSpaces(19 - item.getNames()
									.length()) + " varchar(512) " + space1
							+ " null, --" + item.getTitle() + "\n");
					note = "";
					flag = false;
				}
				if (flag) {
					sb.append(space1
							+ item.getNames()
							+ BaseUtil.createSpaces(19 - item.getNames()
									.length())
							+ " varchar("
							+ BaseUtil.getExtraLen(item.getLengths())
							+ ") "
							+ BaseUtil.createSpaces(7 - item.getLengths()
									.length()) + " null, --" + item.getTitle()
							+ "\n");
				}
			} else if (item.getTypes().equals("d")) {
				sb.append(space1
						+ item.getNames()
						+ BaseUtil.createSpaces(19 - item.getNames().length())
						+ " datetime "
						+ BaseUtil
								.createSpaces(10 - item.getLengths().length())
						+ " null, --" + item.getTitle() + "\n");
			} else {
				sb.append(space1 + item.getNames()
						+ BaseUtil.createSpaces(19 - item.getNames().length())
						+ item.getTypes() + "("
						+ BaseUtil.getExtraLen(item.getLengths()) + ") "
						+ BaseUtil.createSpaces(7 - item.getLengths().length())
						+ " null, --" + item.getTitle() + "\n");
			}
		}

		sb.append(space1 + "--�����ֶνӿ����õ�" + "\n");
		sb.append(space1 + "creatorId" + BaseUtil.createSpaces(11)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --������ID" + "\n");
		sb.append(space1 + "modifyTime" + BaseUtil.createSpaces(10)
				+ "datetime" + BaseUtil.createSpaces(10) + "null," + " --�޸�ʱ��"
				+ "\n");
		sb.append(space1 + "modifyId" + BaseUtil.createSpaces(12)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --�޸ı��λ" + "\n");
		sb.append(space1 + "flagDelete" + BaseUtil.createSpaces(10)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --ɾ�����λ" + "\n");
		sb.append(space1 + "syscode" + BaseUtil.createSpaces(13)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --ϵͳ����" + "\n");
		sb.append(space1 + "createTime" + BaseUtil.createSpaces(10)
				+ "datetime" + BaseUtil.createSpaces(10) + "null,"
				+ " --���ݱ���ʱ��" + "\n");
		sb.append(space1 + "applicant" + BaseUtil.createSpaces(11)
				+ "varchar(100)" + BaseUtil.createSpaces(6) + "null,"
				+ " --������" + "\n");
		sb.append(space1 + "company" + BaseUtil.createSpaces(13)
				+ "varchar(200)" + BaseUtil.createSpaces(6) + "null,"
				+ " --���벿�ű�ţ�(��ҵ���9λȷ��ֵ)" + "\n");
		sb.append(space1 + "deptid" + BaseUtil.createSpaces(14)
				+ "varchar(254)" + BaseUtil.createSpaces(6) + "null,"
				+ " --���벿�ű�ţ�(��ҵ���9λȷ��ֵ)" + "\n");
		sb.append(space1 + "itemid" + BaseUtil.createSpaces(14)
				+ "varchar(254)" + BaseUtil.createSpaces(6) + "null,"
				+ " --С���ţ�(��ҵ���3λȷ��ֵ)" + "\n");
		sb.append(space1 + "pid" + BaseUtil.createSpaces(17) + "varchar(254)"
				+ BaseUtil.createSpaces(6) + "null," + " --�����ţ�(��ҵ���18λȷ��ֵ)"
				+ "\n");
		sb
				.append(space1
						+ "wbsum"
						+ BaseUtil.createSpaces(15)
						+ "varchar(254)"
						+ BaseUtil.createSpaces(6)
						+ "null,"
						+ " --ҵ������ţ�����ҵ��ͳһ�����Ψһ���(W+��ǰ���ڣ�yyyyMMddHHmmss��+��λ�����),�ύ��ʱ����ͳһ�ķ������ɡ�"
						+ "\n");
		sb
				.append(space1
						+ "bxcl_id"
						+ BaseUtil.createSpaces(13)
						+ "varchar(2048)"
						+ BaseUtil.createSpaces(5)
						+ "null,"
						+ " --�������id(�������ɵ��ļ���1_���ϱ��1|�������ɵ��ļ���2_���ϱ��2)  (ÿ������������32λid�Ͳ��ϱ��,�м����»���_����;��ͬ����֮����|����)"
						+ "\n");
		sb.append(space1 + "bxcl_name" + BaseUtil.createSpaces(11)
				+ "varchar(2048)" + BaseUtil.createSpaces(5) + "null,"
				+ " --�����������(����ԭ�ļ�����1_��������1|����ԭ�ļ�����2_��������2)" + "\n");
		sb.append(space1 + "ewcl_id" + BaseUtil.createSpaces(13)
				+ "varchar(2048)" + BaseUtil.createSpaces(5) + "null,"
				+ " --�������id(�������ɵ��ļ���1|�������ɵ��ļ���2)" + "\n");
		sb.append(space1 + "ewcl_name" + BaseUtil.createSpaces(11)
				+ "varchar(2048)" + BaseUtil.createSpaces(5) + "null,"
				+ " --�����������(����ԭ�ļ�����1|����ԭ�ļ�����2)" + "\n");
		sb.append(space1 + "lxr" + BaseUtil.createSpaces(17) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --��ϵ��" + "\n");
		sb.append(space1 + "yddh" + BaseUtil.createSpaces(16) + "varchar(11)"
				+ BaseUtil.createSpaces(7) + "null," + " --�ƶ��绰" + "\n");
		sb.append(space1 + "zjlx" + BaseUtil.createSpaces(16) + "varchar(30)"
				+ BaseUtil.createSpaces(7) + "null," + " --֤������" + "\n");
		sb.append(space1 + "sfzjhm" + BaseUtil.createSpaces(14) + "varchar(30)"
				+ BaseUtil.createSpaces(7) + "null," + " --֤������" + "\n");
		sb.append(space1 + "dzxx" + BaseUtil.createSpaces(16) + "varchar(50)"
				+ BaseUtil.createSpaces(7) + "null," + " --��������" + "\n");
		sb.append(space1 + "lxdh" + BaseUtil.createSpaces(16) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --��ϵ�绰" + "\n");
		sb.append(space1 + "jgmc" + BaseUtil.createSpaces(16) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --������" + "\n");
		sb.append(space1 + "bz" + BaseUtil.createSpaces(18) + "varchar(1024)"
				+ BaseUtil.createSpaces(5) + "null," + " --��ע" + "\n");
		sb.append(space1 + "xmmc" + BaseUtil.createSpaces(16) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --" + "\n");
		sb.append(space1 + "xb" + BaseUtil.createSpaces(18) + "varchar(10)"
				+ BaseUtil.createSpaces(7) + "null," + " --�Ա�" + "\n");
		sb.append(space1 + "yzbm" + BaseUtil.createSpaces(16) + "varchar(10)"
				+ BaseUtil.createSpaces(7) + "null," + " --��������" + "\n");
		sb.append(space1 + "dz" + BaseUtil.createSpaces(18) + "varchar(200)"
				+ BaseUtil.createSpaces(6) + "null," + " --��ַ" + "\n");
		sb.append("\n");
		sb.append(space1 + "constraint PK_zhj_" + BaseUtil.getTitle()
				+ " primary key (resourceId)" + "\n");
		sb.append(");");

		return sb.toString();
	}

	/**
	 * ��ʼ��Sql�ű����°汾��
	 * 
	 * @param items
	 *            ���ݿ��ֶμ���
	 * @param temp
	 *            ���ݿ�ű�ģ��·��
	 * @return sql�ű��ַ���
	 * @throws Exception
	 *             IO�쳣
	 */
	public static String initSql(List<Item> items, String temp)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		String head = BaseUtil.getHead();
		String date = BaseUtil.getDate();
		String title = BaseUtil.getTitle();

		String space1 = BaseUtil.createSpaces(4);

		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		while ((s = br.readLine()) != null) {
			
			if (s.trim().equals("-- 3.ҵ��������ֶ�")) {
				sb.append(s + "\n");
				//������Ϣ
				List<Item> baseItems = BaseUtil.getBaseInfo(items);
				for (int i = 0; i < baseItems.size(); i++) {
					Item item = baseItems.get(i);
					if (item.getTypes().equals("v")) {
						sb.append(space1
								+ item.getNames()
								+ BaseUtil.createSpaces(20 - item.getNames()
										.length())
								+ "varchar("
								+ BaseUtil.getExtraLen(item.getLengths())
								+ ")"
								+ BaseUtil.createSpaces(7 - BaseUtil
										.getExtraLen(item.getLengths())
										.length()) + "null,"
								+ BaseUtil.createSpaces(2) + "--"
								+ item.getTitle() + "\n");
					} else if (item.getTypes().equals("d")) {
						sb.append(space1
								+ item.getNames()
								+ BaseUtil.createSpaces(20 - item.getNames()
										.length()) + "datetime"
								+ BaseUtil.createSpaces(8) + "null,"
								+ BaseUtil.createSpaces(2) + "--"
								+ item.getTitle() + "\n");
					}
				}
				//ҵ����Ϣ
				List<Item> busItems = BaseUtil.getBusInfo(items);
				for (int i = 0; i < busItems.size(); i++) {
					Item item = busItems.get(i);
					if (item.getTypes().equals("v")) {
						sb.append(space1
								+ item.getNames()
								+ BaseUtil.createSpaces(20 - item.getNames()
										.length())
								+ "varchar("
								+ BaseUtil.getExtraLen(item.getLengths())
								+ ")"
								+ BaseUtil.createSpaces(7 - BaseUtil
										.getExtraLen(item.getLengths())
										.length()) + "null,"
								+ BaseUtil.createSpaces(2) + "--"
								+ item.getTitle() + "\n");
					} else if (item.getTypes().equals("d")) {
						sb.append(space1
								+ item.getNames()
								+ BaseUtil.createSpaces(20 - item.getNames()
										.length()) + "datetime"
								+ BaseUtil.createSpaces(8) + "null,"
								+ BaseUtil.createSpaces(2) + "--"
								+ item.getTitle() + "\n");
					}
				}
				continue;
			}
			//�ϴ�����
			if (s.trim().equals("-- 4.�ϴ������ֶ�")) {
				sb.append(s + "\n");
				List<Item> all = BaseUtil.getAllFile(items);
				for (int i = 0; i < all.size(); i++) {
					Item item = all.get(i);
					sb.append(space1
							+ item.getNames()
							+ BaseUtil.createSpaces(20 - item.getNames()
									.length())
							+ "varchar("
							+ BaseUtil.getExtraLen(item.getLengths())
							+ ")"
							+ BaseUtil.createSpaces(7 - BaseUtil.getExtraLen(
									item.getLengths()).length()) + "null,"
							+ BaseUtil.createSpaces(2) + "--" + item.getTitle()
							+ "\n");
				}
				continue;
			}
			sb.append(s.replace("headTag", head).replace("dateTag", date)
					.replace("titleTag", title)
					+ "\n");
		}

		return sb.toString();
	}

}
