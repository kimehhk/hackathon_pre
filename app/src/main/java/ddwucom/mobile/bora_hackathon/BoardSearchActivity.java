package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardSearchActivity extends AppCompatActivity {
    EditText searchBoard;
    String rslt;

    private ListView searchView;
    private ListAdapter adapter;

    private static final String TAG_RESULT = "result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTEXT = "context";
    private static final String TAG_POSTID = "post_id";
    private static final String TAG_DATE = "date";

    JSONArray jsonArray = null;

    ArrayList<HashMap<String, Object>> dataList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 타이틀바 로고 넣기
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchBoard = findViewById(R.id.et_searchBoard);
        searchView = (ListView)findViewById(R.id.searchView);

        Intent gIntent = getIntent();
        String user_id = gIntent.getStringExtra("user_id");

        dataList = new ArrayList<HashMap<String, Object>>();

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BoardSearchActivity.this, DetailActivity.class);
                intent.putExtra("post_id",dataList.get(position).get("post_id").toString());
                intent.putExtra("title", dataList.get(position).get("title").toString());
                intent.putExtra("context", dataList.get(position).get("context").toString());
                intent.putExtra("date", dataList.get(position).get("date").toString());
                intent.putExtra("user_id", user_id);
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

                HashMap<String, Object> data = new HashMap<String, Object>();

                data.put(TAG_POSTID, post_id);
                data.put(TAG_TITLE, title);
                data.put(TAG_CONTEXT, context);
                data.put(TAG_DATE, date);

                dataList.add(data);

            }
            adapter = new SimpleAdapter(
                    BoardSearchActivity.this, dataList, R.layout.custom_adapter_view,
                    new String[]{TAG_TITLE, TAG_CONTEXT, TAG_DATE},
                    new int[]{R.id.boardTitle, R.id.boardContent, R.id.boardDate}
            );

            searchView.setAdapter(adapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class getData extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String searchKeyword = params[0];
                String serverURL = "http://boragame.dothome.co.kr/search.php";
                String postParameters = "context=" + searchKeyword;

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(serverURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setReadTimeout(5000);
                    con.setConnectTimeout(5000);
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.connect();

                    OutputStream outputStream = con.getOutputStream();
                    outputStream.write(postParameters.getBytes("UTF-8"));
                    outputStream.flush();
                    outputStream.close();

                    int responseStatusCode = con.getResponseCode();
                    InputStream inputStream;
                    if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                        inputStream = con.getInputStream();
                    }
                    else {
                        inputStream = con.getErrorStream();
                    }
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_search:
                dataList.clear();

                getData task = new getData();
                task.execute(searchBoard.getText().toString());
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }
}
