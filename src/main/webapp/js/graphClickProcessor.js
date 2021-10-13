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
