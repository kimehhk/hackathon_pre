package ddwucom.mobile.bora_hackathon;


//import com.android.volley.toolbox.StringRequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class BoardRequest extends StringRequest {

    final static private String URL = "http://boragame.co.kr/read.php";
    private Map<String, String> map;

    public BoardRequest(String post_id, String title, String context, String date, String user_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

    }

}
