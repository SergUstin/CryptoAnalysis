
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bruteforce {

    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    public void bruteforce() throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> strings = new ArrayList<>();
        Util.writeMassage("Введите путь к фалу для его расшифровки");
        String path = Util.readString();

        Path bruteforce = Util.buildFullName(path, "_bruteforce");

        String content = Files.readString(Path.of(path));
        builder.append(content).append(System.lineSeparator());
        strings.add(content);
        for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
            String decrypt = caesarCipher.decrypt(builder.toString(), i);
            if (isValidateText(decrypt)) {
                for (String string : strings) {
                    String encrypt = caesarCipher.decrypt(string, i);
                    Files.writeString(bruteforce, encrypt + System.lineSeparator());
                }
                Util.writeMassage("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = "
                        + i + System.lineSeparator());
                break;
            }
        }


//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
//             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {
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
//                    Util.writeMassage("Содержимое файла расшифровано, методом подбора ключей. Ключ расшифровки K = "
//                            + i + System.lineSeparator());
//                    break;
//                }
//            }
//        }

    }

    private boolean isValidateText(String text) throws IOException {

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
