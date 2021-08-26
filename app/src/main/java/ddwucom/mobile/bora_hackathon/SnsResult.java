package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SnsResult extends AppCompatActivity {
    String sex;
    String which;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sns_result);

        img = findViewById(R.id.m_sns_result);
//        Glide.with(this)
//                .load(R.drawable.m1)
//                .into(img);

        Intent intent = getIntent();
        which = intent.getStringExtra("which");
        sex = intent.getStringExtra("sex");

        switch (which) {
            case "0":
                if (sex.equals("man")) {
                    Glide.with(this)
                            .load(R.drawable.m1)
                            .into(img);
                } else {
                    Glide.with(this)
                            .load(R.drawable.w1)
                            .into(img);
                }
                break;
            case "1":
                if (sex.equals("man")) {
                    Glide.with(this)
                            .load(R.drawable.m2)
                            .into(img);
                } else {
                    Glide.with(this)
                            .load(R.drawable.w2)
                            .into(img);
                }
                break;
            case "2":
                if (sex.equals("man")) {
                    Glide.with(this)
                            .load(R.drawable.m3)
                            .into(img);
                } else {
                    Glide.with(this)
                            .load(R.drawable.w3)
                            .into(img);
                }
               break;
        }
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.m_sns_result:
                finish();
                break;
        }
    }
}
