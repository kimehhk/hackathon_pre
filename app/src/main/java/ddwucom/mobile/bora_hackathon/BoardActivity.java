package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class BoardActivity extends AppCompatActivity {
    final int ADD_CODE = 100;
    final int SEARCH_CODE = 200;
    final int CENTER_CODE = 300;

    String rslt;

    private ListView listView;
    private SimpleAdapter adapter;
    private HashMap<String, Object> data;

    private static final String TAG_RESULT = "result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTEXT = "context";
    private static final String TAG_POSTID = "post_id";
    private static final String TAG_DATE = "date";

    JSONArray jsonArray = null;

    ArrayList<HashMap<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        listView = (ListView)findViewById(R.id.list);
        ScrollView scrollView = findViewById(R.id.boardScrollView);

        dataList = new ArrayList<HashMap<String, Object>>();

//        getData("http://boragame.dothome.co.kr/board.php");

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BoardActivity.this, DetailActivity.class);
                intent.putExtra("post_id",dataList.get(position).get("post_id").toString());
                intent.putExtra("title", dataList.get(position).get("title").toString());
                intent.putExtra("context", dataList.get(position).get("context").toString());
                intent.putExtra("date", dataList.get(position).get("date").toString());
                startActivity(intent);
            }
        });

    }

    protected void showList() {
        try {
            JSONObject obj = new JSONObject(rslt);
            jsonArray = obj.getJSONArray(TAG_RESULT);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);

                String post_id = c.getString(TAG_POSTID);
                String title = c.getString(TAG_TITLE);
                String context = c.getString(TAG_CONTEXT);
                String date = c.getString(TAG_DATE);

                data = new HashMap<>();

                data.put(TAG_POSTID, post_id);
                data.put(TAG_TITLE, title);
                data.put(TAG_CONTEXT, context);
                data.put(TAG_DATE, date);

                dataList.add(data);

            }
            adapter = new SimpleAdapter(
                    BoardActivity.this, dataList, R.layout.custom_adapter_view,
                    new String[]{TAG_TITLE, TAG_CONTEXT, TAG_DATE},
                    new int[]{R.id.boardTitle, R.id.boardContent, R.id.boardDate}
            );

            listView.setAdapter(adapter);

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
                rslt = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    protected void onResume() {
        super.onResume();
        dataList.clear();
        getData("http://boragame.dothome.co.kr/board.php");

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_search:
                Intent intent = new Intent(BoardActivity.this, BoardSearchActivity.class);
                startActivityForResult(intent, SEARCH_CODE);
                break;
            case R.id.button_cancel:
                finish();
                break;
            case R.id.btn_upload:
                Intent gIntent = getIntent();
                String user_id = gIntent.getStringExtra("user_id");

                intent = new Intent(BoardActivity.this, BoardAddActivity.class);
                intent.putExtra("user_id", user_id);
                //startActivityForResult(intent, ADD_CODE);
                startActivity(intent);
//            case R.id.btn_center:
//                intent = new Intent(BoardActivity.this, CenterActivity.class);
//                //startActivityForResult(intent, CENTER_CODE);
//                startActivity(intent);
        }
    }
}
