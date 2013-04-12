package com.cdc.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cdc.model.Item;
import com.cdc.util.BaseUtil;

public class ActionCreator {
	
	public static String assembleAction(List<Item> items) {
		String title1 = BaseUtil.getTitle();
		String title2 = BaseUtil.upCase(title1);
		StringBuffer sb = new StringBuffer();
		String space1 = BaseUtil.createSpaces(4);
		String space2 = BaseUtil.createSpaces(8);
		String space3 = BaseUtil.createSpaces(12);
		String space4 = BaseUtil.createSpaces(16);
		String space5 = BaseUtil.createSpaces(20);
		
		sb.append("package com.gdcn.cms.wsbs." + title1 + ".web.action;" + "\n");
		sb.append("\n");
		sb.append("import java.util.ArrayList;" + "\n"
				+ "import java.util.Date;" + "\n"
				+ "import java.util.List;" + "\n");
		sb.append("\n");
		sb.append("import javax.servlet.http.HttpServletRequest;" + "\n"
				+ "import javax.servlet.http.HttpServletResponse;" + "\n"
				+ "import javax.servlet.http.HttpSession;" + "\n");
		sb.append("\n");
		sb.append("import org.apache.commons.beanutils.BeanUtils;" + "\n"
				+ "import org.apache.commons.lang.StringUtils;" + "\n"
				+ "import org.apache.struts.action.ActionForm;" + "\n"
				+ "import org.apache.struts.action.ActionForward;" + "\n"
				+ "import org.apache.struts.action.ActionMapping;" + "\n");
		sb.append("\n");
		sb.append("import com.gdcn.bpaf.common.base.BaseStrutsAction;" + "\n"
				+ "import com.gdcn.bpaf.common.helper.Pagelet;" + "\n"
				+ "import com.gdcn.bpaf.common.helper.WebHelper;" + "\n"
				+ "import com.gdcn.bpaf.common.taglib.SplitPage;" + "\n"
				+ "import com.gdcn.bpaf.security.model.LogonVO;" + "\n"
				+ "import com.gdcn.cms.sxbl.model.Sxgl;" + "\n"
				+ "import com.gdcn.cms.sxbl.service.SxglService;" + "\n"
				+ "import com.gdcn.cms.wsbs.ws.client.service.CmsBsdtWSService;" + "\n"
				+ "import com.gdcn.cms.wsbs." + title1 + ".model." + title2 + ";" + "\n"
				+ "import com.gdcn.cms.wsbs." + title1 + ".service.I" + title2 + "Service;" + "\n"
				+ "import com.gdcn.cms.wsbs." + title1 + ".web.form." + title2 + "Form;" + "\n"
				+ "import com.gdcn.components.appauth.common.helper.DictionaryHelper;" + "\n"
				+ "import com.gdcn.components.appauth.common.vo.DictHelperVo;" + "\n");
		
		//注释内容
		sb.append("/**" + "\n"
				+ "* " + BaseUtil.getHead() + "\n" 
				+ "* @author yaoguoc" + "\n"
				+ "*" + "\n"
				+ "*/" + "\n");
		
		sb.append("public class " + title2 + "Action extends BaseStrutsAction{" + "\n"
				+ space1 + "private SxglService sxglService;" + "\n"
				+ space1 + "private CmsBsdtWSService cmsBsdtWSService;" + "\n"
				+ space1 + "private I" + title2 + "Service " + title1 + "Service;" + "\n");
		sb.append("\n");
		sb.append(space1 + "public void set" + title2 + "Service(I" + title2 + "Service " + title1 + "Service) {" + "\n"
				+ space2 + "this." + title1 + "Service = " + title1 + "Service;" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		sb.append(space1 + "public void setSxglService(SxglService sxglService) {" + "\n"
				+ space2 + "this.sxglService = sxglService;" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		sb.append(space1 + "public void setCmsBsdtWSService(CmsBsdtWSService cmsBsdtWSService) {" + "\n"
				+ space2 + "this.cmsBsdtWSService = cmsBsdtWSService;" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		sb.append(space1 + "public static final String F_LIST_JHSYZ = \"\";" + "\n");
		sb.append("\n");
		
		//list方法
		sb.append(space1 + "public ActionForward list" + title2 + "(ActionMapping mapping, ActionForm form," + "\n"
				+ space3 + "HttpServletRequest request, HttpServletResponse response)" + "\n"
				+ space3 + "throws Exception {" + "\n"
				+ space2 + "" + title2 + "Form " + title1 + "Form = (" + title2 + "Form) form;" + "\n"
				+ space2 + "HttpSession session = request.getSession();" + "\n"
				+ space2 + "String cms_syscode = session.getAttribute(\"cms_syscode\")==null?\"\":session.getAttribute(\"cms_syscode\").toString();" + "\n"
				+ space2 + "int pageIndex=super.getPageIndex(request);" + "\n"
				+ space2 + "int pageSize=super.getPageSize(request);" + "\n"
				+ space2 + "\n"
				+ space2 + "if(StringUtils.isNotBlank(cms_syscode)){" + "\n"
				+ space3 + "" + title1 + "Form.setSysCode(cms_syscode);" + "\n"
				+ space2 + "}" + "\n"
				+ space2 + "\n"
				+ space2 + "Pagelet pagelet=" + title1 + "Service.listAll(" + title1 + "Form, pageIndex, pageSize);" + "\n"
				+ space2 + "SplitPage.setTotalRecord(request, pagelet.getTotalRecord());" + "\n"
				+ space2 + "request.setAttribute(\"list" + title2 + "\", pagelet.getPageList());" + "\n"
				+ space2 + "\n"
				+ space2 + "return mapping.findForward(\"\");" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		
		//编辑方法
		sb.append(space1 + "@SuppressWarnings(\"unchecked\")" + "\n"
				+ space1 + "public ActionForward edit" + title2 + "(ActionMapping mapping, ActionForm form," + "\n"
				+ space3 + "HttpServletRequest request, HttpServletResponse response)" + "\n"
				+ space3 + "throws Exception {" + "\n"
				+ space2 + "" + title2 + "Form " + title1 + "Form = (" + title2 + "Form) form;" + "\n"
				+ space2 + "" + title2 + " " + title1 + " = new " + title2 + "();" + "\n"
				+ space2 + "String resourceId=" + title1 + "Form.getResourceId();" + "\n"
				+ space2 + "LogonVO logon=WebHelper.getLogon(request);" + "\n"
				+ space2 + "String navCode=request.getParameter(\"nav_id\");" + "\n"
				+ space2 + "String params=request.getQueryString();" + "\n"
				+ space2 + "\n"
				+ space2 + "if(StringUtils.isNotBlank(resourceId)){" + "\n"
				+ space3 + "" + title1 + "=" + title1 + "Service.getEntity(resourceId);" + "\n"
				+ space3 + "if(" + title1 + "!=null){" + "\n"
				+ space4 + "BeanUtils.copyProperties(" + title1 + "Form, " + title1 + ");" + "\n"
				+ space3 + "}" + "\n"
				+ space3 + "" + title1 + "Form.setModifyId(logon==null?\"\":logon.getUserid());" + "\n"
				+ space2 + "}else{" + "\n"
				+ space3 + "HttpSession session = request.getSession();" + "\n"
				+ space3 + "String cms_syscode = session.getAttribute(\"cms_syscode\")==null?\"\":session.getAttribute(\"cms_syscode\").toString();" + "\n"
				+ space3 + "" + title1 + "Form.setSysCode(cms_syscode);" + "\n"
				+ space3 + "" + title1 + "Form.setCreatorId(logon==null?\"\":logon.getUserid());" + "\n"
				+ space3 + "" + title1 + "Form.setWbsum(cmsBsdtWSService.generateWBSNum());" + "\n"
				+ space2 + "}" + "\n"
				+ space2 + "\n"
				+ space2 + "super.saveToken(request);" + "\n"
				+ space2 + "List<DictHelperVo> list = new ArrayList<DictHelperVo>();" + "\n"
				+ space2 + "list = DictionaryHelper.getDictionaryByTypeCode(\"GJJ_SQTQ\");" + "\n"
				+ space2 + "Sxgl sx = sxglService.findSxglByCode(navCode);" + "\n"
				+ space2 + "request.setAttribute(\"" + title1 + "List\", list);" + "\n"
				+ space2 + "request.setAttribute(\"sxgl\", sx);" + "\n"
				+ space2 + "request.setAttribute(\"" + title1 + "Form\", " + title1 + "Form);" + "\n"
				+ space2 + "\n"
				+ space2 + "String forward = request.getContextPath() + \"/getContent.tiles?\"" + "\n"
				+ space4 + "+ params;" + "\n"
				+ space2 + "request.setAttribute(\"forward_url\", \"/biz/" + title1 + "_form.jsp\");" + "\n"
				+ space2 + "request.setAttribute(\"left\", \"zxsb\");" + "\n"
				+ space2 + "request.getRequestDispatcher(forward).forward(request, response);" + "\n"
				+ space2 + "return null;" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		
		//保存方法
		sb.append(space1 + "public ActionForward save" + title2 + "(ActionMapping mapping, ActionForm form," + "\n"
				+ space3 + "HttpServletRequest request, HttpServletResponse response)" + "\n"
				+ space3 + "throws Exception {" + "\n"
				+ space2 + "" + title2 + "Form " + title1 + "Form = (" + title2 + "Form) form;" + "\n"
				+ space2 + "String resourceId=" + title1 + "Form.getResourceId();" + "\n"
				+ space2 + "" + title2 + " " + title1 + " = new " + title2 + "();" + "\n"
				+ space2 + "String params=request.getQueryString();" + "\n"
				+ space2 + "\n"
				+ space2 + "if(super.isTokenValid(request,true)){" + "\n"
				+ space3 + "if(StringUtils.isNotBlank(resourceId)){" + "\n"
				+ space4 + "LogonVO logon=WebHelper.getLogon(request);" + "\n"
				+ space4 + "" + title1 + "=" + title1 + "Service.getEntity(resourceId);" + "\n"
				+ space4 + "BeanUtils.copyProperties(" + title1 + ", " + title1 + "Form);" + "\n"
				+ space4 + "" + title1 + ".setModifyTime(new Date());" + "\n"
				+ space4 + "" + title1 + ".setModifyId(logon.getUserid());" + "\n"
				+ space4 + "" + title1 + "Service.updateEntity(" + title1 + ");" + "\n"
				+ space3 + "}else{" + "\n"
				+ space4 + "HttpSession session = request.getSession();" + "\n"
				+ space4 + "String cms_syscode = session.getAttribute(\"cms_syscode\")==null?\"\":session.getAttribute(\"cms_syscode\").toString();" + "\n"
				+ space4 + "BeanUtils.copyProperties(" + title1 + ", " + title1 + "Form);" + "\n"
				+ space4 + "" + title1 + ".setCreateTime(new Date());" + "\n"
				+ space4 + "if(StringUtils.isNotBlank(cms_syscode)){" + "\n"
				+ space5 + "" + title1 + ".setSysCode(cms_syscode);" + "\n"
				+ space4 + "}" + "\n"
				+ space4 + "" + title1 + "=copyWSProp(" + title1 + ");" + "\n"
				+ space4 + "" + title1 + ".setCsrq(" + title1 + "Form.getCsrq());" + "\n");
		
		//给时间字段赋值	
		for(int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			if(item.getTypes().equals("d") && !item.getNames().equals("csrq")) {
				sb.append(space4 + "" + title1 + ".set" + BaseUtil.upCase(item.getNames()) + "(" + title1 + "Form.get" + BaseUtil.upCase(item.getNames()) + "());" + "\n");
			}
		}
		
		sb.append(space4 + "" + title1 + "Service.createEntity(" + title1 + ");" + "\n"
				+ space3 + "}" + "\n"
				+ space3 + "" + "\n"
				+ space3 + "String result = cmsBsdtWSService.approveBusiness(" + title1 + ");" + "\n"
				+ space3 + "System.out.println(\"RESULT:\" + result);" + "\n"
				+ space3 + "" + "\n"			
				+ space3 + "BeanUtils.copyProperties(" + title1 + "Form, " + title1 + ");" + "\n"
				+ space2 + "}" + "\n"
				+ space2 + "\n"
				+ space2 + "String forward = request.getContextPath() + \"/getContent.tiles?\"" + "\n"
				+ space4 + "+ params;" + "\n"
				+ space2 + "request.setAttribute(\"forward_url\", \"/common/childpage/zxsb_finish.jsp\");" + "\n"
				+ space2 + "request.setAttribute(\"left\", \"zxsb\");" + "\n"
				+ space2 + "request.setAttribute(\"recordNum\", " + title1 + "Form.getWbsum());" + "\n"
				+ space2 + "request.getRequestDispatcher(forward).forward(request, response);" + "\n"
				+ space2 + "return null;" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		
		//材料赋值
		List<Item> require = BaseUtil.getAllFile(items);//获取所有材料字段
		sb.append(space1 + "public " + title2 + " copyWSProp(" + title2 + " " + title1 + "){" + "\n"
				+ space2 + "if (" + title1 + ".get" + BaseUtil.upCase(require.get(0).getNames()) + "() != null && " + title1 + ".get" + BaseUtil.upCase(require.get(0).getNames()) + "().trim().length() > 0) {" + "\n"
				+ space3 + "" + title1 + ".setBxclId(cmsBsdtWSService.clIdWrapper(" + "\n"
				+ space4 + "new String[] { " + title1 + ".get" + BaseUtil.upCase(require.get(0).getNames()) + "() }," + "\n"
				+ space4 + "\"xxxxxxxxxxxxxxxxx-xxx\")" + "\n"
				+ space4 + "+ \"|\");" + "\n"
				+ space3 + "" + title1 + ".setBxclName(cmsBsdtWSService.clNameWrapper(" + "\n"
				+ space4 + "new String[] { " + title1 + ".get" + BaseUtil.upCase(require.get(1).getNames()) + "() }," + "\n"
				+ space4 + "\"" + BaseUtil.upCase(require.get(0).getTitle()) + "\")" + "\n"
				+ space4 + "+ \"|\");" + "\n"
				+ space2 + "}" + "\n");
		sb.append("\n");
		
		
		for(int i=2; i<require.size(); i=i+2) {
			String param1 = BaseUtil.upCase(require.get(i).getNames());
			String param2 = BaseUtil.upCase(require.get(i+1).getNames());
			sb.append(space2 + "" + title1 + "=setEwcl(" + title1 + ", " + title1 + ".get" + param1 + "(), " + title1 + ".get" + param2 + "());" + "\n");
		}
		
		sb.append(space2 + "String ewclIdTmp=" + title1 + ".getEwclId();" + "\n"
				+ space2 + "String ewclNameTmp=" + title1 + ".getEwclName();" + "\n"
				+ space2 + "if(ewclIdTmp!=null && !ewclIdTmp.trim().equals(\"\") && ewclIdTmp.indexOf(\"|\")!=-1){" + "\n"
				+ space3 + "ewclIdTmp=ewclIdTmp.substring(0,ewclIdTmp.length()-1);" + "\n"
				+ space3 + "ewclNameTmp=ewclNameTmp.substring(0,ewclNameTmp.length()-1);" + "\n"
				+ space2 + "}" + "\n"
				+ space2 + "\n"
				+ space2 + "" + title1 + ".setEwclId(ewclIdTmp);" + "\n"
				+ space2 + "" + title1 + ".setEwclName(ewclNameTmp);" + "\n"
				+ space2 + "\n"
				+ space2 + "" + title1 + ".setApplicant(" + title1 + ".getName());" + "\n"
				+ space2 + "" + title1 + ".setLxr(" + title1 + ".getName());" + "\n"
				+ space2 + "" + title1 + ".setLxdh(" + title1 + ".getYddh());" + "\n"
				+ space2 + "" + title1 + ".setZjlx(\"身份证\");" + "\n"
				+ space2 + "" + title1 + ".setSfzjhm(" + title1 + ".getIdCardNumber());" + "\n"
				+ space2 + "" + title1 + ".setDzxx(" + title1 + ".getEmail());" + "\n"
				+ space2 + "" + title1 + ".setJgmc(\"\");" + "\n"
				+ space2 + "" + title1 + ".setBz(\"\");" + "\n"
				+ space2 + "" + title1 + ".setXmmc(\"\");" + "\n"
				+ space2 + "" + title1 + ".setXb(" + title1 + ".getSex());" + "\n"
				+ space2 + "" + title1 + ".setYzbm(" + title1 + ".getYzbm());" + "\n"
				+ space2 + "" + title1 + ".setDz(" + title1 + ".getLxdz());" + "\n"
				+ space2 + "return " + title1 + ";" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		
		//额外材料赋值
		sb.append(space1 + "private " + title2 + " setEwcl(" + title2 + " " + title1 + ", String ewclId,String ewclName){" + "\n"
				+ space2 + "if(ewclId!=null && !ewclId.trim().equals(\"\")){" + "\n"
				+ space3 + "if(" + title1 + ".getEwclId()!=null && !" + title1 + ".getEwclId().trim().equals(\"\")){" + "\n"
				+ space4 + "" + title1 + ".setEwclId(" + title1 + ".getEwclId()+ewclId+\"|\");" + "\n"
				+ space4 + "" + title1 + ".setEwclName(" + title1 + ".getEwclName()+ ewclName+\"|\");" + "\n"
				+ space3 + "}" + "\n"
				+ space3 + "else{" + "\n"
				+ space4 + "" + title1 + ".setEwclId(ewclId+\"|\");" + "\n"
				+ space4 + "" + title1 + ".setEwclName(ewclName+\"|\");" + "\n"
				+ space3 + "}" + "\n"
				+ space2 + "}" + "\n"
				+ space2 + "return " + title1 + ";" + "\n"
				+ space1 + "}" + "\n");
		sb.append("\n");
		sb.append("}");
		
		return sb.toString();
	}
	
	public static String initAction(List<Item> items, String temp) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(temp);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		List<Item> fileItems = BaseUtil.getAllFile(items);
		List<Item> boos = new ArrayList<Item>();
		
		String s = null;
		while ((s = br.readLine()) != null) {
			if(s.trim().equals("// TODO 各业务特殊处理在这里补充")) {
				sb.append(s + "\n");
				for(int i=0; i<items.size(); i++) {
					Item item = items.get(i);
					if(item.getTypes().equals("d")) {
						sb.append(BaseUtil.createSpaces(16) + BaseUtil.getTitle() + ".set" + BaseUtil.upCase(item.getNames()) + "(" + BaseUtil.getTitle() + "Form.get" + BaseUtil.upCase(item.getNames()) + "());" + "\n");
					}
					if(item.getView().equals("多选框")) {
						boos.add(item);
					}
				}
				continue;
			}
			if(s.trim().startsWith("bxclIds += cmsBsdtWSService")) {
				sb.append(s.replace("FieldName", BaseUtil.upCase(fileItems.remove(1).getNames())) + "\n");
				continue;
			}
			if(s.trim().startsWith("bxclNames += cmsBsdtWSService")) {
				sb.append(s.replace("FieldName", BaseUtil.upCase(fileItems.remove(0).getNames())) + "\n");
				continue;
			}
			if(s.trim().startsWith("obj.setEwclId(cms")) {
				String str = "obj.getFieldName(), ";
				StringBuffer sb1 = new StringBuffer();
				for(int i=1; i<fileItems.size(); i=i+2) {
					Item item = fileItems.get(i);
					sb1.append(str.replace("FieldName", BaseUtil.upCase(item.getNames())));
				}
				sb.append(s.replace("EwclPathTag", sb1.toString().substring(0, sb1.toString().length()-2)) + "\n");
				continue;
			}
			if(s.trim().startsWith("obj.setEwclName(cms")) {
				String str = "obj.getFieldName(), ";
				StringBuffer sb1 = new StringBuffer();
				for(int i=0; i<fileItems.size(); i=i+2) {
					Item item = fileItems.get(i);
					sb1.append(str.replace("FieldName", BaseUtil.upCase(item.getNames())));
				}
				sb.append(s.replace("EwclNameTag", sb1.toString().substring(0, sb1.toString().length()-2)));
				continue;
			}
			sb.append(s.replace("headTag", BaseUtil.getHead()).replace("titleTag1", BaseUtil.getTitle()).replace("titleTag2", BaseUtil.upCase(BaseUtil.getTitle())) + "\n");
		}
		String action = sb.toString();
		if(boos.size()>0) {
			action = action.replace("//checkbox赋值", addCheckBox(boos));
		}
		return action;
	}
	
	/**
	 * 添加checkBox赋值方法
	 * @param boos
	 * @return
	 */
	public static String addCheckBox(List<Item> boos) {
		String space1 = BaseUtil.createSpaces(8);
		String space2 = BaseUtil.createSpaces(12);
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<boos.size(); i++) {
			Item item = boos.get(i);
			sb.append(space1 + "String[] " + item.getNames() + "s = request.getParameterValues(\"" + item.getNames() + "\");" + "\n"
					+ space1 + "String " + item.getNames() + " = \"\";" + "\n"
			+ space1 + "for(int i=0; i<" + item.getNames() + "s.length; i++) {" + "\n"
			+ space2 + "" + item.getNames() + " += " + item.getNames() + "s[i] + \",\";" + "\n"
			+ space1 + "}" + "\n"
			+ space1 + "" + BaseUtil.getTitle() + ".set" + BaseUtil.upCase(item.getNames()) + "(" + item.getNames() + ".substring(0, " + item.getNames() + ".length()-1));" + "\n"
			+ space1 + "" + BaseUtil.getTitle() + "Form.set" + BaseUtil.upCase(item.getNames()) + "(" + item.getNames() + ".substring(0, " + item.getNames() + ".length()-1));" + "\n");
		}
		return sb.toString();
	}

}
