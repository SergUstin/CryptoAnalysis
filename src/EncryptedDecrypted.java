import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptedDecrypted {

    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) throws IOException {
        Util.writeMassage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String path = Util.readString();

        Util.writeMassage("Введите ключ");
        int key = Util.readInt();

        Path newPath = Util.buildFullName(path, flag ? "_encrypted" : "_decrypted");

        String content = Files.readString(Path.of(path)); // получаем все содержимое ввиде строки
        String encryptDecrypt = flag? caesarCipher.encrypt(content, key) : caesarCipher.decrypt(content, key);
        Files.writeString(newPath, encryptDecrypt + System.lineSeparator()); // запись всего содержимого по указанному адресу

//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
//             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
//            while (reader.ready()) {
//                String string = reader.readLine();
//                String encryptDecrypt = flag? caesarCipher.encrypt(string, key) : caesarCipher.decrypt(string, key);
//                writer.write(encryptDecrypt + System.lineSeparator());
//            }
//        }

        Util.writeMassage("Содержимое файла " + newPath.getFileName() + (flag ? " зашифровано" : " расшифровано") +
                System.lineSeparator());
    }
}
