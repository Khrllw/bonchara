function show_information() {
    alert("Харлова Алёна Александровна\nГруппа ИКПИ-23\nПрактическая №4");
}

function show_information2(elem) {
    let x = parseInt(elem.value);
    if (!isNaN(x) && 3.0 <= x && x <= 5.0) {
        alert("Ваш средний балл – " + elem.value + " балла");
    } else {
        alert("Нет данных!");
    }
}

function send(family, radio1, radio2, radio3) {
    let markProg = parseInt(radio1.value),
        markMath = parseInt(radio2.value),
        markPhyz = parseInt(radio3.value);
    document.write("<h2>Фамилия: " + family.value + "</h2>");
    document.write(
        "<h2>Оценка по дисциплине «Программирование»: " + markProg + "</h2>"
    );
    document.write(
        "<h2>Оценка по дисциплине «Математика»: " + markMath + "</h2>"
    );
    document.write("<h2>Оценка по дисциплине «Физика»: " + markPhyz + "</h2>");
    document.write(
        "<h2>Рейтинг: " + (markProg + markMath + markPhyz) / 3 + "</h2>"
    );
}