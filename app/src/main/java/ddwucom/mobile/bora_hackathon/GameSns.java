package ddwucom.mobile.bora_hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class GameSns extends AppCompatActivity {
    int next;
    int percent;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_sns);
        next = 0;

        ImageView image = findViewById(R.id.sns_image);
        ImageView sns_result = findViewById(R.id.m_sns_result);

        Glide.with(this)
                .load(R.drawable.sns_m)
                .into(image);
//        Glide.with(this)
//                .load(R.drawable.sns_m)
//                .into(sns_result);
//
//        Intent intent = getIntent();
//        sex = intent.getStringExtra("sex");

//        switch (sex) {
//            case "man" :
//                Glide.with(this)
//                        .load(R.drawable.sns_m)
//                        .into(image);
//                break;
//            case "woman" :
//                Glide.with(this)
//                        .load(R.drawable.sns_w)
//                        .into(image);
//                break;
//        }
    }

    public void onClick(View v) {
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        TextView text3 = findViewById(R.id.text3);
        TextView text4 = findViewById(R.id.text4);
        TextView text5 = findViewById(R.id.text5);
        TextView text6 = findViewById(R.id.text6);
        switch (v.getId()) {
            case R.id.sns_image:
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
                break;

        }
    }
}
