package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameActivity extends AppCompatActivity {

    EditText etName_first;
    EditText etName_last;
    RadioButton btn_m;
    RadioButton btn_w;
    String first_name;
    String last_name;
    ImageView img;

    SoundManager soundManager;
    SoundPool soundPool;
    boolean play;
    int playSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_username);

        // 타이틀바 로고 넣기
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*ActionBar actionbar = getSupportActionBar();
        actionbar.hide();*/

        img = findViewById(R.id.game_nameBg);
        Glide.with(this)
                .load(R.drawable.gamebackground)
                .placeholder(R.drawable.loading)
                .into(img);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundManager = new SoundManager(this, soundPool);
        soundManager.addSound(0, R.raw.click1);
    }

    public void user_setting() {
        etName_first = findViewById(R.id.et_game_user_firstname);
        etName_last = findViewById(R.id.et_game_user_lastname);

        first_name = etName_first.getText().toString();
        last_name = etName_last.getText().toString();

        btn_m = findViewById(R.id.game_user_man);
        btn_w = findViewById(R.id.game_user_woman);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gameName_ok:
                if (!play) {
                    playSoundId = soundManager.playSound(0);
                    play = true;
                } else {
                    soundManager.resumeSound(playSoundId);
                    soundManager.playSound(0);
                    play = false;
                }

                user_setting();

                if (!btn_m.isChecked() && !btn_w.isChecked()) {
                    Toast.makeText(GameActivity.this, "성별을 선택하세요", Toast.LENGTH_SHORT).show();
                    break;
                } else if (last_name.length() == 0) {
                    Toast.makeText(GameActivity.this, "성을 입력하세요", Toast.LENGTH_SHORT).show();
                    break;
                } else if (first_name.length() == 0) {
                    Toast.makeText(GameActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    break;
                }

                Intent intent = new Intent(this, GameBoard.class);

                intent.putExtra("firstName", first_name);
                intent.putExtra("lastName", last_name);
                if (btn_m.isChecked()) {
                    intent.putExtra("sex", "man");
                } else if (btn_w.isChecked()) {
                    intent.putExtra("sex", "woman");
                }
                startActivity(intent);
                break;
        }
    }

    public void onStop() {
        super.onStop();
        finish();
    }

}