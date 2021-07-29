package ddwucom.mobile.bora_hackathon;

public class Comment {
    int comment_id;
    int post_id;
    String comment;

    public Comment(int comment_id, int post_id, String comment) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.comment = comment;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(comment_id);
        sb.append(".\t\t");
        sb.append(post_id);
        sb.append(".\t\t");
        sb.append(comment);
        sb.append(")");
        return sb.toString();
    }
}