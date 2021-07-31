package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BoardAddActivity extends AppCompatActivity {
    EditText et_boardTitle;
    EditText et_boardContext;
    Button button_ok;

    final static private String UPLOAD_URL = "http://boragame.dothome.co.kr/upload.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_boardTitle = findViewById(R.id.et_boardTitle);
        et_boardContext = findViewById(R.id.et_boardContext);
        button_ok = findViewById(R.id.button_ok);

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // EditText에 현재 입력되어있는 값을 가져옴.
                String title = et_boardTitle.getText().toString();
                String context = et_boardContext.getText().toString();

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);

                int month = c.get(Calendar.MONTH) + 1;

                int day = c.get(Calendar.DAY_OF_MONTH);

                String date = year + "-" + month + "-" + day;

                Intent intent = getIntent();
                String user_id = intent.getStringExtra("user_id");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //회원등록에 성공한 경우
                                Toast.makeText(BoardAddActivity.this, "글 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(BoardAddActivity.this, BoardActivity.class);
//                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(BoardAddActivity.this, "글 등록에에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                BoardRequest boardRequest = new BoardRequest(title, context, date, user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(BoardAddActivity.this);
                queue.add(boardRequest);
                finish();
            }
        });
    }
}