package ddwucom.mobile.bora_hackathon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

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

public class BoardActivity extends AppCompatActivity {
    final int READ_CODE = 100;
    final int SEARCH_CODE = 200;

    String rslt;
    int postId;

    private MyAdapter_board myAdapter;
    private ListView listView;
    private ArrayList<Board> boardList;

    private static final String TAG_RESULT = "result";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTEXT = "context";
    private static final String TAG_POSTID = "post_id";
    private static final String TAG_DATE = "date";

    JSONArray board = null;
    //ArrayList<HashMap<String, String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        listView = (ListView)findViewById(R.id.customListView);
//        myAdapter = new MyAdapter_board(BoardActivity.this, R.layout.custom_adapter_view, boardList);
//        listView.setAdapter(myAdapter);

        boardList = new ArrayList<Board>();
        //dataList = new ArrayList<HashMap<String, String>>();
        getData d = new getData();
        d.execute("http://boragame.dothome.co.kr/read.php","");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BoardActivity.this, DetailActivity.class);
                //intent.putExtra("post_id", postId);
                startActivityForResult(intent, READ_CODE);
            }
        });

    }

    protected void showList() {
        try {
            JSONObject obj = new JSONObject(rslt);
            board = obj.getJSONArray(TAG_RESULT);

            for (int i = 0; i < board.length(); i++) {
                JSONObject c = board.getJSONObject(i);
                String title = c.getString(TAG_TITLE);
                String context = c.getString(TAG_CONTEXT);
                //postId = c.getInt(TAG_POSTID);

                //HashMap<String, String> data = new HashMap<String, String>();
                Board data = new Board(title, context);
                boardList.add(data);
                //myAdapter.notifyDataSetChanged();

            }
            myAdapter = new MyAdapter_board(BoardActivity.this, R.layout.custom_adapter_view, boardList);
            listView.setAdapter(myAdapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class getData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected  void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(BoardActivity.this, "Please wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            rslt = result;
            showList();
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = "title=" + params[1];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {
                //Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }

    }

//    protected void onResume() {
//        super.onResume();
//        boardList.clear();
////        boardList.addAll(boardDBManager.getAllBoard());
//        myAdapter.notifyDataSetChanged();
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
