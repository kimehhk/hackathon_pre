package ddwucom.mobile.bora_hackathon;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommentRequest extends StringRequest {

    final static private String URL = "http://boragame.co.kr/comment_add.php";
    private Map<String, String> map;

    public CommentRequest(String comment_id, String content, String user_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("post_id", comment_id);
        map.put("title", content);
        map.put("user_id", user_id);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
