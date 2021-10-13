<%@ page import="com.example.weblab2.resContent.ResultTable" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>lab 2</title>
    <link rel="icon" type="image/png" href="img/aim.png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/graph.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/resTable.css">

</head>

<body>
<jsp:include page="header.jsp"/>
<div class="main-content">
    <form class="parameter-form" action="controller-servlet" method="get" name="form">
        <h3>X value:</h3>
        <label>
            <input id='textX' class="text-input" maxlength="6" type="text" name="x" placeholder="(-3; 3)" size="13">
        </label>

        <h3>Y value:</h3>
        <table class="y-table">
            <tbody>
            <tr>
                <td><label>
                    <input id="radio1" class="radioY" type="radio" name="y" value="-3">-3</label></td>
                <td><label>
                    <input id="radio2" class="radioY" type="radio" name="y" value="-2">-2</label></td>
                <td><label>
                    <input id="radio3" class="radioY" type="radio" name="y" value="-1">-1</label></td>
            </tr>
            <tr>
                <td><label>
                    <input id="radio4" class="radioY" type="radio" name="y" value="0"> 0</label></td>
                <td><label>
                    <input id="radio5" class="radioY" type="radio" name="y" value="1"> 1</label></td>
                <td><label>
                    <input id="radio6" class="radioY" type="radio" name="y" value="2"> 2</label></td>
            </tr>
            <tr>
                <td><label>
                    <input id="radio7" class="radioY" type="radio" name="y" value="3"> 3</label></td>
                <td><label>
                    <input id="radio8" class="radioY" type="radio" name="y" value="4"> 4</label></td>
                <td><label>
                    <input id="radio9" class="radioY" type="radio" name="y" value="5"> 5</label></td>
            </tr>
            </tbody>
        </table>

        <h3>Radius:</h3>
        <label>
            <input id="textR" class="text-input" maxlength="6" type="text" name="r" placeholder="(2; 5)" size="13">
        </label>

        <div class="action-buttons">
            <button class="action-button" type="submit" id="submit_request" name="check" value="check">
                Check
            </button>
            <button class="action-button" type="submit" id="clear_table" name="clear" value="clear">
                Clear
            </button>

            <button class="action-button" type="submit" id="draw_request" name="draw" value="draw">
                Draw
            </button>
        </div>
    </form>

    <svg class="svg-coordinates" height="350" width="350" xmlns="http://www.w3.org/2000/svg" id="area-graph">
        <%
            String coordinateText = "R";
            String candidate = (String) session.getAttribute("curR");
            if (candidate != null && !Double.isNaN(Double.parseDouble(candidate))) {
                coordinateText = candidate;
            }
            ResultTable resultTable = (ResultTable) session.getAttribute("resultTable");
            if (resultTable == null) {
                resultTable = new ResultTable();
            }
        %>
        <rect class="coordinates-figure" x="105" y="175" width="70" height="140"></rect>
        <path class="coordinates-figure" d="M 175 105 h0 v70 h70 v0 A70 70 0 0 0 175 105z"></path>
        <polygon class="coordinates-figure" points="105,175 175,105 175,175 "></polygon>
        <line class="coordinate-axis" x1="0" x2="345" y1="175" y2="175"></line>
        <line class="coordinate-axis" x1="175" x2="175" y1="350" y2="5"></line>

        <polygon class="coordinate-axis" points="350,175 335,170 335,180"></polygon>
        <polygon class="coordinate-axis" points="175,0 180,15 170,15"></polygon>

        <text class="coordinates-text" x="182" y="11">y</text>
        <text class="coordinates-text" x="335" y="167">x</text>

        <line class="coordinates-marker" x1="171" x2="179" y1="35" y2="35"></line>
        <line class="coordinates-marker" x1="171" x2="179" y1="105" y2="105"></line>
        <line class="coordinates-marker" x1="171" x2="179" y1="245" y2="245"></line>
        <line class="coordinates-marker" x1="171" x2="179" y1="315" y2="315"></line>

        <line class="coordinates-marker" x1="35" x2="35" y1="171" y2="179"></line>
        <line class="coordinates-marker" x1="105" x2="105" y1="171" y2="179"></line>
        <line class="coordinates-marker" x1="245" x2="245" y1="171" y2="179"></line>
        <line class="coordinates-marker" x1="315" x2="315" y1="171" y2="179"></line>

        <text class="coordinates-text" id="Ry" x="180" y="40"><%=coordinateText%>
        </text>
        <text class="coordinates-text" id="-Ry" x="180" y="320">-<%=coordinateText%>
        </text>
        <text class="coordinates-text" id="-Rx" x="20" y="195">-<%=coordinateText%>
        </text>
        <text class="coordinates-text" id="Rx" x="310" y="195"><%=coordinateText%>
        </text>
        <%=
        resultTable.getSvg()
        %>
    </svg>
