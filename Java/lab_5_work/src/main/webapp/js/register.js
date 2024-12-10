// Функция обработки формы регистрации
function sendFormData() {

    // Получение данных формы
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var email = document.getElementById("email").value;

    // Формирование HTTP запроса
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "registerServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Обработка ответа сервера
    xhr.onload = function () {
        let response;

        // Ответ успешно получен
        if (xhr.status === 200) {
            response = Number(xhr.responseText);

            // Обработка ответа сервера
            switch (response){
                // Перенаправление на страницу авторизации
                case 0: alert("Registration was successful"); location.replace(window.location.protocol + "//" + location.host + "/lab_5_war/"); break;
                // Вывод сообщения об ошибке
                case 1: alert("The user already exists"); break;
                case 2: alert("Something went wrong"); break;
            }
        }
        // Вывод сообщения об ошибке
        else {
            alert("Error: " + xhr.status);
        }
    };

    // Подготовка данных для запроса
    var data =
        "username=" + encodeURIComponent(username) +
        "&password=" + encodeURIComponent(password) +
        "&email=" + encodeURIComponent(email);

    // Отправка запроса сервлету
    xhr.send(data);
}
