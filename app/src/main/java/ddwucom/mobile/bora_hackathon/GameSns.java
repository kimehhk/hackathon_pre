package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameSns extends AppCompatActivity {
    int next;
    int percent;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sns_m);
        next = 0;

        ImageView image_m = findViewById(R.id.sns_image_m);

        Intent intent = getIntent();
        sex = intent.getStringExtra("sex");

        switch (sex) {
            case "man" :
                Glide.with(this)
                        .load(R.drawable.woman_sns)
                        .into(image_m);
                break;
            case "woman" :
                setContentView(R.layout.activity_game_sns_w);
                ImageView image_w = findViewById(R.id.sns_image_w);
                Glide.with(this)
                    .load(R.drawable.man_sns)
                    .into(image_w);

                break;
        }
    }

    public void onClick(View v) {
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        TextView text3 = findViewById(R.id.text3);
        TextView text4 = findViewById(R.id.text4);
        TextView text5 = findViewById(R.id.text5);
        TextView text6 = findViewById(R.id.text6);
        if (v.getId() == R.id.sns_image_m || v.getId() == R.id.sns_image_w) {
            next++;
            switch (next) {
                case 1:
                    text1.setVisibility(v.INVISIBLE);
                    break;
                case 2:
                    text2.setVisibility(v.INVISIBLE);
                    break;
                case 3:
                    text3.setVisibility(v.INVISIBLE);
                    break;
                case 4:
                    text4.setVisibility(v.INVISIBLE);
                    break;
                case 5:
                    text5.setVisibility(v.INVISIBLE);
                    break;
                case 6:
                    text6.setVisibility(v.INVISIBLE);
                    break;
                default:
                    finish();
                    break;
            }
        }
    }
}
