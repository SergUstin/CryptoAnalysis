import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Переимоновать класс в Util, добавить три статических метода, для вывода и чтение строки, чтения числа
// Использовать буфер ридер как основной способ
// Удалить Scanner и BufferReader
// Подключить к проекту maven
public class Util {
    private Util() {

    }

    public static Path buildFullName(String path, String suffix) {
        Path fullPath = Paths.get(path);
        Path parent = fullPath.getParent();
        String fileName = fullPath.getFileName().toString();
        String newFileName;
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0, index) + suffix + fileName.substring(index);
        } else {
            newFileName = fileName + suffix;
        }

        return parent.resolve(Path.of(newFileName));
    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static String readConsole () throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

    public static void writeFile (Path newPath, String content) throws IOException {
        Files.writeString(newPath, content);
    }
}
