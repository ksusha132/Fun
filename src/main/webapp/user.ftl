<html>
<head><title>Acmee Products International</title>
<body>
<p>These are our latest offers:
<ul>
    <#list userObjects as userObject>
        <li>${userObject.name}</li>
    </#list>
</ul>
</body>
</html>
