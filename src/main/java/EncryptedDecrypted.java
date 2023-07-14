import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {

    private final CaesarCipher caesarCipher = new CaesarCipher();

    @SneakyThrows
    public void encryptedDecrypted(boolean flag) {
        Util.writeMassage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String path = Util.readString();

        Util.writeMassage("Введите ключ");
        int key = Util.readInt();

        Path newPath = Util.buildFullName(path, flag ? "_encrypted" : "_decrypted");

        String content = Files.readString(Path.of(path));

//        Files.writeString(newPath, content);

        String encryptDecrypt = flag? caesarCipher.encrypt(content, key) : caesarCipher.decrypt(content, key);
        Files.writeString(newPath, encryptDecrypt);

        Util.writeMassage("Содержимое файла " + newPath.getFileName() + (flag ? " зашифровано" : " расшифровано") +
                System.lineSeparator());
    }
}
