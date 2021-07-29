package ddwucom.mobile.bora_hackathon;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BoardAddActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.et_boardTitle);
        etContext = findViewById(R.id.et_boardContext);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }
}
