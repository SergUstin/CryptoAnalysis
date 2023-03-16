import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Parsing {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<Character, Integer> encrypted = new HashMap<>();
    private final Map<Character, Integer> statistic = new HashMap<>();

    public void parse () throws IOException {
        System.out.println("Введите путь до зашифрованного файла");
        String path = scanner.nextLine();
        System.out.println("Введите путь к файлу для набора статистики");
        String pathStatistic = scanner.nextLine();

        Map<Character, Integer> characterIntegerMap = fillMapValues(encrypted, path);
        Map<Character, Integer> characterIntegerMap1 = fillMapValues(statistic, pathStatistic);


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

    private void method (Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();

    }
}
