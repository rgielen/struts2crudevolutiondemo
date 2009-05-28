<html>
    <head><title><@s.text name="applicationText.listSkills"/></title></head>
    <@s.head />
<body>

<h1><@s.text name="applicationText.listSkills"/></h1>

<table>
    <@s.iterator value="list" status="rowstatus">
        <tr>
            <@s.if test="#rowstatus.odd != true">
              <td style="background:lightgray">
            </@s.if>
            <@s.else>
              <td>
            </@s.else>
                <a href="
                        <@s.url action='skill' namespace="/">
                            <@s.param name="model.key" value="key"/>
                        </@s.url>
                    ">
                    <@s.property value="anzeigeName"/>
                </a>
            </td>
        </tr>
    </@s.iterator>
</table>

<br/>
<a href="<@s.url action='skill' includeParams="none" />">Add skill</a>
&nbsp;-&nbsp;
<a href="<@s.url action='menu' namespace="/" />">Mainmenu</a>
</body>
</html>