import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    public static void main(String[] args) {

        while (true) {
            Util.writeMassage("""
                    Выберите действие введя его номер:\s
                    1. Зашифровать текст с помощью ключа.\s
                    2. Расшифровать текст с помощью ключа.\s
                    3. Подобрать ключ.
                    4. Расшифровать текст с помощью синтаксического анализа.\s
                    5. Выход из программы.\s
                    """);

            switch (Util.readString()) {
                case "1" -> new EncryptedDecrypted().encryptedDecrypted(true);
                case "2" -> new EncryptedDecrypted().encryptedDecrypted(false);
                case "3" -> new Bruteforce().bruteforce();
                case "4" -> new Parsing().parse();
                case "5" -> {
                    return;
                }
            }
        }
    }
}
