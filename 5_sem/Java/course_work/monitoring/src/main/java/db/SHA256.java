package db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    // Метод для хеширования строки с использованием SHA-256
    public static String hashSHA256(String input) {
        try {
            // Создаем объект MessageDigest с алгоритмом SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Преобразуем строку в массив байтов
            byte[] hashBytes = digest.digest(input.getBytes());

            // Преобразуем массив байтов в строку в формате hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при хешировании: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Пример строки для хеширования
        String password = "1";

        // Хешируем строку
        String hashedPassword = hashSHA256(password);

        // Выводим результат
        System.out.println("Исходный пароль: " + password);
        System.out.println("Хешированный пароль (SHA-256): " + hashedPassword);
    }
}