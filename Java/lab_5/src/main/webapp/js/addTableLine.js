// Получаем кнопку и контейнер
const addButton = document.getElementById('addButton');
const container = document.getElementById('editableTable-tbody');

// Добавляем обработчик события клика
addButton.addEventListener('click', function () {
    // Создаем новый элемент
    const newElement = document.createElement('tr');
    newElement.innerHTML = ` <td>
            <label>
            <input type="text" placeholder="EMPTY" class="table-input">
            </label>
    </td>
        <td>
            <label>
                <input type="date" data-placeholder="EMPTY" class="table-input" required aria-required="true">
            </label>
        </td>
        <td>
            <label>
                <select>
                    <option value=1 >1/10</option>
                <option value=2 >2/10</option>
            <option value=3 >3/10</option>
        <option value=4 >4/10</option>
        <option value=5 >5/10</option>
        <option value=6 >6/10</option>
        <option value=7 >7/10</option>
        <option value=8 >8/10</option>
        <option value=9 >9/10</option>
        <option value=10>10/10</option>
    </select>
    </label>
    </td>
    <td>
       <label>
                <select>
            <option value=true>true</option>
        <option value=false selected>false</option>
          </select>
       </label>
    </td>
        <td>
            <label>
                <input type="text" placeholder="EMPTY" class="table-input">
            </label>
        </td>`;

    // Вставляем новый элемент в контейнер
    container.appendChild(document.createElement('tbody').appendChild(newElement));
});