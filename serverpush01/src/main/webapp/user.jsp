<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="resources/style.css"/>
        <script src="resources/functions.js"></script>
        <title>User Push</title>
    </head>
    <body>
        <h1>User styled</h1>
        <!-- <img src="resources/javaee-logo.png"/> -->
        <img src="${pageContext.request.contextPath}/resources/javaee-logo.png" alt="Java EE Logo"/>
        <br />
        <button onclick="message()">Message</button>
        <br />
        <a href="javascript:window.history.back();">Back</a>
    </body>
</html>