package ddwucom.mobile.bora_hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    //Board board;
    TextView date;
    TextView title;
    TextView context;
    //MyAdapter_comment myAdapter_comment;
    ListView listView;
    ArrayList commentList;
    List<Comment> comments;
    ArrayAdapter myAdapter;

    final static private String READ_URL = "http://boragame.dothome.co.kr/comment_read.php";
    final static private String DEL_URL = "http://boragame.dothome.co.kr/comment_del.php";

    //ArrayList<HashMap<String, Object>> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent board = getIntent();
        board.getStringExtra("post_id");


        title = findViewById(R.id.tvTitleDetail);
        date = findViewById(R.id.tvDateDetail);
        context = findViewById(R.id.tvContextDetail);
        listView = findViewById(R.id.listView);

/*
<<<<<<< HEAD
=======
        date = findViewById(R.id.et_boardDate);
        title = findViewById(R.id.et_boardTitle1);
        context = findViewById(R.id.et_boardContext1);
        listView = findViewById(R.id.searchView);
>>>>>>> c2c223a6bfa425a3cc2f363daf161b04190fa35c*/

        //date.setText(board.getDate());
        //title.setText(board.getTitle());
        //context.setText(board.getContext());


        //commentList = new ArrayList();
        //myAdapter_comment = new MyAdapter_comment(this, R.layout.custom_adapter_view_comment, commentList);
        //listView.setAdapter(myAdapter_comment);

        commentList = new ArrayList();

        getComments();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                String message = "댓글을 삭제하겠습니까?";
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("삭제 확인")
                        .setMessage(message)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    public void getComments() {
        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, READ_URL,
                new Response.Listener<String>() {
        //JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, READ_URL, null,
                //new Response.Listener<JSONArray>() {
                    @Override
                    //public void onResponse(JSONArray response) {
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = (JSONObject) array.get(i);
                                //JSONObject object = response.getJSONObject(i);

                                //String comment_id = object.getJSONArray("result").getString(0);
                                String comment_id = object.getString("comment_id");
                                String content = object.getString("content");
                                String post_id = object.getString("post_id");

                                HashMap<String, String> data = new HashMap<String, String>();

                                data.put("comment_id", comment_id);
                                data.put("content", content);
                                data.put("post_id", post_id);

                                commentList.add(content);

                                //Comment comment = new Comment(comment_id, content, post_id);
                                //comments.add(comment);
                            }
                            myAdapter = new ArrayAdapter(
                                    DetailActivity.this, android.R.layout.simple_list_item_1,
                                    commentList);

                            listView.setAdapter(myAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error is " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    //myAdapter_comment = new MyAdapter_comment(DetailActivity.this, R.layout.custom_adapter_view_comment, comments);
                    //listView.setAdapter(myAdapter_comment);
                   /* myAdapter_comment = new MyAdapter_comment(DetailActivity.this, comments);
                    listView.setAdapter(myAdapter_comment);*/
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(stringRequest);
        //queue.add(jsonArrayRequest);
    }


}