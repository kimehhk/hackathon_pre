package ddwucom.mobile.bora_hackathon;

public class Comment {
    String comment_id;
    String post_id;
    String comment;

    public Comment(String comment_id, String comment, String post_id) {
        this.comment_id = comment_id;
        this.comment = comment;
        this.post_id = post_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public void setPost_id(String post_id) {
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