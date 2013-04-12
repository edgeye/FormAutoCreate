<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    
    <title>My JSP 'first.jsp' starting page</title>
    
  </head>
  
  <body>
    <form action="dismantle.do" method="post">
    	<b>事项标题：</b>
    	<input type="text" name="headTitle" id="head" />
    	&nbsp;&nbsp;
    	<b>事项名称：</b>
    	<input type="text" name="headLine" id="headLine" />
    	<br/>
    	<br/>
    	<table>
    		<tr>
    			<td><b>基本信息：</b></td>
    			<td><b>类型：</b></td>
    			<td><b>必填项：</b></td>
    			<td><b>取值范围：</b></td>
    			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td><b>业务信息：</b></td>
    			<td><b>类型：</b></td>
    			<td><b>必填项：</b></td>
    			<td><b>取值范围：</b></td>
    		</tr>
    		<tr>
    			<td><textarea name="name" rows="10" cols="25"></textarea></td>
    			<td><textarea name="type" rows="10" cols="10"></textarea></td>
    			<td><textarea name="need" rows="10" cols="5"></textarea></td>
    			<td><textarea name="area" rows="10" cols="20"></textarea></td>
    			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td><textarea name="name" rows="10" cols="25"></textarea></td>
    			<td><textarea name="type" rows="10" cols="10"></textarea></td>
    			<td><textarea name="need" rows="10" cols="5"></textarea></td>
    			<td><textarea name="area" rows="10" cols="20"></textarea></td>
    		</tr>
    		<tr>
    			<td><b>必须材料：</b></td>
    			<td><b>类型：</b></td>
    			<td><b>必填项：</b></td>
    			<td><b>取值范围：</b></td>
    			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td><b>额外材料：</b></td>
    			<td><b>类型：</b></td>
    			<td><b>必填项：</b></td>
    			<td><b>取值范围：</b></td>
    		</tr>
    		<tr>
    			<td><textarea name="name" rows="10" cols="25"></textarea></td>
    			<td><textarea name="type" rows="10" cols="10"></textarea></td>
    			<td><textarea name="need" rows="10" cols="5"></textarea></td>
    			<td><textarea name="area" rows="10" cols="20"></textarea></td>
    			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td><textarea name="name" rows="10" cols="25"></textarea></td>
    			<td><textarea name="type" rows="10" cols="10"></textarea></td>
    			<td><textarea name="need" rows="10" cols="5"></textarea></td>
    			<td><textarea name="area" rows="10" cols="20"></textarea></td>
    		</tr>
    	</table>
    	<br/>
    	<input type="submit" name="next" id="next" value="提交下一步"/>
    </form>
  </body>
</html>
