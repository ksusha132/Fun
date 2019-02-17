
<html>
<head>
    <title>Book ticket</title>
</head>
<body>
<form action="/ticket/book" method="post">
    Event name: <input type="text" name="name"><br>
    Date and time: <input type="text" name="dateTime"><br>
    Seat number: <input type="text" name="seat"><br>
    Price: <input type="text" name="price"><br>
    Paid: <input type="checkbox" name="paid"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
