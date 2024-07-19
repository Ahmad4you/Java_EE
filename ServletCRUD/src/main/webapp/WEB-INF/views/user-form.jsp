<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
    <title>User Management Application</title>
</head>
<body>
    <h1>User Management</h1>
    <h2>
        <c:if test="${user != null}">
            Edit User
        </c:if>
        <c:if test="${user == null}">
            Add New User
        </c:if>
    </h2>
    <form action="users" method="post">
        <input type="hidden" name="action" value="${user != null ? 'update' : 'create'}">
        <c:if test="${user != null}">
            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
        </c:if>
        <table border="1">
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                        value="<c:out value='${user.firstName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>User Vorname:</th>
                <td>
                    <input type="text" name="vorname" size="45"
                        value="<c:out value='${user.lastName}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>