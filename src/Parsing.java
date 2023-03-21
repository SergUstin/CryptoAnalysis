import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Parsing {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<Character, Integer> encrypted = new HashMap<>();
    private final Map<Character, Integer> statistic = new HashMap<>();
    private final Map<Character, Character> decrypted = new HashMap<>();

    public void parse () throws IOException {
        System.out.println("Введите путь до зашифрованного файла");
        String path = scanner.nextLine();
        System.out.println("Введите путь к файлу для набора статистики");
        String pathStatistic = scanner.nextLine();

        Path parsing = PathHelper.buildFullName(path, "_statistic");

        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(fillMapValues(encrypted, path));
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(fillMapValues(statistic, pathStatistic));


        if (listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                decrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
        } else {
            System.out.println("Размер файла статистики меньше, предоставьте файл больше!");
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path));
             BufferedWriter writer = Files.newBufferedWriter(parsing)) {
            while (reader.ready()) {
                StringBuilder builder = new StringBuilder();
                String string = reader.readLine();
                for (char encryptedChar : string.toCharArray()) {
                    Character decryptedChar = decrypted.get(encryptedChar);
                    builder.append(decryptedChar);
                }
                writer.write(builder + System.lineSeparator());
            }
        }

        System.out.println("Содержимое файла расшифровано методом статистического анализа!" + System.lineSeparator());
    }

    private Map<Character, Integer> fillMapValues (Map<Character, Integer> map, String path) throws IOException {

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
            for (char aChar : builder.toString().toCharArray()) {
                if (!map.containsKey(aChar)) {
                    map.put(aChar, 1);
                } else {
                    map.put(aChar, map.get(aChar) + 1);
                }
//                encrypted.merge(aChar, 1, Integer::sum);
            }
        }
        return map;
    }

    private List<Map.Entry<Character, Integer>> mapToList (Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(entries);
        Comparator<Map.Entry<Character, Integer>> comparator = (o1, o2) -> o2.getValue() - o1.getValue();
        list.sort(comparator);
        return list;
    }
}
