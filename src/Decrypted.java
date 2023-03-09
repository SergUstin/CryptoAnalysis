import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Decrypted {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void decrypted () throws IOException {
        System.out.println("Введите путь к файлу для расшифровки");
        String path = scanner.nextLine();
        System.out.println("Введите ключ шифрования");
        int key = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите путь для записи расшифрованного файла");
        String path2 = scanner.nextLine();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(path2))) {
            while (reader.ready()) {
                String string = reader.readLine();
                String decrypt = caesarCipher.decrypt(string, key);
                writer.write(decrypt);
            }
        }

        System.out.println("Содержимое файла было расшифровано");

    }
}
