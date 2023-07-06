import java.nio.file.Path;
import java.nio.file.Paths;
// Переимоновать класс в Util, добавить три статических метода, для вывода и чтение строки, чтения, числа
// ИСпользоватьл буфер ридер как основной способ
// Удалить Scanner и BufferReader
// Подключить к проекту maven
public class PathHelper {
    private PathHelper() {

    }
    public static Path buildFullName (String path, String suffix) {
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
}
