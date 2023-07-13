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
// Переделать с внесенными правками!!
public class Util {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

    public static void writeMassage(String massage) {
        System.out.println(massage);
    }

    public static String readString () {
        String string;
        try {
            string = reader.readLine();
        } catch (IOException e) {
            writeMassage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз!");
            string = readString();
        }
        return string;
    }

    public static int readInt () {
        int number;
        try {
            number = Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            writeMassage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз!");
            number = readInt();
        }
        return number;
    }
}
