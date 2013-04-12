<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>My JSP 'third.jsp' starting page</title>

  </head>
  
  <body>
  	<form method="post" action="assemble.do">
 		<b>事项标题：</b>
    	${headTitle }
    	&nbsp;&nbsp;
    	<b>事项名称：</b>
    	${headLine }
    	<br/>
  		<b>表单已完成！！！！</b><br/>
    	<br/>
  </body>
</html>
