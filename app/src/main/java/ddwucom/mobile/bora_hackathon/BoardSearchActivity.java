package ddwucom.mobile.bora_hackathon;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BoardSearchActivity extends AppCompatActivity {
    EditText searchBoard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBoard = findViewById(R.id.et_searchBoard);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_search:
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }
}
