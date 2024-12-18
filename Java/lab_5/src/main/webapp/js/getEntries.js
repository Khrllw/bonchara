function sendUserChanges(array) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "updateServlet", true);

    xhr.setRequestHeader("Content-Type", "application/json");

    // Обработка ответа сервера
    xhr.onload = function () {
        let response; // Запрос завершен
        if (xhr.status === 200) {
            response = Number(xhr.responseText);
            switch (response) {
                case 1:
                    // Вывод сообщения об ошибке
                    alert("Data could not be updated. Try again");
                    break;
                case 0:
                    alert("Data has been successfully updated");
                    document.location.reload();
            }
        } else {
            alert("Error: " + xhr.status);
        }

    };
    xhr.send(JSON.stringify(array));
}

document.getElementById('update-button').addEventListener('click', () => {
    const arr = [];
    const rows = document.getElementById('editableTable').getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');

        const obj = {
            name: cells[0].getElementsByTagName('label')[0].getElementsByTagName('input')[0].value,
            date: cells[1].getElementsByTagName('label')[0].getElementsByTagName('input')[0].value,
            quality: cells[2].getElementsByTagName('label')[0].getElementsByTagName('select')[0].value,
            dream: cells[3].getElementsByTagName('label')[0].getElementsByTagName('select')[0].value,
            description: cells[4].getElementsByTagName('label')[0].getElementsByTagName('input')[0].value
        };

        arr.push(obj);
    }
    sendUserChanges(arr);
});