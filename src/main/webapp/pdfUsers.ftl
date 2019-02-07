<html>
<head>
    <title>Users Pdf</title>
</head>
<body>
<p>We have these users in the list:
<table border=1>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Birthday</th>
    </tr>
  <#list usersPdf as user>
  <tr>
      <td>${user.name}</td>
      <td>${user.email}</td>
      <td>${user.birthday}</td>
  </tr>
  </#list>
</table>
</body>
</html>
