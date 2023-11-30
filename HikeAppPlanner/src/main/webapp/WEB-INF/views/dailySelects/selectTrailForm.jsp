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

                <!-- Brak szlaku dla wybranej kategorii -->
                <c:choose>
                    <c:when test="${empty trails[dailySelection.id]}">
                        <option value="Brak dostępnych szlaków"/>
                    </c:when>
                    <c:otherwise>

                        <!-- Pobranie dostępnych szlaków dla danego dnia wyprawy -->
                        <c:forEach items="${trails[dailySelection.id]}" var="trail">
                            <!-- Jeśli szlak nie został jeszcze wybrany, pokaż go w polu wyboru -->
                            <c:if test="${dailySelection.trail == null}">
                                <option value="${trail.id}" title="${trail.description}">${trail.name}</option>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>

            <!-- Obsługa przypadku braku szlaków dla wybranej kategorii -->
            <c:if test="${errorMessages[dailySelection.id] != null}">
                <p style="color: red;">${errorMessages[dailySelection.id]}</p>
                <a href="/select/category?hikeId=${hikeId}">Wróć do wyboru kategorii szlaków</a>
            </c:if>
        </fieldset>
        <br/>
        <button type="submit">Zapisz</button>
    </form>
</c:forEach>
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
