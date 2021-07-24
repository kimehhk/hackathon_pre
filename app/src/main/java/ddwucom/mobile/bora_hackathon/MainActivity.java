package ddwucom.mobile.bora_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass, tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);
        tv_name = findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        String password = intent.getStringExtra("password");
        String name = intent.getStringExtra("name");

        tv_id.setText(user_id);
        tv_pass.setText(password);
        tv_name.setText(name + "님 반갑습니다!");
    }
}