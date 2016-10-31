package CustomFile.ReaderWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import CustomFile.*;


public class TXTWriterReader implements IWriterReader {
    private String fileName;

    public TXTWriterReader(String fileName) {
        this.fileName = fileName + ".txt";
    }

    @Override
    public ArrayList<FileItem> read() {
        String line, line1;
        ArrayList<FileItem> file = new ArrayList<>();
        try {
            File readFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            line = br.readLine();
            if (line != null) {
                line1 = br.readLine();
                file.add(new FileItem(line, new Date(Long.parseLong(line1))));

            } else {
                file.add(new FileItem("0", new Date()));
                return file;
            }
            while ((line = br.readLine()) != null) {
                line1 = br.readLine();
                file.add(new FileItem(line, new Date(Long.parseLong(line1))));
            }

            return file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void write(CustomFile file) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
