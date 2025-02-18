async function hashPassword(password) {
    // Преобразуем пароль в массив байтов
    const encoder = new TextEncoder();
    const data = encoder.encode(password);

    // Хешируем с использованием SHA-256
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);

    // Преобразуем хеш в строку
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    console.log(hashArray.map(b => b.toString(16).padStart(2, '0')).join(''));
    return hashArray.map(b => b.toString(16).padStart(2, '0')).join('').toString();
}


// Функция обработки формы авторизации
async function sendFormData() {

    // Получение данных формы
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    let hash = await hashPassword(password);


    // Формирование HTTP запроса
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "loginServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Обработка ответа сервера
    xhr.onload = function () {
        let response;

        // Ответ успешно получен
        if (xhr.status === 200) {
            response = Number(xhr.responseText);
            switch (response) {
                case 1:
                    // Вывод сообщения об ошибке
                    alert("Invalid username or password");
                    break;
                case 0:
                    // Перенаправление в линый кабинет пользователя
                    location.replace(window.location.protocol + "//" + location.host + "/monitoring_war_exploded" + "/general.jsp");
            }
        }
        // Вывод сообщения об ошибке
        else {
            alert("Error: " + xhr.status);
        }
    };

    // Подготовка данных для запроса
    const data =
        "username=" + encodeURIComponent(username) +
        "&password=" + encodeURIComponent(hash);

    // Отправка запроса сервлету
    xhr.send(data);
}
