
package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView login;
    private TextView register;
    private String id;
    private String name;

    SoundManager soundManager;
    SoundPool soundPool;
    boolean play;
    int playSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundManager = new SoundManager(this, soundPool);

        soundManager.addSound(0, R.raw.click1);

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
        name = intent.getStringExtra("name");
        if (name != null) {
            login.setText(name + "님");
            register.setText("");
        }
    }


    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
        name = intent.getStringExtra("name");
        if (name != null) {
            login.setText(name + "님");
            register.setText("");
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_game:
                if (!play) {
                    playSoundId = soundManager.playSound(0);
                    play = true;
                } else {
                    soundManager.resumeSound(playSoundId);
                    soundManager.playSound(0);
                    play = false;
                }
                Intent intentG = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intentG);
                break;
            case R.id.button_board:
                Intent intent = new Intent(this, BoardActivity.class);
                if (id != null)
                    intent.putExtra("user_id", id);
                startActivity(intent);
                break;
            case R.id.login:
                if (id == null) {
                    Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.register:
                Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }
}