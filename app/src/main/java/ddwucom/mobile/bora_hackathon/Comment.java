package ddwucom.mobile.bora_hackathon;

public class Comment {
    long _id;
    String comment;
    String date;

    public Comment(long _id, String comment, String date) {
        this._id = _id;
        this.comment = comment;
        this.date = date;
    }

    public long get_id() {
        return _id;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".\t\t");
        sb.append(comment);
        sb.append(".\t\t");
        sb.append(date);
        sb.append(")");
        return sb.toString();
    }

}
