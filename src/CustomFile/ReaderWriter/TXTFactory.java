package CustomFile.ReaderWriter;
public class TXTFactory implements IRWFactory {
    @Override
    public IWriterReader createWriterReader(String name) {
        return new TXTWriterReader(name);
    }
}
