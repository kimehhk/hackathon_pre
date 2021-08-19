package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    EditText etName_first;
    EditText etName_last;
    RadioButton btn_m;
    RadioButton btn_w;
    String first_name;
    String last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_manual);
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
            case R.id.btn_gameMaunual_ok:
                setContentView(R.layout.activity_game_username);
                break;
            case R.id.btn_gameName_ok:
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
}