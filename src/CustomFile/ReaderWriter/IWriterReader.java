package CustomFile.ReaderWriter;

import java.util.ArrayList;
import CustomFile.*;

public interface IWriterReader {
    void write(CustomFile customFile);

    ArrayList<FileItem> read();
}
