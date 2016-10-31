package CustomFile;

import java.util.Date;
import java.util.Objects;

public class FileItem {
    private String data;
    private Date date;

    public FileItem(String data, Date date){
        this.data=data;
        this.date=date;
    }
    public Date getDate() {
        return date;
    }

    public String getData() {
        return data;
    }

    public boolean equals(FileItem fileItem) {
        return fileItem != null && Objects.equals(fileItem.getData(), this.data) && Objects.equals(fileItem.getDate(), this.date);
    }
    @Override
    public String toString() {
        return this.data + "\n" + this.date.getTime();
    }

    public FileItem copy() {
        String s = this.data;
        Date d = new Date(this.date.getTime());
        return new FileItem(s, d);

    }
}
