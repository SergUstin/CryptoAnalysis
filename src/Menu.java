import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("""
                    Выберите действие введя его номер:\s
                    1. Зашифровать текст с помощью ключа.\s
                    2. Расшифровать текст с помощью ключа.\s
                    3. Подобрать ключ.
                    4. Расшифровать текст с помощью синтаксического анализа.\s
                    5. Выход из программы.\s
                    """);

            String answer = reader.readLine();
            switch (answer) {
                case "1" -> new EncryptedDecrypted().encryptedDecrypted(true);
                case "2" -> new EncryptedDecrypted().encryptedDecrypted(false);
                case "3" -> new Bruteforce().bruteforce();
                case "4" -> System.out.println("Расшифровать текст с помощью синтаксического анализа.");
                case "5" -> {
                    return;
                }
            }
        }
    }
}
