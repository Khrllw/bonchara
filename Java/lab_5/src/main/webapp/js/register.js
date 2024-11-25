// Обработка формы регистрации
// Вызов функции при нажатии кнопки регистрации новго пользователя
$('#sign_up').click(function () {
    $.ajax({
        // URL-имя сервлета для обработки данных
        url: 'register',

        // Передаваемые сервлету данные
        data: {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val()
        },

        // Обработка ответа от сервера
        success: function (response) {
            // Вывод сообщения об ошибке
            if (response != null) {
                alert(response);
            }
        }
    });
});