<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 07.11.2023
  Time: 08:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Utwórz wyjazd</title>
    <style>
        .error {
            color: #E74C3C;
            font-weight: normal;
        }

        body {
            background-color: #CCCCCC;
            font-family: Arial, sans-serif;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #8B4513;
        }

        form {
            background-color: #B3B3B3;
            border: 1px solid #999999;
            border-radius: 5px;
            padding: 20px;
            margin: 0 auto;
            width: 80%;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #222;
        }

        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #CCCCCC;
            border-radius: 3px;
            background-color: #D9D9D9;
        }

        button {
            background-color: #004080;
            color: #FFFFFF;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #002B4D;
        }
    </style>
</head>
<body>
<h1>Utwórz nowy wyjazd</h1>

<!-- Obsługa przypadku wyboru dat z nieodpowiedniego zakresu -->
<c:if test="${not empty invalidDateMessage}">
    <p style="color: #E74C3C;">${invalidDateMessage}</p>
</c:if>
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
    <form:select path="area" id="areaSelect">
        <form:option value="" label="Wybierz miejsce"/>
        <c:forEach items="${areas}" var="area">
            <form:option value="${area.id}" label="${area.name}" title="${area.description}"/>
        </c:forEach>
    </form:select>
    <form:errors path="area" cssClass="error"/>
    <hr/>
    <form:button>Utwórz wyjazd</form:button>
</form:form>

<script>

    function setMinEndDate() {
        const startDate = document.getElementById('startDate').value;
        const endDate = document.getElementById('endDate');
        const endDateValue = endDate.value;

        // Ustawienie minimalnej daty dla daty zakończenia
        endDate.min = startDate;

        // Sprawdzenie, czy data zakończenia jest wcześniejsza niż data początkowa
        if (endDateValue < startDate) {
            endDate.value = ''; // Jeśli tak, wyczyść pole daty zakończenia
        }
    }

    // Funkcja dla wyświetlania tooltipa po najechaniu kursorem na opcję obszaru
    const areaSelect = document.getElementById('areaSelect');
    areaSelect.addEventListener('mouseover', function (event) {
        const target = event.target;
        if (target.tagName === 'OPTION') {
            const description = target.getAttribute('title');
            if (description) {
                alert(description);
            }
        }
    });
</script>
</body>
</html>
