import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EncryptedDecrypted {

//    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) throws IOException {
        Util.print("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String path = Util.readConsole();

        Util.print("Введите ключ");
        int key = Integer.parseInt(Util.readConsole());

        Path newPath = Util.buildFullName(path, flag ? "_encrypted" : "_decrypted");

//        String content = Files.readString(Path.of(path)); // получаем все содержимое ввиде строки
//        Files.writeString(newPath, content); // запись всего содержимого по указанному адресу
//
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
//             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
//            while (reader.ready()) {
//                String string = reader.readLine();
//                String encryptDecrypt = flag? caesarCipher.encrypt(string, key) : caesarCipher.decrypt(string, key);
//                writer.write(encryptDecrypt + System.lineSeparator());
//            }
//        }

        String string = Util.readFile(path);
        String encryptDecrypt = flag? caesarCipher.encrypt(string, key) : caesarCipher.decrypt(string, key);
        Util.writeFile(newPath, encryptDecrypt + System.lineSeparator());

        Util.print("Содержимое файла " + newPath.getFileName() + (flag ? " зашифровано" : " расшифровано") +
                System.lineSeparator());
    }
}
