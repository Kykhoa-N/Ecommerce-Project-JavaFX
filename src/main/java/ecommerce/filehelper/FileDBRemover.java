package ecommerce.filehelper;

import java.io.*;
import java.util.*;

public class FileDBRemover {

    private FileDBRemover() {}

    // Remove the first line whose first CSV field equals `name`
    public static boolean removeByName(String path, String name) {
        File file = new File(path);
        if (!file.exists()) return false;

        List<String> kept = new ArrayList<>();
        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!removed && matchesFirstField(line, name)) {
                    removed = true; // skip this line (delete it)
                    continue;
                }
                kept.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!removed) return false;

        // rewrite entire file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String line : kept) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean matchesFirstField(String line, String name) {
        if (line == null) return false;

        String trimmed = line.trim();
        if (trimmed.isEmpty()) return false;

        int comma = trimmed.indexOf(',');
        String first = (comma == -1) ? trimmed : trimmed.substring(0, comma);

        return first.equals(name);
    }
}