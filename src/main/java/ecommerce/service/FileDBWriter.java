package ecommerce.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileDBWriter {

    private FileDBWriter() {} // prevent instantiation

    public static void appendLine(String filePath, String data) {
        try {
            File file = new File(filePath);

            // ensure parent folder exists
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(data + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
