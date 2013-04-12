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
	 * 组装数据库脚本（老版本）
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
		sb.append(space1 + "--数据表主键" + "\n");
		sb.append(space1 + "resourceId" + BaseUtil.createSpaces(10)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "not null, "
				+ "\n");
		sb.append(space1 + "--基本信息" + "\n");

		String note = space1 + "--上传材料" + "\n";
		for (int i = 0; i < items.size(); i++) {
			boolean flag = true;
			Item item = items.get(i);
			if (item.getTypes().equals("v")) {
				if (item.getProp().equals("必须材料")
						|| item.getProp().equals("额外材料")) {
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

		sb.append(space1 + "--以下字段接口中用到" + "\n");
		sb.append(space1 + "creatorId" + BaseUtil.createSpaces(11)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --创建人ID" + "\n");
		sb.append(space1 + "modifyTime" + BaseUtil.createSpaces(10)
				+ "datetime" + BaseUtil.createSpaces(10) + "null," + " --修改时间"
				+ "\n");
		sb.append(space1 + "modifyId" + BaseUtil.createSpaces(12)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --修改标记位" + "\n");
		sb.append(space1 + "flagDelete" + BaseUtil.createSpaces(10)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --删除标记位" + "\n");
		sb.append(space1 + "syscode" + BaseUtil.createSpaces(13)
				+ "varchar(32)" + BaseUtil.createSpaces(7) + "null,"
				+ " --系统编码" + "\n");
		sb.append(space1 + "createTime" + BaseUtil.createSpaces(10)
				+ "datetime" + BaseUtil.createSpaces(10) + "null,"
				+ " --数据表创建时间" + "\n");
		sb.append(space1 + "applicant" + BaseUtil.createSpaces(11)
				+ "varchar(100)" + BaseUtil.createSpaces(6) + "null,"
				+ " --申请人" + "\n");
		sb.append(space1 + "company" + BaseUtil.createSpaces(13)
				+ "varchar(200)" + BaseUtil.createSpaces(6) + "null,"
				+ " --申请部门编号，(各业务表单9位确定值)" + "\n");
		sb.append(space1 + "deptid" + BaseUtil.createSpaces(14)
				+ "varchar(254)" + BaseUtil.createSpaces(6) + "null,"
				+ " --申请部门编号，(各业务表单9位确定值)" + "\n");
		sb.append(space1 + "itemid" + BaseUtil.createSpaces(14)
				+ "varchar(254)" + BaseUtil.createSpaces(6) + "null,"
				+ " --小项编号，(各业务表单3位确定值)" + "\n");
		sb.append(space1 + "pid" + BaseUtil.createSpaces(17) + "varchar(254)"
				+ BaseUtil.createSpaces(6) + "null," + " --事项编号，(各业务表单18位确定值)"
				+ "\n");
		sb
				.append(space1
						+ "wbsum"
						+ BaseUtil.createSpaces(15)
						+ "varchar(254)"
						+ BaseUtil.createSpaces(6)
						+ "null,"
						+ " --业务受理号，所有业务统一分配的唯一编号(W+当前日期（yyyyMMddHHmmss）+三位随机数),提交表单时调用统一的方法生成。"
						+ "\n");
		sb
				.append(space1
						+ "bxcl_id"
						+ BaseUtil.createSpaces(13)
						+ "varchar(2048)"
						+ BaseUtil.createSpaces(5)
						+ "null,"
						+ " --必须材料id(附件生成的文件名1_材料编号1|附件生成的文件名2_材料编号2)  (每个附件都包括32位id和材料编号,中间用下划线_隔开;不同附件之间用|隔开)"
						+ "\n");
		sb.append(space1 + "bxcl_name" + BaseUtil.createSpaces(11)
				+ "varchar(2048)" + BaseUtil.createSpaces(5) + "null,"
				+ " --必须材料名称(附件原文件名称1_材料名称1|附件原文件名称2_材料名称2)" + "\n");
		sb.append(space1 + "ewcl_id" + BaseUtil.createSpaces(13)
				+ "varchar(2048)" + BaseUtil.createSpaces(5) + "null,"
				+ " --额外材料id(附件生成的文件名1|附件生成的文件名2)" + "\n");
		sb.append(space1 + "ewcl_name" + BaseUtil.createSpaces(11)
				+ "varchar(2048)" + BaseUtil.createSpaces(5) + "null,"
				+ " --额外材料名称(附件原文件名称1|附件原文件名称2)" + "\n");
		sb.append(space1 + "lxr" + BaseUtil.createSpaces(17) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --联系人" + "\n");
		sb.append(space1 + "yddh" + BaseUtil.createSpaces(16) + "varchar(11)"
				+ BaseUtil.createSpaces(7) + "null," + " --移动电话" + "\n");
		sb.append(space1 + "zjlx" + BaseUtil.createSpaces(16) + "varchar(30)"
				+ BaseUtil.createSpaces(7) + "null," + " --证件类型" + "\n");
		sb.append(space1 + "sfzjhm" + BaseUtil.createSpaces(14) + "varchar(30)"
				+ BaseUtil.createSpaces(7) + "null," + " --证件号码" + "\n");
		sb.append(space1 + "dzxx" + BaseUtil.createSpaces(16) + "varchar(50)"
				+ BaseUtil.createSpaces(7) + "null," + " --电子信箱" + "\n");
		sb.append(space1 + "lxdh" + BaseUtil.createSpaces(16) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --联系电话" + "\n");
		sb.append(space1 + "jgmc" + BaseUtil.createSpaces(16) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --经办人" + "\n");
		sb.append(space1 + "bz" + BaseUtil.createSpaces(18) + "varchar(1024)"
				+ BaseUtil.createSpaces(5) + "null," + " --备注" + "\n");
		sb.append(space1 + "xmmc" + BaseUtil.createSpaces(16) + "varchar(20)"
				+ BaseUtil.createSpaces(7) + "null," + " --" + "\n");
		sb.append(space1 + "xb" + BaseUtil.createSpaces(18) + "varchar(10)"
				+ BaseUtil.createSpaces(7) + "null," + " --性别" + "\n");
		sb.append(space1 + "yzbm" + BaseUtil.createSpaces(16) + "varchar(10)"
				+ BaseUtil.createSpaces(7) + "null," + " --邮政编码" + "\n");
		sb.append(space1 + "dz" + BaseUtil.createSpaces(18) + "varchar(200)"
				+ BaseUtil.createSpaces(6) + "null," + " --地址" + "\n");
		sb.append("\n");
		sb.append(space1 + "constraint PK_zhj_" + BaseUtil.getTitle()
				+ " primary key (resourceId)" + "\n");
		sb.append(");");

		return sb.toString();
	}

	/**
	 * 初始化Sql脚本（新版本）
	 * 
	 * @param items
	 *            数据库字段集合
	 * @param temp
	 *            数据库脚本模板路径
	 * @return sql脚本字符串
	 * @throws Exception
	 *             IO异常
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
			
			if (s.trim().equals("-- 3.业务申请表单字段")) {
				sb.append(s + "\n");
				//基本信息
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
				//业务信息
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
			//上传材料
			if (s.trim().equals("-- 4.上传材料字段")) {
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
