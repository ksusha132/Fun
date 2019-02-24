
<html>
<head>
    <title>Events</title>
</head>
<body>
<ul>
    <#list eventObjects as event>
        <li>${event.name}</li>
        <li>${event.auditoriumName}</li>
        <li>${event.basePrice}</li>
        <li>${event.rating}</li>
        <li>${event.datesEvent}</li>
    </#list>
</ul>
</body>
</html>
