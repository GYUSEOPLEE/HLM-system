<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<h1>Hello World</h1>
<form action="/login" method="post">
    <table>
        <td>
            <tr>
                ID : <input type="text" name="id">
            </tr>
            <tr>
                PW : <input type="password" name="password">
            </tr>
        </td>
    </table>
    <input type="submit" value="로그인">
</form>
</body>
</html>