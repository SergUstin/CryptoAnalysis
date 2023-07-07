import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bruteforce_2 {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    public void bruteforce() throws IOException {
        System.out.println("Введите путь к файлу для его расшифровки");
        String path = scanner.nextLine();

        Path bruteforce = Util.buildFullName(path, "_bruteforce");

        String content = Files.readString(Paths.get(path));
        for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
            String decrypt = caesarCipher.decrypt(content, i);
            if (isValidateText(decrypt)) {
                Files.writeString(bruteforce, decrypt);
                System.out.println("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = "
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
            System.out.println(substring);
            System.out.println("Понятен ли текст? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                System.out.println("Выберите только между y или n");
            }
        }
        return false;
    }
}
