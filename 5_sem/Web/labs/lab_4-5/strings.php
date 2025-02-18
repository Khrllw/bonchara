<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Лабораторная работа №4</title>
    <style>
    body {
        display: flex;
        align-content: center;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        height: 100vh;
        margin:0;
        font-family: Arial;
    }

    form {
        display: flex;
        flex-direction: column;
        width: fit-content;
        background-color: lightgrey;
        padding: 2%;
        margin: auto;
        align-items: center;
        border-radius: 2%;
    }

    input {
        padding: 5px;
        border-radius: 2px;
        border: none;
    }
    </style>
</head>
<body>
<p>Количество введенных слов: <?php
               $data = isset($_GET["data"]) ? $_GET["data"] : '';
               echo str_word_count($data);
           ?></p>
<form>
    <label>Введите строку:</label><br>
    <input type="text" name="data" placeholder="Your string"><br>
    <input type="submit" value="Посчитать">
</form>

</body>
</html>
