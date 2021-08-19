package ddwucom.mobile.bora_hackathon;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    EditText etName_first;
    EditText etName_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_manual);

        etName_first = findViewById(R.id.et_game_user_firstname);
        etName_last = findViewById(R.id.et_game_user_lastname);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gameMaunual_ok:
                setContentView(R.layout.activity_game_username);
                break;
            case R.id.btn_gameName_ok:
                etName_first = findViewById(R.id.et_game_user_firstname);
                etName_last = findViewById(R.id.et_game_user_lastname);
                if(etName_first == null)
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                //setContentView(R.layout.activity_game_ment);
                break;
            case R.id.button_game_exit:
                finish();
                break;
        }
    }
}