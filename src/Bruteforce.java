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

//    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    public void bruteforce () throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> strings = new ArrayList<>();
        Util.print("Введите путь к фалу для его расшифровки");
        String path = Util.readConsole();

        Path bruteforce = Util.buildFullName(path, "_bruteforce");

        String str = Util.readFile(path);
        builder.append(str).append(System.lineSeparator());
        strings.add(str);

        for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
            String decrypt = caesarCipher.decrypt(builder.toString(), i);
            if (isValidateText(decrypt)) {
                for (String string : strings) {
                    String encrypt = caesarCipher.decrypt(string, i);
                    Util.writeFile( bruteforce,encrypt + System.lineSeparator());
                }
                Util.print("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = "
                        + i + System.lineSeparator());
                break;
            }
        }




//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
//             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {
//            StringBuilder builder = new StringBuilder();
//            List<String> strings = new ArrayList<>();
//            while (reader.ready()) {
//                String string = reader.readLine();
//                builder.append(string).append(System.lineSeparator());
//                strings.add(string);
//            }
//            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
//                String decrypt = caesarCipher.decrypt(builder.toString(), i);
//                if (isValidateText(decrypt)) {
//                    for (String string : strings) {
//                        String encrypt = caesarCipher.decrypt(string, i);
//                        writer.write(encrypt + System.lineSeparator());
//                    }
//                    Util.print("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = "
//                            + i + System.lineSeparator());
//                    break;
//                }
//            }
//        }

    }

    private boolean isValidateText (String text) throws IOException {

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
            Util.print(substring);
            Util.print("Понятен ли текст? (y/n)");
            String answer = Util.readConsole();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                Util.print("Выберите только между y или n");
            }
        }
        return false;
    }
}
