import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Выбирите действие введя его номер: " +
                    "1. Заштфровать текст с помощью ключа. " +
                    "2. Расшифровать текст с помощью ключа. " +
                    "3. Подобрать ключ." +
                    "4. Расшифровать текст с помощью синтаксического анализа. " +
                    "5. Выход из программы. ");

            String answer = reader.readLine();
            switch (answer) {
                case "1" -> System.out.println("Заштфровать текст с помощью ключа.");
                case "2" -> System.out.println("Расшифровать текст с помощью ключа");
                case "3" -> System.out.println("Подобрать ключ.");
                case "4" -> System.out.println("Расшифровать текст с помощью синтаксического анализа.");
                case "5" -> {
                    return;
                }
            }
        }
    }
}
