// Обработка формы входа
$(document).ready(function() {

// Вызов функции при нажатии кнопки входа в аккаунт
$('#sign_in').click(function () {
    $.ajax({
        // URL-имя сервлета для обработки данных
        url: 'login',

        // Передаваемые сервлету данные
        data: {
            username: $('#username').val(),
            password: $('#password').val()
        },

        // Обработка ответа от сервера
        success: function (response) {
            // Вывод сообщения об ошибке
            if (response != null) {
                alert(response);
            }
            console.log("RESP " + response);
            alert(response);
            $("header").text(response);
        }
    });
});});