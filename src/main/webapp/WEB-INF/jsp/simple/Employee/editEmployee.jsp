<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head><title>Person bearbeiten</title></head>
    <s:head />
<body>
<h1>Person bearbeiten</h1>
<s:form action="saveEmployee">

    <s:hidden name="employee.id" />

    <s:select name="employee.anrede" list="{'Herr','Frau'}" headerKey="''" headerValue="--Bitte auswählen --"/>

    <s:textfield label="Nachname" name="employee.nachname" value="%{employee.nachname}"/>

    <s:textfield label="Vorname" name="employee.vorname" />
    <s:textfield label="Jahreslohn" name="employee.jahreslohn" />

<%--
    <s:select name="employee.skills.id" list="availableSkills"
            listKey="key" listValue="anzeigeName" multiple="true"/>
--%>

    <s:submit value="Speichern"/>

</s:form>
<a href="<s:url action='Start' namespace="/" />">Zum Hauptmenü</a>
</body>
</html>