package ddwucom.mobile.bora_hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
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
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    //Board board;
    TextView date;
    TextView title;
    TextView context;
    EditText comment;
    //MyAdapter_comment myAdapter_comment;
    ListView listView;
    ArrayList commentList = null;
    List<Comment> comments;
    ArrayAdapter myAdapter;
    String board_post_id;
    String board_title;
    String board_context;
    String board_date;

    final static private String READ_URL = "http://boragame.dothome.co.kr/comment_read.php";
    final static private String DEL_URL = "http://boragame.dothome.co.kr/comment_del.php";
    final static private String ADD_URL = "http://boragame.dothome.co.kr/comment_add.php";

    //ArrayList<HashMap<String, Object>> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent board = getIntent();
        board_post_id = board.getStringExtra("post_id");
        board_title = board.getStringExtra("title");
        board_date = board.getStringExtra("date");
        board_context = board.getStringExtra("context");

        title = findViewById(R.id.tvTitleDetail);
        date = findViewById(R.id.tvDateDetail);
        context = findViewById(R.id.tvContextDetail);
        comment = findViewById(R.id.et_commentAdd);
        listView = findViewById(R.id.listView);

        date.setText(board_date);
        title.setText(board_title);
        context.setText(board_context);

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
                                deleteComment("1");
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commentAdd:
                String str = comment.getText().toString().trim();
                addComment(board_post_id, str);
                comment.setText(null);
                break;
        }
    }

    private void addComment(String board_post_id, String str) {
        StringRequest request = new StringRequest(Request.Method.POST, ADD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("Data Inserted")) {
                            Toast.makeText(DetailActivity.this, "댓글 입력 성공", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailActivity.this, "댓글 입력 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //params.put("comment_id", "8");
                params.put("content", str);
                params.put("post_id", board_post_id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void deleteComment(String id) {
        StringRequest request = new StringRequest(Request.Method.POST, DEL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("Data Deleted")) {
                            Toast.makeText(DetailActivity.this, "댓글 삭제 성공", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailActivity.this, "댓글 삭제 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("comment_id", id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
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

                                if(post_id.equals(board_post_id))
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