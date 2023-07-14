import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {

    private final Map<Character, Integer> encrypted = new HashMap<>();
    private final Map<Character, Integer> statistic = new HashMap<>();
    private final Map<Character, Character> decrypted = new HashMap<>();

    @SneakyThrows
    public void parse() {
        Util.writeMassage("Введите путь до зашифрованного файла");
        String path = Util.readString();
        Util.writeMassage("Введите путь к файлу для набора статистики");
        String pathStatistic = Util.readString();

        Path parsing = Util.buildFullName(path, "_statistic");

        List<Map.Entry<Character, Integer>> listEncrypted = fillMapAndConvertToList(encrypted, path);
        List<Map.Entry<Character, Integer>> listStatistic = fillMapAndConvertToList(statistic, pathStatistic);


        if (listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                decrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
        } else {
            Util.writeMassage("Размер файла статистики меньше, предоставьте файл больше!");
        }

        String content = Files.readString(Path.of(path));
        StringBuilder builder = new StringBuilder();
        for (char encryptedChar : content.toCharArray()) {
            Character decryptedChar = decrypted.get(encryptedChar);
            builder.append(decryptedChar);
        }
        Files.writeString(parsing, builder);

        Util.writeMassage("Содержимое файла расшифровано методом статистического анализа!" + System.lineSeparator());
    }

    @SneakyThrows
    private List<Map.Entry<Character, Integer>> fillMapAndConvertToList(Map<Character, Integer> map, String path) {

        for (char aChar : Files.readString(Path.of(path)).toCharArray()) {
            encrypted.merge(aChar, 1, Integer::sum);
        }

        return map.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).toList();

    }
}
