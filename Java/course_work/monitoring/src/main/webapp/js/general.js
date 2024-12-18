function showPopup(id) {
    document.getElementById("pop-up-wrap").classList.remove("hidden");
}

function closePopup() {
    document.getElementById("pop-up-wrap").classList.add("hidden");
}

let currentData;

let temp = [0];
let speed = [0];

function updateData() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "updateServlet", true);

    xhr.responseType = 'json';

    // Обработка ответа сервера
    xhr.onload = function () {
        // Запрос завершен
        if (xhr.status === 200) {
            currentData = xhr.response;
            console.log("Данные с сервера:", currentData);
            if (currentData != null) {
                document.getElementById("destemmer-crusher-parametres").innerHTML = `Temperature: ${currentData["temperature1"]}°C<br>
                Speed: ${currentData["speed1"]} rpm`;
                document.getElementById("wine-press-parametres").innerHTML = `Pressure: ${currentData["pressure2"]} bar<br>
                Temperature: ${currentData["temperature2"]}°C`;
                document.getElementById("fermentation-tanks-parametres").innerHTML = `Volume: ${currentData["volume3"]} liters<br>
                Pressure: ${currentData["pressure3"]} atmosphere<br>
                Temperature: ${currentData["temperature3"]}°C`;
                document.getElementById("chilling-unit-parametres").innerHTML = `Temperature: ${currentData["temperature4"]}°C<br>
                Time: ${currentData["time4"]} hours`;
                document.getElementById("filtering-equipment-parametres").innerHTML = `Pressure: ${currentData["pressure5"]} bar<br>
                Temperature: ${currentData["temperature5"]}°C`;
                document.getElementById("wine-pump-parametres").innerHTML = `Speed: ${currentData["speed6"]} lpm<br>
                Pressure: ${currentData["pressure6"]} bar<br>
                Temperature: ${currentData["temperature6"]}°C`;
                document.getElementById("thermal-shock-tank-parametres").innerHTML = `Temperature: ${currentData["temperature7"]}°C<br>
                Time: ${currentData["time7"]} hours`;
                document.getElementById("fining-equipment-parametres").innerHTML = `Temperature: ${currentData["temperature8"]}°C<br>
                Time: ${currentData["time8"]} hours`;
                document.getElementById("bottling-line-parametres").innerHTML = `Speed: ${currentData["speed9"]} bpm<br>
                Pressure: ${currentData["pressure9"]} bsr<br>
                Temperature: ${currentData["temperature9"]}°C`;
                document.getElementById("treatment-unit-parametres").innerHTML = `Pressure: ${currentData["pressure10"]} bsr<br>
                Time: ${currentData["time10"]} minutes`;
                document.getElementById("electromagnetic-treatment-unit-parametres").innerHTML = `Frequency: ${currentData["frequency11"]} Hz<br>
                Voltage: ${currentData["voltage11"]} kV<br>
                Time: ${currentData["time11"]} minutes`;
                console.log(temp);
                if (temp.length === 15) {
                    temp.shift();
                }
                console.log(speed);
                temp = temp.concat(currentData["temperature1"]);
                if (speed.length === 15) {
                    speed.shift();
                }
                speed = speed.concat(currentData["speed1"]);
                drawLineChart(document.getElementById("temperature-canv"), temp);
                drawLineChart(document.getElementById("speed-canv"), speed);
            }
        } else {
            alert("Error: " + xhr.status);
        }

    };
    xhr.send();
}

setInterval(updateData, 300);

// Линейный график
function drawLineChart(ctx_my, data) {
    const ctx = ctx_my.getContext('2d');

    // Очищаем холст
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);

    // Находим минимальное и максимальное значения в данных
    const minValue = Math.min(...data) - 20;
    const maxValue = Math.max(...data) + 20;

    // Масштабируем данные в соответствии с размерами холста
    const scaleY = ctx.canvas.height / (maxValue - minValue);
    const scaleX = ctx.canvas.width / (data.length - 1);

    // Рисуем сетку
    drawGrid(ctx, ctx.canvas.width, ctx.canvas.height, 15, 15);


    // Начинаем рисовать линию из первого значения массива
    ctx.beginPath();
    const startX = 0; // Начальная точка по оси X
    const startY = ctx.canvas.height - (data[0] - minValue) * scaleY; // Начальная точка по оси Y
    ctx.moveTo(startX, startY);


    // Рисуем линию и подписи
    data.forEach((value, index) => {
        const x = index * scaleX;
        const y = ctx.canvas.height - (value - minValue) * scaleY;

        // Проверяем, чтобы координаты не выходили за границы холста
        const clampedX = Math.max(0, Math.min(x, ctx.canvas.width));
        const clampedY = Math.max(0, Math.min(y, ctx.canvas.height));

        ctx.lineTo(clampedX, clampedY);

        // Подпись значения
        ctx.fillStyle = '#000';
        ctx.font = '10px TeletactileLatCyr';
        const offsetX = index === data.length - 1 ? -15 : 5;
        ctx.fillText(value, clampedX + offsetX, clampedY - 10);
    });

    // Стили для линии
    ctx.strokeStyle = '#FF2400';
    ctx.lineWidth = 2;
    ctx.stroke();
}


// Функция для отрисовки сетки
function drawGrid(ctx, width, height, gridSizeX, gridSizeY) {
    ctx.strokeStyle = '#191970'; // Цвет сетки
    ctx.lineWidth = 0.5; // Толщина линий сетки

    // Рисуем вертикальные линии
    for (let x = 0; x <= width; x += gridSizeX) {
        ctx.beginPath();
        ctx.moveTo(x, 0);
        ctx.lineTo(x, height);
        ctx.stroke();
    }

    // Рисуем горизонтальные линии
    for (let y = 0; y <= height; y += gridSizeY) {
        ctx.beginPath();
        ctx.moveTo(0, y);
        ctx.lineTo(width, y);
        ctx.stroke();
    }
}

// Функция для генерации массива случайных чисел
function generateRandomNumbers(count, min, max) {
    const randomNumbers = [];
    for (let i = 0; i < count; i++) {
        // Генерация случайного числа в диапазоне [min, max]
        const randomNumber = Math.floor(Math.random() * (max - min + 1)) + min;
        randomNumbers.push(randomNumber);
    }
    return randomNumbers;
}




