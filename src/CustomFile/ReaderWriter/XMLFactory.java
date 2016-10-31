package CustomFile.ReaderWriter;

public class XMLFactory implements IRWFactory {
    @Override
    public IWriterReader createWriterReader(String name) {
        return new XMLWriterReader(name);
    }
}
