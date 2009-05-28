<html>
    <head><title><@s.text name="applicationText.editSkill"/></title></head>
    <@s.head />
<body>

<h1><@s.text name="applicationText.editSkill"/></h1>

<@s.form action="saveSkill">
    <@s.textfield key="key"/>
    <@s.textfield key="anzeigeName" />
    <@s.submit value="Speichern"/>
</@s.form>

<a href="<@s.url action='skills' namespace="/" />">List</a>
&nbsp;-&nbsp;
<a href="<@s.url action='menu' namespace="/" />">Mainmenu</a>
</body>
</html>