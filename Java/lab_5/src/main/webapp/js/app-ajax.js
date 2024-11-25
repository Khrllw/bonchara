// вызов функции по завершению загрузки страницы
$(document).ready(function() {
    // вызов функции после потери полем 'userName' фокуса
    $('#username').blur(function() {
        $.ajax({
            url : 'test',     // URL - сервлет
            data : {                 // передаваемые сервлету данные
                userName : $('#username').val()
            },
            success : function(response) {
                // обработка ответа от сервера
                //alert(response);
                //$('#ajaxUserServletResponse').text(response);
            }
        });
    });
});