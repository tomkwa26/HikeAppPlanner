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
            background-color: #CCCCCC;
            font-family: Arial, sans-serif;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #2E8B57;
        }

        table {
            background-color: #999999;
            border-collapse: collapse;
            border: 1px solid #666666;
            width: 80%;
            margin: 0 auto;
            color: #222;
        }

        table td, th {
            border: 1px solid #666666;
            padding: 5px;
            text-align: center;
        }

        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        a {
            background-color: #D2B48C;
            color: #222;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            text-decoration: none;
            margin: 2px;
            display: inline-block;
        }

        a.new-hike {
            background-color: #BC8F8F;
        }
    </style>
</head>
<body>
<h1>Podsumowanie wyprawy: ${hikeName}</h1>

<table>
    <thead>
    <tr>
        <th>Data</th>
        <th>Obszar</th>
        <th>Kategoria szlaku</th>
        <th>Szlak</th>
        <th>Kolor szlaku</th>
        <th>Dystans</th>
        <th>Pogoda</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dailySelections}" var="dailySelection" varStatus="loop">
        <tr>
            <td>${dailySelection.date}</td>
            <td>${dailySelection.hike.area.name}</td>
            <td>${dailySelection.category.name}</td>
            <td>${dailySelection.trail.name}</td>
            <td>${dailySelection.trail.color}</td>
            <td>${dailySelection.trail.length} km</td>
            <td>
                <c:choose>
                    <c:when test="${loop.index < weatherDtoList.size()}">
                        <span>Temperatura max.: ${weatherDtoList[loop.index].maxTemperature} °C</span><br>
                        <span>Temperatura min.: ${weatherDtoList[loop.index].minTemperature} °C</span><br>
                        <span>Prędkość wiatru: ${weatherDtoList[loop.index].windSpeed} km/h</span><br>
                        <span>Wschód Słońca: ${weatherDtoList[loop.index].sunrise}</span><br>
                        <span>Zachód Słońca: ${weatherDtoList[loop.index].sunset}</span><br>
                        <span>Warunki pogodowe: ${weatherDtoList[loop.index].conditionText}</span><br>
                        <img src="${weatherDtoList[loop.index].conditionIcon}" alt="Icon for weather condition">
                    </c:when>
                    <c:otherwise>
                        <span>Brak danych pogodowych</span>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="button-container">
    <a href="/hike/create" class="new-hike">Utwórz nowy wyjazd</a>
</div>
</body>
</html>
