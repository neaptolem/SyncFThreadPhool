package CustomFile;
import java.util.ArrayList;

import CustomFile.EventHandler.ChangeHandler;
import CustomFile.ReaderWriter.*;
public class CustomFile {
    private ArrayList<FileItem> data = new ArrayList<>();
    private IWriterReader writerReaderFileItem = ReaderWriterCreator.create("xml").createWriterReader("sync");
    private ArrayList<ChangeHandler> changeHandlers=new ArrayList<>();

    public CustomFile(ArrayList<FileItem> fileItemArrayList) {
        this.data = fileItemArrayList;
    }


    public CustomFile() {}

    public synchronized void load(){
        data = writerReaderFileItem.read();
        onChange();
    }
    public void save(){
        writerReaderFileItem.write(this);
    }

    public void compare(){
        ArrayList<FileItem> fileItems = writerReaderFileItem.read();
        if (! this.equals(fileItems)){
            this.data=fileItems;
            onChange();
        }
    }

    public synchronized String toPrint() {
        String s = new String();
        for (FileItem item : data) {
            s += item.getData() + "\n" + item.getDate() + "\n";
        }
        return s;
    }

    @Override
    public String toString(){
        String s = new String();
        for (FileItem item : data) {
            s += item.toString() + "\n";
        }
        return s;
    }

    public boolean eq(ArrayList<FileItem> fileItems){
        int size = Math.max(fileItems.size(), this.data.size());
        for (int i = 0; i < size; i++) {
            if (! this.data.get(i).equals(fileItems.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(CustomFile customFile) {
        ArrayList<FileItem> customFileData = customFile.getData();
        int size = Math.max(customFileData.size(), this.data.size());
        for (int i = 0; i < size; i++) {
            if (!(this.data.get(i)).equals(customFileData.get(i))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<FileItem> getData() {
        return this.data;
    }

    public synchronized void setData(ArrayList<FileItem> data) {
        if (!this.equals(data)) {
            this.data = data;
            onChange();
        }
    }

    public CustomFile copy() {
        ArrayList<FileItem> copyData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            copyData.add(data.get(i).copy());
        }
        CustomFile customfile = new CustomFile();
        customfile.data = copyData;
        return customfile;
    }

    public void addToListener(ChangeHandler changeHandler){
        changeHandlers.add(changeHandler);
    }
    private void onChange(){
        for(ChangeHandler item:changeHandlers){
            item.onChange();
        }
    }
}
