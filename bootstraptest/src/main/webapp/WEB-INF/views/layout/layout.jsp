<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
</head>
<body>
	<div>
		<tiles:insertAttribute name="header"/>
	</div>
	<div>
		<tiles:insertAttribute name="menu"/>
	</div>
	<div>
		<tiles:insertAttribute name="body"/>
	</div>
	<div>
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>