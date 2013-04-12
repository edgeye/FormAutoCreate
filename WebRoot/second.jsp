<%@ page language="java" import="java.util.*, com.cdc.model.Item" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>My JSP 'second.jsp' starting page</title>
  </head>
  
  <body>
  	<form method="post" action="assemble.do">
 		<b>事项标题：</b>
    	<input type="text" name="headTitle" id="headTitle" value="${headTitle }"/>
    	&nbsp;&nbsp;
    	<b>事项名称：</b>
    	<input type="text" name="headLine" id="headLine" value="${headLine }"/>
    	<br/>
    	<br/>
  		<b>基本字段说明：</b><br/>
    	<table border="1">
    		<tr>
    			<td><b>名称</b></td>
    			<td><b>字段</b></td>
    			<td><b>类型</b></td>
    			<td><b>长度</b></td>
    			<td><b>必填项</b></td>
    			<td><b>属性</b></td>
    			<td><b>示图</b></td>
    		</tr>
    		<c:forEach var="item" items="${items}">
    		<tr>
    			<td><input type="text" name="title" id="title" value="${item.title }" style="width:200px"/></td>
    			<td><input type="text" name="names" id="names" value="${item.names }" /></td>
    			<td><input type="text" name="types" id="types" value="${item.types }" style="width:70px"/></td>
    			<td><input type="text" name="lengths" id="lengths" value="${item.lengths }" style="width:70px"/></td>
    			<td><input type="text" name="req" id="req" value="${item.req }" style="width:70px"/></td>
    			<td><input type="text" name="prop" id="prop" value="${item.prop }" /></td>
    			<!--<td><input type="text" name="view" id="view" value="${item.view }" /></td>
    			--><td>
    				<select name="view" id="view">
    					<option value="文本框">普通文本框</option>
    					<option value="文本框2">单行文本框</option>
    					<option value="文本框s">半行文本框</option>
    					<option value="文本框t">时期区间</option>
    					<option value="文本框3">文本域</option>
    					<option value="单选按钮">单选按钮</option>
    					<option value="下拉列表">下拉列表</option>
    					<option value="多选框">多选框</option>
    				</select>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    	<br/>
    	<input type="submit" id="next" value="提交下一步" />
    </form>
  </body>
</html>
