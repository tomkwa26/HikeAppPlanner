<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 13.11.2023
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Podsumowanie wyprawy</title>
    <style>
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
<h1>Podsumowanie wyprawy</h1>

<p>Nazwa wyprawy: ${hike.name}</p>
<p>Data początkowa: ${hike.startDate}</p>
<p>Data końcowa: ${hike.endDate}</p>

<h2>Wybrany obszar, kategorie i szlaki:</h2>
<c:forEach items="${dailySelections}" var="selection">
    <p>Obszar: ${dailySelections.hike.area}</p>
    <p>Data: ${dailySelections.date}</p>
    <p>Kategoria szlaku: ${dailySelections.category.name}</p>
    <p>Szlak: ${dailySelections.trail.name}</p>
    <hr/>
</c:forEach>
</body>
</html>
