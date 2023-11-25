<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 07.11.2023
  Time: 08:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Utwórz wyjazd</title>
    <style>
        .error {
            color: crimson;
            font-weight: normal;
        }
        body {
            background-color: #E6E6E6;
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            background-color: #F2F2F2;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin: 0 auto;
            width: 80%;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #D9D9D9;
        }

        button {
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Utwórz nowy wyjazd</h1>
<form:form method="post" modelAttribute="hike" action="/hike/create">
    <form:hidden path="id"/>
    <form:label path="name">Nazwa</form:label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="error"/>
    <hr/>
    <label for="startDate">Data rozpoczęcia wyjazdu</label>
    <form:input path="startDate" type="date" id="startDate" min="<%= LocalDate.now() %>" onchange="setMinEndDate()"/>
    <form:errors path="startDate" cssClass="error"/>
    <hr/>
    <label for="endDate">Data zakończenia wyjazdu</label>
    <form:input path="endDate" type="date" id="endDate" onchange="setMinEndDate()"/>
    <form:errors path="endDate" cssClass="error"/>
    <hr/>
    <form:label path="area">Miejsce</form:label>
    <form:select path="area">
        <form:option value="" label="Wybierz miejsce"/>
        <form:options items="${areas}" itemLabel="name" itemValue="id"/>
    </form:select>
    <form:errors path="area" cssClass="error"/>
    <hr/>
    <form:button>Utwórz wyjazd</form:button>
</form:form>
<script>
    function setMinEndDate() {
        const startDate = document.getElementById('startDate').value;
        document.getElementById('endDate').min = startDate;
    }
</script>
</body>
</html>
