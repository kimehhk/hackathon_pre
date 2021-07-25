package ddwucom.mobile.bora_hackathon;

import java.io.Serializable;

public class Board implements Serializable {
    long _id;
    String context;
    String title;
    String date;
    int post_id;

    public Board(long _id, String context, String title, String date) {
        this._id = _id;
        this.context = context;
        this.title = title;
        this.date = date;
    }

    public Board(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public long get_id() {
        return _id;
    }

    public String getContext() {
        return context;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".\t\t");
        sb.append(context);
        sb.append(".\t\t");
        sb.append(title);
        sb.append(".\t\t");
        sb.append(date);
        sb.append(")");
        return sb.toString();
    }
}
