const sendButton = document.getElementById("submit_request")
const drawButton = document.getElementById("draw_request")

function validate() {
    const xVal = document.forms['form']['x'].value.replace(/,/, '.');
    const yVal = document.forms['form']['y'].value.replace(/,/, '.');
    const rVal = document.forms['form']['r'].value.replace(/,/, '.');
    console.log('form values: ' + xVal + ' ' + yVal + ' ' + rVal);
    if (!isEmpty(rVal) && !isNaN(rVal) && rVal > 2 && rVal < 5) {
        console.log(rVal);
        localStorage.setItem('rVal', String(rVal));
    }
    if (isEmpty(xVal)) {
        document.getElementsByClassName('text-input')[0].style.background = 'red';
        alert('Enter a number in X field');
    } else if (isEmpty(yVal)) {
        yTable = document.getElementsByClassName('y-table');
        for (let i = 0; i < yTable.length; ++i) {
            yTable.item(i).style.background = 'red';
        }
        alert('Select Y');
    } else if (isEmpty(rVal)) {
        document.getElementsByClassName('text-input')[1].style.background = 'red';
        alert('Enter a number in R field');
    } else {
        if (isNaN(xVal) || (Math.abs(xVal) >= 3)) {
            document.getElementsByClassName('text-input')[0].style.background = 'red';
            alert('X must be number in range (-3; 3)');
        } else if (isNaN(yVal) || yVal < -3 || yVal > 5) {
            for (let i = 0; i < yTable.length; ++i) {
                yTable.item(i).style.background = 'red';
            }
            alert('Y must be integer number in range [-3; 5]');

        } else if (isNaN(rVal) || rVal <= 2 || rVal >= 5) {
            document.getElementsByClassName('text-input')[1].style.background = 'red';
            alert('R must be number in range (2; 5)');
        }
    }
}

function validateDraw() {
    const rVal = document.forms['form']['r'].value.replace(/,/, '.');
    if (isEmpty(rVal)) {
        document.getElementsByClassName('text-input')[1].style.background = 'red';
        alert('Enter a number in R field');
    } else if (isNaN(rVal) || rVal <= 2 || rVal >= 5) {
        document.getElementsByClassName('text-input')[1].style.background = 'red';
        alert('R must be number in range (2; 5)');
    }
}

function isEmpty(obj) {
    for (let key in obj) {
        return false;
    }
    return true;
}

drawButton.onclick = validateDraw;
sendButton.onclick = validate;