package ddwucom.mobile.bora_hackathon;

import java.io.Serializable;

public class Board implements Serializable {
    int post_id;
    String user_id;
    String context;
    String title;
    String date;

    public Board(int post_id, String user_id, String context, String title, String date) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.context = context;
        this.title = title;
        this.date = date;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getUser_id() {
        return user_id;
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

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
        sb.append(post_id);
        sb.append(".\t\t");
        sb.append(user_id);
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
