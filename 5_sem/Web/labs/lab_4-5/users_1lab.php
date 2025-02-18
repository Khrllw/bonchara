<?php
include 'index_1.html';

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "users_1lab";

$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: "
        . $conn->connect_error);
    echo "BAD";}

mysqli_set_charset($conn, 'utf8mb4');
if (isset($_POST["login"]) && isset($_POST["password"]) && isset($_POST["mail"]) && 
    strlen($_POST["login"]) >=3 && strlen($_POST["login"]) <= 15 &&
    strlen($_POST["password"]) >= 10 && strlen($_POST["password"]) <= 15){
    $login = mysqli_real_escape_string($conn, $_POST["login"]);
    $password = password_hash(mysqli_real_escape_string($conn, $_POST["password"]), PASSWORD_DEFAULT);
    $country = mysqli_real_escape_string($conn, $_POST["country"]);
    $mail = mysqli_real_escape_string($conn, $_POST["mail"]);
    $age = mysqli_real_escape_string($conn, $_POST["age"]);
    $hobby = mysqli_real_escape_string($conn, $_POST["hobby"]);
    
    $sql = "INSERT INTO users (login, password, country, mail, age, hobby) VALUES ('$login', '$password', '$country', '$mail', '$age', '$hobby')";
    mysqli_query($conn, "use users_1lab");
    if(mysqli_query($conn, $sql)){
    echo "Данные успешно добавлены";
    } else{
    echo "Ошибка: ".mysqli_error($conn);
    }
}
else{
    echo "Неправильно заполнена форма";
}
?>