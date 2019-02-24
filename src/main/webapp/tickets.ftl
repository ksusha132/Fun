<html>
<head>
    <title>Tickets</title>
</head>
<body>
<ul>
    <#list ticketObjects as ticketObject>
        <li>${ticketObject.eventName}</li>
        <li>${ticketObject.price}</li>
        <li>${ticketObject.seat}</li>
        <li>${ticketObject.dateTime}</li>
        <li>${ticketObject.paid?then('Y', 'N')}</li>
    </#list>
</ul>
</body>
</html>
