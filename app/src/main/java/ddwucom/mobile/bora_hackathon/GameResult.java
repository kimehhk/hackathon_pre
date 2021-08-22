package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameResult extends AppCompatActivity {
    TextView m_result;
    TextView w_result;
    String percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result_w);

        Intent intent = getIntent();
        String sex = intent.getStringExtra("sex");

        if (sex.equals("man")) {
            setContentView(R.layout.activity_game_result_m);
        } else {
            setContentView(R.layout.activity_game_result_w);
        }
        percent = intent.getStringExtra("percent");

        m_result = findViewById(R.id.m_result);
        w_result = findViewById(R.id.w_result);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.m_result:
                m_result.setText("당신은 데이트 폭력 위험에\n약 " + percent + "% 노출되어있습니다.");
                break;
            case R.id.w_result:
                w_result.setText("당신은 데이트 폭력 위험에\n약 " + percent + "% 노출되어있습니다.");
                break;
            case R.id.button_game_center:
                Intent intent = new Intent(this, CenterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_game_exit:
                finish();
                break;
        }
    }
}

