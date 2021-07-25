package ddwucom.mobile.bora_hackathon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardActivity extends AppCompatActivity {

    String data;

    private ListView customListview;

    private static final String TAG_RESULT = "result";
    private static final String TAG_POSTID = "post_id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTEXT = "context";
    private static final String TAG_DATE = "date";
    private static final String TAG_USERID = "user_id";

    JSONArray board = null;
    ArrayList<HashMap<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customListview = findViewById(R.id.customListView);
        dataList = new ArrayList<HashMap<String,String>>();
        getData("http://boragame.dothome.co.kr/read.php");
    }
    protected void showList() {
        try {
            JSONObject obj = new JSONObject(data);
            board = obj.getJSONArray(TAG_RESULT);

            for (int i = 0; i < board.length(); i++) {
                JSONObject c = board.getJSONObject(i);
                String post_id = c.getString(TAG_POSTID);
                String title = c.getString(TAG_TITLE);
                String context = c.getString(TAG_CONTEXT);
                String date = c.getString(TAG_DATE);
                String user_id = c.getString(TAG_USERID);

                HashMap<String, String> data = new HashMap<String, String>();

                data.put(TAG_POSTID, post_id);
                data.put(TAG_TITLE, title);
                data.put(TAG_CONTEXT, context);
                data.put(TAG_DATE, date);
                data.put(TAG_USERID, user_id);

                dataList.add(data);
            }
            ListAdapter adapter = new SimpleAdapter(
                    BoardActivity.this, dataList, R.layout.custom_adapter_view,
                    new String[]{TAG_TITLE, TAG_CONTEXT},
                    new int[]{R.id.et_title, R.id.et_context}
            );

            customListview.setAdapter(adapter);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                data = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                break;
            case R.id.btn_center:
                break;
        }
    }
}