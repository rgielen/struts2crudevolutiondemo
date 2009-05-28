<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
    <head><title><s:text name="applicationText.editPerson"/></title></head>
    <s:head/>
    <sx:head/>
<body>

<h1><s:text name="applicationText.editPerson"/></h1>

<s:form action="saveEmployee">
    <s:hidden name="id" />
    <s:select key="anrede" list="{'Herr','Frau'}" headerKey="''" headerValue="%{getText('applicationText.choseValue')}"/>

    <s:textfield key="nachname"/>
    <s:textfield key="vorname" />
    <s:textfield key="email" />
    <sx:datetimepicker key="geburtsDatum"/>
    <s:textfield key="jahreslohn" />

    <s:select key="abteilung" size="2"
              list="#{'LABS':'Forschung','PROD':'Produktion'}" />

    <s:select label="%{getText('hauptSkill')}" name="hauptSkill.key" list="skills" listKey="key" listValue="anzeigeName"/>
    
    <s:submit value="Speichern"/>
</s:form>

<a href="<s:url action='employees' namespace="/" />">List</a>
&nbsp;-&nbsp;
<a href="<s:url action='menu' namespace="/" />">Mainmenu</a>
</body>
</html>