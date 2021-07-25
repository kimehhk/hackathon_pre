package ddwucom.mobile.bora_hackathon;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BoardRequest extends StringRequest {

    final static private String URL = "http://boragame.co.kr/read.php";
    private Map<String, String> map;

    public BoardRequest(String post_id, String title, String context, String date, String user_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("post_id", post_id);
        map.put("title", title);
        map.put("context", context);
        map.put("date", date);
        map.put("user_id", user_id);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}