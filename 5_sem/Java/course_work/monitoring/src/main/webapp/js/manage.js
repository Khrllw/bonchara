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


// Функция обработки формы изменения данных пользователя
async function updateUserInfo() {
    const password = document.getElementById("password").value;

    let hash = await hashPassword(password);
    const data = {
        accessRights: document.getElementById("access-rights").value,
        userName: document.getElementById("username").value,
        lastName: document.getElementById("lastname").value,
        firstName: document.getElementById("firstname").value,
        patronymic: document.getElementById("patronymic").value,
        passwordHashed: hash
    };

    // Формирование HTTP запроса
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "updateUserServlet", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.responseType = 'json';
    console.log(JSON.stringify(data, null, 2));

    // Обработка ответа сервера
    xhr.onload = function () {
        // Запрос завершен
        if (xhr.status === 200) {
            currentData = xhr.response;
            console.log("Данные с сервера:", currentData);
            if (currentData != null) {
            }
        }
        // Вывод сообщения об ошибке
        else {
            alert("Error: " + xhr.status);
        }
    };
    console.log(JSON.stringify(data, null, 2));
    // Отправка запроса сервлету
    xhr.send(JSON.stringify(data, null, 2));
}

function deleteUser(){

    // Получение данных формы
    const username = document.getElementById("delete-username").value;


    // Формирование HTTP запроса
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "deleteUserServlet", true);
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
                    alert("Deleted");
                    break;
            }
        }
        // Вывод сообщения об ошибке
        else {
            alert("Error: " + xhr.status);
        }
    };

    // Подготовка данных для запроса
    const data =
        "username=" + encodeURIComponent(username);

    // Отправка запроса сервлету
    xhr.send(data);
}
