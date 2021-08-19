
package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView login;
    private TextView register;
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

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