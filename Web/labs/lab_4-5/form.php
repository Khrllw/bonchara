<?php
if (isset($_POST["surname"]) && isset($_POST["prog"]) && isset($_POST["math"]) && isset($_POST["phys"])){
$surname = $_POST["surname"];
$prog_score = $_POST["prog"];
$math_score = $_POST["math"];
$phys_score = $_POST["phys"];
$avg_score = ($prog_score+$math_score+$phys_score)/3;
echo "Фамилия: ".$surname."<br>";
echo "Ваша оценка по программированию: ".$prog_score."<br>";
echo "Ваша оценка по математике: ".$math_score."<br>";
echo "Ваша оценка по физике: ".$phys_score."<br>";
echo "Ваш средний балл: ".$avg_score."<br>";
if ($avg_score >= 4){
echo "Вам назначена стипендия";
}
else{
echo "Вам не назначена стипендия(";
}}
?>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Лабораторная работа №4</title>
<link rel="stylesheet" type="text/css" href="style4.css">
</head>
<body>
<h1 class="radio_button all_center">Опрос</h1>
<form name="questionnaire" class="radio_button" method="post">
    <input type="text" name="surname" placeholder="Фамилия"><br>
    <label> Оценка по дисциплине «Программирование»:</label><br>
    <input type="radio" name="prog" value="5"
           checked="checked"><label>Отлично</label><br>
    <input type="radio" name="prog" value="4"><label>Хорошо</label><br>
    <input type="radio" name="prog"
           value="3"><label>Удовлетворительно</label><br>
    <label>Оценка по дисциплине «Математика»:</label><br>
    <input type="radio" name="math" value="5"
           checked="checked"><label>Отлично</label><br>
    <input type="radio" name="math" value="4"><label>Хорошо</label><br>
    <input type="radio" name="math"
           value="3"><label>Удовлетворительно</label><br>
    <label>Оценка по дисциплине «Физика»:</label><br>
    <input type="radio" name="phys" value="5"
           checked="checked"><label>Отлично</label><br>
    <input type="radio" name="phys" value="4"><label>Хорошо</label><br>
    <input type="radio" name="phys"
           value="3"><label>Удовлетворительно</label><br>
    <input type="submit" value="Отправить">
</form>
</body>
</html>

