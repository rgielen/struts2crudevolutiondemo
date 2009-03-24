<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

	<head>
    <title>Personalverwaltung</title>
	</head>

	<body>
		<h1>Personalverwaltung</h1>
        <hr>
            <ul>
                <li><a href="<s:url action='editEmployee' namespace="/singleaction/employee" includeParams="none" />">Mitarbeiter hinzufügen</a></li>
                <li><a href="<s:url action='editEmployee' namespace="/entityaction" includeParams="none" />">Mitarbeiter hinzufügen (optimiert)</a></li>
            </ul>
        <hr>
    </body>

</html>
