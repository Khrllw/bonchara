//  General scroll handler //
window.addEventListener('scroll', function () {
    document.getElementById('showScroll').textContent = `From the top: ${Math.round(window.scrollY)} px`;
    moveText(Math.min(screenHeight * 2, window.scrollY));
    highlightItems(window.scrollY / screenHeight);
});

// Mouse trigger -/- Elements movement(tremble) handler //
const itemsRGB = document.querySelectorAll('.rgb-code');
const itemsMain = document.querySelectorAll('.main-text');
window.addEventListener('mousemove', function (event) {
    const x = event.clientX;
    const y = event.clientY;

    document.getElementById('mouseMove').textContent = `Your mouse: ${x}:${y} px`;

    document.getElementById('mousePointer').style.left = x - cursorCenter * screenHeight + 'px';
    document.getElementById('mousePointer').style.top = y - cursorCenter * screenHeight + 'px';

    for (let i = 0; i < itemsRGB.length; i++) {
        itemsRGB[i].style.transform = 'translate(-' + x * itemsRGBSens / screenWidth + 'px, -' +
            y * itemsRGBSens / screenHeight + 'px)';
        itemsMain[i].style.transform = 'translate(-' + x * itemsMainSense / screenWidth + 'px, -' +
            y * itemsMainSense / screenHeight + 'px)';
    }
});

// Text position changer //
function moveText(y_pos) {
    document.getElementById('redText').style.left = y_pos + "px";
    document.getElementById('greenText').style.left = (y_pos - screenHeight) * screenRatio + "px";
    document.getElementById('blueText').style.left = (y_pos - screenHeight * 2) * screenRatio + "px";
    document.getElementById('redSubText').style.left = -(y_pos) + "px";
    document.getElementById('greenSubText').style.left = -(y_pos - screenHeight) * screenRatio + "px";
    document.getElementById('blueSubText').style.left = -(y_pos - screenHeight * 2) * screenRatio + "px";
}

// Elements color changer handler //
function highlightItems(y_pos) {
    let value = 0;
    switch (true) {
        case (y_pos > mainAnimationPoints[1]):
            value = 2;
            break;
        case (y_pos > mainAnimationPoints[0]):
            value = 1;
            break;
    }

    if (value !== menuActivePoint) {
        document.getElementById(`menuItem_${value}`).classList.add("active");
        document.getElementById(`menuItem_${value}`).style.color = mainColors[value];
        document.getElementById(`menuItem_${menuActivePoint}`).classList.remove("active");
        document.getElementById(`menuItem_${menuActivePoint}`).style.color = defaultColor;

        document.getElementById('mousePointer').style.backgroundColor = mainColors[value];
        document.getElementById('foneItem').style.backgroundColor = mainColors[value];
        document.getElementById('progressLine').style.backgroundColor = mainColors[value];
        menuActivePoint = value;
    }
}