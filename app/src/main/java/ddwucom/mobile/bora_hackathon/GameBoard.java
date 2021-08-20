package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameBoard extends AppCompatActivity {
    TextView ment;
    TextView name;
    ArrayList<String> Mdata;
    int i;
    String sex;
    String first_name;
    String last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        i = 0;

        Intent intent = getIntent();
        sex = intent.getStringExtra("sex");
        first_name = intent.getStringExtra("firstName");
        last_name = intent.getStringExtra("lastName");

        ment = findViewById(R.id.et_game_ment);
        name = findViewById(R.id.ch_name);

        data_insert();
        name.setText(Mdata.get(i++));
        ment.setText(Mdata.get(i++));
    }

    public void data_insert() {
        String girl_name = "여주";
        String boy_name = "남주";
        //String full_name = last_name + first_name;
        String full_name = "나";

        Mdata = new ArrayList<String>();
        if (sex.equals("man")) {
            Mdata.add(girl_name);
            Mdata.add(first_name + "야(아) 왜 이렇게 연락이 잘 안돼? 연락 좀 잘 해주면 좋겠어.");
            Mdata.add(full_name);
            Mdata.add("미안해 수업 중이었어.");
            Mdata.add(girl_name);
            Mdata.add("그럼 수업 끝나고 바로 나한테 카톡 한 번 해줄 수 있잖아. 사귀는 사이에 연락은 기본 예의지. 배려가 너무 없는 거 아니야?");
        } else {
            Mdata.add(full_name);
            Mdata.add("저 여자분 타투 너무 멋있어. 나도 나중에 타투하고 싶어.");
            Mdata.add(boy_name);
            Mdata.add("갑자기 타투?" + first_name + "(이) 타투하고 싶어? 후회하지 않을까?");
            Mdata.add(full_name);
            Mdata.add("에이 내가 내 몸에 원하는 거 남기는데 그게 무슨 상관이야~");
            Mdata.add(boy_name);
            Mdata.add("난 지금 타투 없는 " + first_name + "(이)가 더 좋은데...\n타투 없는게 훨씬 예뻐 " + first_name + "야(아)! 다시 한 번 생각해봐ㅎㅎ");
        }
    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn_game_next:
                if (i == Mdata.size()) {
                    switch (sex) {
                        case "man":
                            setContentView(R.layout.activity_game_result_m);
                            break;
                        case "woman":
                            setContentView(R.layout.activity_game_result_w);
                            break;
                    }
                    /*if (sex.equals("man"))
                        setContentView(R.layout.activity_game_result);
                    else
                        setContentView(R.layout.activity_game_result_w);*/
                    break;
                }
                name.setText(Mdata.get(i++));
                ment.setText(Mdata.get(i++));
                break;
            case R.id.button_game_center:
                Intent intent = new Intent(this, CenterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_game_exit:
                finish();
                break;
        }
    }

    public void onStop() {
        super.onStop();
        finish();
    }
}