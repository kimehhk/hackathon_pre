package ddwucom.mobile.bora_hackathon;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    EditText etName_first;
    EditText etName_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_username);

        etName_first = findViewById(R.id.et_game_user_firstname);
        etName_last = findViewById(R.id.et_game_user_lastname);



    }
}