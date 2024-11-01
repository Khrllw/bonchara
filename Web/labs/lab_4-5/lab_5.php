<?php
 $servername = "localhost";
 $username = "root";
 $password = "";
 $dbname = "socialsite";

 $conn = mysqli_connect($servername, $username, $password, $dbname);
 // Check connection
 if ($conn->connect_error) {
     die("Connection failed: "
         . $conn->connect_error);}

 mysqli_set_charset($conn, 'utf8mb4');

 
$users = $conn->query("SELECT * FROM users");
while ($row = $users->fetch_array()) {
    echo "ID: ", $row['id'], '<br>';
    echo "Nickname: ", $row['nickname'], '<br>';
    echo "Login: ", $row['login'], '<br>';
    echo '<br>';
}
?>