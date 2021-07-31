package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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
                String post_id = String.valueOf(1);
                // EditText에 현재 입력되어있는 값을 가져옴.
                String title = et_boardTitle.getText().toString();
                String context = et_boardContext.getText().toString();

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                String date = year + "-" + month + "-" + day;

                Intent intent = getIntent();
                String user_id = intent.getStringExtra("user_id");

                Toast.makeText(BoardAddActivity.this, post_id + " " + title + " " + context + " " + date + " " + user_id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}