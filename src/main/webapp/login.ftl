<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register user</title>
</head>
<body>
<div>
    <form name="form" action="/user/login" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <label for="email">Username</label>
            <input type="text" id="email" name="email"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
