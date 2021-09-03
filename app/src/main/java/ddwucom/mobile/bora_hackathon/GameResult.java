package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameResult extends AppCompatActivity {
    TextView m_result;
    String percent;
    ImageView img_result;
//    ImageButton center_btn;
    TextView et_warnig;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        /*ActionBar actionbar = getSupportActionBar();
        actionbar.hide();*/

        img_result = findViewById(R.id.img_game_result_w);
//        center_btn = findViewById(R.id.btn_game_center);

//        Glide.with(this)
//                .load(R.drawable.centerbutton)
                //.override(600, 200)
//                .into(center_btn);

        Intent intent = getIntent();
        String sex = intent.getStringExtra("sex");

        switch (sex) {
            case "man":
                Glide.with(this)
                        .load(R.drawable.gameresult_w)
                        .into(img_result);
                break;
            case "woman":
                Glide.with(this)
                        .load(R.drawable.gameresult_m)
                        .into(img_result);
                break;
        }
        percent = intent.getStringExtra("percent");

        m_result = findViewById(R.id.m_result);
        et_warnig = findViewById(R.id.et_game_warning);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.m_result:
                int per = Integer.parseInt(percent);
                if (per < 25)
                    color = Color.GREEN;
                else if (per < 75)
                    color = Color.YELLOW;
                else
                    color = Color.RED;
                m_result.setText("당신은 데이트 폭력 위험에\n약 " + percent + "% 노출되어있습니다.");
                m_result.setTextColor(color);
                et_warnig.setText("가스라이팅, 단호하게 끊어내야 합니다");
                et_warnig.setTextColor(Color.WHITE);
                break;
            case R.id.btn_game_center:
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

