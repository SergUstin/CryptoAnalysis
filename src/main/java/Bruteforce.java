
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Bruteforce {

    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    @SneakyThrows
    public void bruteforce() {
        Util.writeMassage("Введите путь к фалу для его расшифровки");
        String path = Util.readString();

        Path bruteforce = Util.buildFullName(path, "_bruteforce");

        String content = Files.readString(Path.of(path));
        for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
            String decrypt = caesarCipher.decrypt(content, i);
            if (isValidateText(decrypt)) {
                Files.writeString(bruteforce, decrypt);
                Util.writeMassage("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = "
                        + i + System.lineSeparator());
                break;
            }
        }
    }

    private boolean isValidateText(String text) {

        boolean isValidate = false;

        for (String word : text.split(" ")) {
            if (word.length() > MAX_LENGTH_WORD) {
                return false;
            }
        }
        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
            isValidate = true;
        }

        while (isValidate) {
            int indexStart = new Random().nextInt(text.length() / 2);
            String substring = text.substring(indexStart, indexStart + (int) Math.sqrt(text.length()));
            Util.writeMassage(substring);
            Util.writeMassage("Понятен ли текст? (y/n)");
            String answer = Util.readString();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                Util.writeMassage("Выберите только между y или n");
            }
        }
        return false;
    }
}
