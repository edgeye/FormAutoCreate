<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
    <title>My JSP 'first.jsp' starting page</title>
    
    <script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  
  <body>
    <form action="original.do" method="post">
    	<b>事项标题：</b>
    	<input type="text" name="headTitle" id="headTile" />
    	&nbsp;&nbsp;
    	<b>事项名称：</b>
    	<input type="text" name="headLine" id="headLine" />
    	<br/>
    	<br/>
    		<textarea id="base" name="base" rows="20" cols="100"></textarea>
    	<br/>
    	<br/>
    	<input type="submit" name="next" id="next" value="提交下一步" />
    </form>
  </body>
</html>
