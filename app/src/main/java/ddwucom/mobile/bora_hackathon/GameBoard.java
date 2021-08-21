package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class GameBoard extends AppCompatActivity {
    TextView ment;
    TextView name;
    ArrayList<String> Mdata;
    int i;
    String sex;
    String first_name;
    String last_name;

    ArrayList<String> w_choice;
    ArrayList<String> m_choice;

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
            Mdata.add(full_name);
            Mdata.add("choice");
        } else {
            Mdata.add(full_name);
            Mdata.add("저 여자분 타투 너무 멋있어. 나도 나중에 타투하고 싶어.");
            Mdata.add(boy_name);
            Mdata.add("갑자기 타투? " + first_name + "(이) 타투하고 싶어? 후회하지 않을까?");
            Mdata.add(full_name);
            Mdata.add("에이 내가 내 몸에 원하는 거 남기는데 그게 무슨 상관이야~");
            Mdata.add(boy_name);
            Mdata.add("난 지금 타투 없는 " + first_name + "(이)가 더 좋은데...\n타투 없는게 훨씬 예뻐 " + first_name + "야(아)!\n다시 한 번 생각해봐ㅎㅎ");
            Mdata.add(full_name);
            Mdata.add("choice");
        }

        m_choice = new ArrayList<String>();
        m_choice.add("정말 미안해 앞으로 5분에 한번씩 카톡 확인할게.");
        m_choice.add("그래 그렇게 생각했을 수 있겠다 주의할게");
        m_choice.add("내가 다른 것도 아니고 수업 때문에 확인 못 하는건데...");

        w_choice = new ArrayList<String>();
        w_choice.add("남주 말이 맞는 것 같아. 내가 무슨 타투야~ 나는 타투랑 안 어울려");
        w_choice.add("음...그런가? 다시 한 번 생각해볼게");
        w_choice.add("나 타투 너무 멋있어 보이는데... 내 생각엔 나 타투하면 더 예뻐질 것 같아");
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
                if (Mdata.get(i).equals("choice")) {
                    i++;
                    ment.setText("");
                    PopupMenu popupMenu = new PopupMenu(GameBoard.this,v);
                    switch (sex) {
                        case "man":
                            popupMenu.getMenu().add(0, 1, 0, m_choice.get(0));
                            popupMenu.getMenu().add(0, 2, 1, m_choice.get(1));
                            popupMenu.getMenu().add(0, 3, 2, m_choice.get(2));
                            break;
                        case "woman":
                            popupMenu.getMenu().add(0, 1, 0, w_choice.get(0));
                            popupMenu.getMenu().add(0, 2, 1, w_choice.get(1));
                            popupMenu.getMenu().add(0, 3, 2, w_choice.get(2));
                            break;
                    }
                    popupMenu.inflate(R.menu.menu_choice);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            ment.setText(item.getTitle());
                            return true;
                        }
                    });
                    popupMenu.show();
                } else {
                    ment.setText(Mdata.get(i++));
                }
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