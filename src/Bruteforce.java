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

public class Bruteforce {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    public void bruteforce () throws IOException {
        System.out.println("Введите путь к фалу для его расшифровки");
        String path = scanner.nextLine();

        Path bruteforce = PathHelper.buildFullName(path, "_bruteforce");


        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {
            StringBuilder builder = new StringBuilder();
            List<String> strings = new ArrayList<>();
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string).append(System.lineSeparator());
                strings.add(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String decrypt = caesarCipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    for (String string : strings) {
                        String encrypt = caesarCipher.decrypt(string, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText (String text) {

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