</div>

<div class="result">
    <table class="result-table">
        <thead style="font-weight: bold">
        <tr>
            <td>
                X val
            </td>
            <td>
                Y val
            </td>
            <td>
                R val
            </td>
            <td>
                Cur time
            </td>
            <td>
                Exc time
            </td>
            <td>
                Result
            </td>
        </tr>
        </thead>
        <tbody>
        <%=
        resultTable.toString()
        %>
        </tbody>
    </table>
</div>
</body>
<script src="js/validator.js"></script>
<script>
    function update_graph() {
        const dots = document.getElementsByClassName('coordinate-dot');

        const curR = +(localStorage.getItem('rVal') - 0.0);

        const rVal = +(document.forms["form"]["r"].value.replace(/,/, '.') - 0.0);
        if (!isNaN(rVal) && !isNaN(curR) && rVal > 2 && rVal < 5) {
            document.getElementById('Rx').textContent = rVal.toString();
            document.getElementById('-Rx').textContent = '-' + rVal.toString();
            document.getElementById('Ry').textContent = rVal.toString();
            document.getElementById('-Ry').textContent = '-' + rVal.toString();
            for (let i = 0; i < dots.length; ++i) {
                const xCor = +(dots.item(i).getAttribute('cx'));
                const yCor = +(dots.item(i).getAttribute('cy'));
                let xVal = (((xCor - 175) * curR) / rVal) + 175;
                let yVal = (((yCor - 175) * curR) / rVal) + 175;
                dots.item(i).setAttribute('cx', xVal.toString());
                dots.item(i).setAttribute('cy', yVal.toString());
            }
            if (dots.length > 0)
                console.log('changed');
            localStorage.setItem('rVal', rVal.toString());
        } else {
            console.log('bad values: ', curR, rVal);
        }
    }

    function getDot() {
        const svgArea = document.getElementById("area-graph");
        let rect = svgArea.getBoundingClientRect();
        let yCor = (event.clientY - rect.top);
        let xCor = (event.clientX - rect.left);
        const rVal = document.forms["form"]["r"].value.replace(/,/, '.');
        const xVal = Math.floor(((xCor - 193) / 140 * rVal) * 100) / 100;
        const yVal = Math.floor(((193 - yCor) / 140 * rVal) * 100) / 100;
        if (isEmpty(rVal)) {
            document.getElementsByClassName('text-input')[1].style.background = 'red';
            alert('Enter a number in R field');
            document.getElementsByClassName('text-input')[1].style.background = 'inherit';
        } else if (isNaN(rVal) || rVal <= 2 || rVal >= 5) {
            document.getElementsByClassName('text-input')[1].style.background = 'red';
            alert('R must be number in range (2; 5)');
            document.getElementsByClassName('text-input')[1].style.background = 'inherit';
        } else if ((Math.abs(xVal) < 3) && yVal > -3 && yVal < 5) {
            sendRequest(xVal, yVal, rVal);
        } else {
            if ((Math.abs(xVal) >= 3)) {
                alert('X must be number in range (-3; 3)');
            } else if (yVal < -3 || yVal > 5) {
                alert('Y must be integer number in range [-3; 5]');
            }
        }
    }


    function sendRequest(x, y, r) {
        console.log(x, y, r);
        let http = new XMLHttpRequest();
        const main = 'http://0.0.0.0:3675/web-lab-2-1.0-SNAPSHOT';
        // const main = 'http://localhost:8080/web-lab-2-1.0-SNAPSHOT';
        const page = '/controller-servlet';
        const params = '?x=' + x.toString() + '&y=' + y + '&r=' + r.toString() + '&' + 'check=dotCheck';
        let url = main + page + params;
        localStorage.setItem('rVal', r);
        http.onload = function () {
            document.location.href = 'index.jsp';
        };
        http.open('GET', url, true);
        http.send();
    }

    function isEmpty(obj) {
        for (let key in obj) {
            return false;
        }
        return true;
    }

    document.getElementById('area-graph').onclick = getDot;
    document.getElementById('textR').onchange = update_graph;
</script>
</html>