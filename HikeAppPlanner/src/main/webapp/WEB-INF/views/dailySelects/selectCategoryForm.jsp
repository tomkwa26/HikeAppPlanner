<%--
  Created by IntelliJ IDEA.
  User: tomasz
  Date: 09.11.2023
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wybierz kategorię szlaku</title>
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

        a.select-trail {
            background-color: #BC8F8F;
        }
    </style>
</head>
<body>
<h1>Wybierz kategorie szlaków</h1>

<c:forEach items="${dailySelections}" var="dailySelection" varStatus="loop">
    <form action="/select/category" method="post">
        <fieldset>
            <legend>Dzień ${dailySelection.date}</legend>
            <input type="hidden" name="dailySelectionId" value="${dailySelection.id}"/>
            <input type="hidden" name="hikeId" value="${hikeId}"/>
            <!-- Pole do wyświetlenia wybranej kategorii -->
            <select id="categoryId_${dailySelection.id}" name="categoryId">
                <option value="">Wybierz kategorię szlaku:</option>
                <!-- Pobranie dostępnych kategorii dla danego dnia wyprawy -->
                <c:forEach items="${categories[loop.index]}" var="category">
                    <!-- Jeśli kategoria została wybrana, pokaż jej nazwę -->
                    <c:choose>
                        <c:when test="${dailySelection.category != null && dailySelection.category.id eq category.id}">
                            <option value="${category.id}" selected>${category.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.id}" title="${category.description}">${category.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </fieldset>
        <br/>
        <button type="submit">Zapisz</button>
    </form>
</c:forEach>

<div class="button-container">
    <a href="/select/trail?hikeId=${hikeId}" class="select-trail">Dalej</a>
</div>

<script>

    // Funkcja dla wyświetlania tooltipa po najechaniu kursorem na opcję kategorii
    const categorySelects = document.querySelectorAll('.category-select');
    categorySelects.forEach(select => {
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
