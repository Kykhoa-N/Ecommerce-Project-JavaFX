package ecommerce.uiHelper;

import ecommerce.model.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.*;

public class FileDBLoader {

    private FileDBLoader() {}

    public static <T> List<T> load(
            String path,
            Function<String, T> creator
    ) {
        List<T> result = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) return result;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    result.add(creator.apply(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
