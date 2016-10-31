package CustomFile.ReaderWriter;
public class ReaderWriterCreator {
    public static IRWFactory create(String type) {
        switch (type) {
            case "txt":
                return new TXTFactory();
            case "xml":
                return new XMLFactory();
            default:
                throw new RuntimeException("Unsuported type of file: " + type);
        }
    }
}
