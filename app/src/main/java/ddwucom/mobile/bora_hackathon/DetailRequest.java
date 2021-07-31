package ddwucom.mobile.bora_hackathon;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DetailRequest extends StringRequest {

    final static private String URL = "http://boragame.dothome.co.kr/detail.php";
    private Map<String, String> map;

    public DetailRequest(int post_id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("post_id", String.valueOf(post_id));
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
