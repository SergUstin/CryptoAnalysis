import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// Разобраться почему не работает.
// Подсказка: Проблема в Scanner (в методе nextInt).
// Получить вариант зашифрованного текста. Посмотреть почему не верное форматирование.
// Написать класс Decrypted. Аналог класса Encrypted.
// Написать общий метод, который бы работал как Encrypted и Decrypted, что бы избавиться от дублирующего кода.
// *Изучить класс Path/File. Подумай как не спрашивать у пользователя путь для записи зашиврованного/расшифрованного файла.
// *Подумай как можно добавить суффикс к названию файла:
  // - Если расшифровали, добавь суффикс _encrypted
  // - Если зашифровали, добавь суффикс _decrypted
  // Суффиксы должны добавляться к имени файла историей.

public class Encrypted {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void encrypted () throws IOException {
        System.out.println("Введите путь к файлу для зашифровки");
        String path = scanner.nextLine();
        System.out.println("Введите ключ шифрования");
        int key = scanner.nextInt();
        System.out.println("Введите путь, для записи зашифрованного текста");
        String path2 = scanner.nextLine();


        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(path2))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encrypt = caesarCipher.encrypt(string, key);
                writer.write(encrypt);
            }
        }

        System.out.println("Содержимое файла было зашифровано");



    }

}
