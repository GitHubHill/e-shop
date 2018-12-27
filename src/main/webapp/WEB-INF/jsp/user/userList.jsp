<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
</head>
<body>
    <div>
        <table>
            <tbody>
                <tr>
                    <td>用户名</td>
                    <td>用户性别</td>
                    <td>用户年龄</td>
                    <td>用户邮箱</td>
                    <td>用户电话</td>
                </tr>
            </tbody>
            <c:forEach items="${users}" var="user" varStatus="vs">
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.userSex}</td>
                    <td>${user.userAge}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.userPhoneNumber}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>