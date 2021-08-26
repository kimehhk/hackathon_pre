package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameResult extends AppCompatActivity {
    TextView m_result;
    String percent;
    ImageView img_result;
    ImageView img_resultM;
    ImageButton center_btn;
    ImageButton exit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        img_result = findViewById(R.id.img_game_result_w);
        center_btn = findViewById(R.id.button_game_center);
        exit_btn = findViewById(R.id.button_game_exit);

        Glide.with(this)
                .load(R.drawable.centerbutton)
                //.override(600, 200)
                .into(center_btn);
        Glide.with(this)
                .load(R.drawable.main_exitbutton)
                .into(exit_btn);

        Intent intent = getIntent();
        String sex = intent.getStringExtra("sex");

        switch (sex) {
            case "man":
                //setContentView(R.layout.activity_game_result_m);
                Glide.with(this)
                        .load(R.drawable.gameresult_w)
                        .into(img_result);
                break;
            case "woman":
                //setContentView(R.layout.activity_game_result_w);
                Glide.with(this)
                        .load(R.drawable.gameresult_m)
                        .into(img_result);
                break;
        }
        percent = intent.getStringExtra("percent");

        m_result = findViewById(R.id.m_result);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.m_result:
                m_result.setText("당신은 데이트 폭력 위험에\n약 " + percent + "% 노출되어있습니다.");
                break;
            case R.id.button_game_center:
                intent = new Intent(this, CenterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_game_exit:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}

