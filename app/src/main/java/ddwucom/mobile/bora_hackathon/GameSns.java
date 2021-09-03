package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameSns extends AppCompatActivity {
    ImageView image;

    int next;
    int percent;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        sex = intent.getStringExtra("sex");

        if (sex.equals("man"))
            setContentView(R.layout.activity_game_sns);
        else
            setContentView(R.layout.activity_game_sns_m);


        /*ActionBar actionbar = getSupportActionBar();
        actionbar.hide();*/

        next = 0;

        image = findViewById(R.id.sns_image);

        switch (sex) {
            case "man" :
                Glide.with(this)
                        .load(R.drawable.m_sns1)
                        .placeholder(R.drawable.load)
                        .into(image);
                break;
            case "woman" :
                Glide.with(this)
                    .load(R.drawable.w_sns1)
                        .placeholder(R.drawable.load)
                    .into(image);
                break;
        }

    }

    public void onClick(View v) {
        if (v.getId() == R.id.sns_image) {
            next++;
            switch (next) {
                case 1:
                    if (sex.equals("man")) {
                        Glide.with(this)
                                .load(R.drawable.m_sns2)
                                .placeholder(R.drawable.load)
                                .into(image);
                    } else {
                        Glide.with(this)
                                .load(R.drawable.w_sns2)
                                .placeholder(R.drawable.load)
                                .into(image);
                    }
                    break;
                case 2:
                    if (sex.equals("man")) {
                        Glide.with(this)
                                .load(R.drawable.m_sns3)
                                .placeholder(R.drawable.load)
                                .into(image);
                    } else {
                        Glide.with(this)
                                .load(R.drawable.w_sns3)
                                .placeholder(R.drawable.load)
                                .into(image);
                    }
                    break;
                case 3:
                    if (sex.equals("man")) {
                        Glide.with(this)
                                .load(R.drawable.m_sns4)
                                .placeholder(R.drawable.load)
                                .into(image);
                    } else {
                        Glide.with(this)
                                .load(R.drawable.w_sns4)
                                .placeholder(R.drawable.load)
                                .into(image);
                    }
                    break;
                case 4:
                    if (sex.equals("man")) {
                        Glide.with(this)
                                .load(R.drawable.m_sns5)
                                .placeholder(R.drawable.load)
                                .into(image);
                    } else {
                        Glide.with(this)
                                .load(R.drawable.w_sns5)
                                .placeholder(R.drawable.load)
                                .into(image);
                    }
                    break;
                case 5:
                    if (sex.equals("man")) {
                        Glide.with(this)
                                .load(R.drawable.m_sns6)
                                .placeholder(R.drawable.load)
                                .into(image);
                    } else {
                        Glide.with(this)
                                .load(R.drawable.w_sns6)
                                .placeholder(R.drawable.load)
                                .into(image);
                    }
                    break;
                default:
                    finish();
                    break;
            }
        }
    }
}
