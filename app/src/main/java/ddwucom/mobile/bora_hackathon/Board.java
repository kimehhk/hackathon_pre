package ddwucom.mobile.bora_hackathon;

import java.io.Serializable;

public class Board implements Serializable {
    long _id;
    String text;

    public Board(long _id, String text) {
        this._id = _id;
        this.text = text;
    }

    public long get_id() {
        return _id;
    }

    public String getText() {
        return text;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".\t\t");
        sb.append(text);
        sb.append(")");
        return sb.toString();
    }
}
