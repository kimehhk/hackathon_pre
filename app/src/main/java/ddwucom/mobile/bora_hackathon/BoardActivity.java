package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    final int READ_CODE = 100;
    final int SEARCH_CODE = 200;

    String data;

    private MyAdapter_board myAdapter;
    private ListView listView;
    private ArrayList<Board> boardList;

    private static final String TAG_RESULT = "result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTEXT = "context";

    JSONArray board = null;
    ArrayList<HashMap<String, String>> dataList;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //myAdapter = new MyAdapter_board(this, R.layout.custom_adapter_view, boardList);
        listView = (ListView)findViewById(R.id.customListView);
        //listView.setAdapter(myAdapter);
        boardList = new ArrayList<>();
        dataList = new ArrayList<HashMap<String, String>>();
        getData("http://boragame.dothome.co.kr/read.php");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Board board = boardList.get(position);
                Intent intent = new Intent(BoardActivity.this, DetailActivity.class);
                intent.putExtra("board", board);
                startActivityForResult(intent, READ_CODE);
            }
        });

    }

    protected void showList() {
        try {
            JSONObject obj = new JSONObject(data);
            board = obj.getJSONArray(TAG_RESULT);

            for (int i = 0; i < board.length(); i++) {
                JSONObject c = board.getJSONObject(i);
                String title = c.getString(TAG_TITLE);
                String context = c.getString(TAG_CONTEXT);

                HashMap<String, String> data = new HashMap<String, String>();

                data.put(TAG_TITLE, title);
                data.put(TAG_CONTEXT, context);

                dataList.add(data);
            }
            adapter = new SimpleAdapter(
                    BoardActivity.this, dataList, R.layout.custom_adapter_view,
                    new String[]{TAG_TITLE, TAG_CONTEXT},
                    new int[]{R.id.boardTitle, R.id.boardContext}
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
                data = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

//    protected void onResume() {
//        super.onResume();
////        boardList.clear();
//////        boardList.addAll(boardDBManager.getAllBoard());
////        myAdapter.notifyDataSetChanged();
//        dataList.clear();
//        adapter.notify();
//    }

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
                intent = new Intent(BoardActivity.this, BoardAddActivity.class);
                startActivity(intent);

        }
    }
}
