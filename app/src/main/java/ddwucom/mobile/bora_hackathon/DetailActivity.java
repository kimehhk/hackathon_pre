package ddwucom.mobile.bora_hackathon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    //Board board;
    int board;
    EditText date;
    EditText title;
    EditText context;
    MyAdapter_comment myAdapter_comment;
    ListView listView;
    //ArrayList commentList;
    List<Comment> comments;
    String content;

    final static private String READ_URL = "http://boragame.co.kr/comment_read.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //board = (int) getIntent().getSerializableExtra("board");
        board = 1;

       /* Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        DetailRequest detailRequest = new DetailRequest(board, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DetailActivity.this);
        queue.add(detailRequest);*/



        date = findViewById(R.id.et_boardDate);
        title = findViewById(R.id.et_boardTitle1);
        context = findViewById(R.id.et_boardContext1);
        listView = findViewById(R.id.customListViewC);

        //date.setText(board.getDate());
        //title.setText(board.getTitle());
        //context.setText(board.getContext());


        //commentList = new ArrayList();
        //myAdapter_comment = new MyAdapter_comment(this, R.layout.custom_adapter_view_comment, commentList);
        //listView.setAdapter(myAdapter_comment);

        getComments();
        context.setHint(content);

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
        //content = "hh";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, READ_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //String content = null;
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                int comment_id = Integer.parseInt(object.getString("comment_id"));
                                content = object.getString("content");
                                int post_id = Integer.parseInt(object.getString("post_id"));

                                Comment comment = new Comment(comment_id, content, post_id);
                                comments.add(comment);
                            }
                        } catch (Exception e) {

                        }

                        myAdapter_comment = new MyAdapter_comment(DetailActivity.this, R.layout.custom_adapter_view_comment, comments);
                        listView.setAdapter(myAdapter_comment);
                       /* myAdapter_comment = new MyAdapter_comment(DetailActivity.this, comments);
                        listView.setAdapter(myAdapter_comment);*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(DetailActivity.this).add(stringRequest);
    }
}