<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head><title><s:text name="applicationText.listPersons"/></title></head>
    <s:head />
<body>

<h1><s:text name="applicationText.listPersons"/></h1>

<table>
    <s:iterator value="list" status="rowstatus">
        <tr>
            <s:if test="#rowstatus.odd != true">
              <td style="background:lightgray">
            </s:if>
            <s:else>
              <td>
            </s:else>
                <a href="
                        <s:url action='employee' namespace="/">
                            <s:param name="model.id" value="id"/>
                        </s:url>
                    ">
                    <s:property value="nachname"/>
                </a>
            </td>
        </tr>
    </s:iterator>
</table>

<br/>
<a href="<s:url action='employee' includeParams="none" />">Add employee</a>
&nbsp;-&nbsp;
<a href="<s:url action='menu' namespace="/" />">Mainmenu</a>
</body>
</html>