<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 10.11.2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wybierz szlak</title>
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

        .button-container {
            display: flex;
            justify-content: right;
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

        a.select-summary{
            background-color: #BC8F8F;
        }
    </style>
</head>
<body>
<h1>Wybierz szlak</h1>

<c:forEach items="${dailySelections}" var="dailySelection">
    <form action="/select/trail" method="post">
        <fieldset>
            <legend>Dzień ${dailySelection.date}</legend>
            <input type="hidden" name="dailySelectionId" value="${dailySelection.id}"/>
            <input type="hidden" name="hikeId" value="${hikeId}"/>
            <input type="hidden" name="categoryId" value="${dailySelection.category.id}"/>
            <label for="trailId_${dailySelection.id}">Wybierz szlak:</label>
            <select id="trailId_${dailySelection.id}" name="trailId">
                <!-- Pole do wyświetlenia wybranego szlaku -->
                <option value=""
                        selected>${dailySelection.trail != null ? dailySelection.trail.name : "Wybierz szlak:"}</option>
                <c:if test="${dailySelection.trail != null}">
                    <option value="${dailySelection.trail.id}" selected>${dailySelection.trail.name}</option>
                </c:if>
                <!-- Pobranie dostępnych szlaków dla danego dnia wyprawy -->
                <c:forEach items="${trails[dailySelection.id]}" var="trail">
                    <!-- Jeśli szlak nie został jeszcze wybrany, pokaż go w polu wyboru -->
                    <c:if test="${dailySelection.trail == null}">
                        <option value="${trail.id}" title="${trail.description}">${trail.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <!-- Obsługa przypadku braku szlaków dla wybranej kategorii -->
            <c:if test="${errorMessages[dailySelection.id] != null}">
                <p style="color: #E74C3C;">${errorMessages[dailySelection.id]}</p>
                <a href="/select/category?hikeId=${hikeId}">Wróć do wyboru kategorii szlaków</a>
            </c:if>
        </fieldset>
        <br/>
        <button type="submit">Zapisz</button>
    </form>
</c:forEach>

<div class="button-container">
    <%--dla płatnej usługi FutureApi--%>
<%--<a href="/select/summary?hikeId=${hikeId}" class="select-summary">Podsumowanie</a>--%>
    <%--dla darmowej usługi ForecastApi--%>
<a href="/select/summaryForecast?hikeId=${hikeId}" class="select-summary">Podsumowanie</a>
</div>

<script>

// Funkcja dla wyświetlania tooltipa po najechaniu kursorem na opcję szlaku
const trailSelects = document.querySelectorAll('.trail-select');
trailSelects.forEach(select => {
    select.addEventListener('mouseover', function (event) {
        const target = event.target;
        if (target.tagName === 'OPTION') {
            const description = target.getAttribute('title');
            if (description) {
                alert(description);
            }
        }
    });
});
</script>
</body>
</html>
