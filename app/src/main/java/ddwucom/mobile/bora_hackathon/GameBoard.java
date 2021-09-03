package ddwucom.mobile.bora_hackathon;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GameBoard extends AppCompatActivity {
    TextView ment;
    TextView name;
    ImageView image;
    ImageView characterImg;
    ImageView snsResult;
    ImageView gameNext;
    ArrayList<String> bgData;
    ArrayList<String> Mdata;
    ConstraintLayout gameBoardLayout;
    ConstraintLayout snsresLayout;
    ImageView snsTop;
    //ImageView next_btn;

    int i;
    int ch;
    int percent;
    int s;

    String sex;
    String first_name;
    String last_name;

    ArrayList<String> w_choice;
    ArrayList<String> m_response;
    ArrayList<String> m_choice;
    ArrayList<String> w_response;

    SoundManager soundManager;
    SoundPool soundPool;
    boolean play;
    int playSoundId;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);


        /*ActionBar actionbar = getSupportActionBar();
        actionbar.hide();*/

        i = 0;
        ch = 0;
        percent = 0;
        s = 0;

        Intent intent = getIntent();
        sex = intent.getStringExtra("sex");
        first_name = intent.getStringExtra("firstName");
        last_name = intent.getStringExtra("lastName");

        snsresLayout = findViewById(R.id.snsresLayout);
        //snsresLayout.bringToFront();
        snsresLayout.setVisibility(View.INVISIBLE);
        snsTop = findViewById(R.id.snsTop);
        //next_btn = findViewById(R.id.btn_game_next);

        ment = findViewById(R.id.et_game_ment);
        name = findViewById(R.id.ch_name);
        image = findViewById(R.id.game_image);
        characterImg = findViewById(R.id.character_img);
        gameNext = findViewById(R.id.btn_game_next);

        gameNext.bringToFront();

        Glide.with(this)
                .load(R.drawable.cafe)
                .placeholder(R.drawable.load)
                .into(image);

        if (sex.equals("man")) {
            Glide.with(this)
                    .load(R.drawable.woman_character)
                    .into(characterImg);
            /*Glide.with(this)
                    .load(R.drawable.sns_top_w)
                    .into(snsTop);*/
        } else {
            Glide.with(this)
                    .load(R.drawable.man_character)
                    .into(characterImg);
            Glide.with(this)
                    .load(R.drawable.sns_top_m)
                    .into(snsTop);
        }

        data_insert();
        name.setText(Mdata.get(i++));
        ment.setText(Mdata.get(i++));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        soundManager = new SoundManager(this, soundPool);

        soundManager.addSound(1, R.raw.page1);
        soundManager.addSound(2, R.raw.page2);
        soundManager.addSound(3, R.raw.page3);
        soundManager.addSound(4, R.raw.kakaotalk);
    }

    public void data_insert() {
        String girl_name = "신아영";
        String boy_name = "권다온";
        String full_name = "나";

        bgData = new ArrayList<String>();
        Mdata = new ArrayList<String>();
        if (sex.equals("man")) {
            Mdata.add(full_name);
            Mdata.add("아영아 왜 이렇게 늦었어? ");
            Mdata.add(girl_name);
            Mdata.add("미안해 오늘 일이 좀 있어서 늦었어");
            Mdata.add(full_name);
            Mdata.add("그러면 일 끝나고 오는 길에라도 연락주지 기다렸잖아\n다음부터는 미리 연락 좀 해줘");
            Mdata.add(girl_name);
            Mdata.add("말을 왜 그런식으로 해 나도 내가 늦을 줄 몰랐어 내가 사과도 했는데 너무 예민한 거 아니야?");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(girl_name);
            Mdata.add(" ");

            Mdata.add("sns");
            Mdata.add(full_name);
            Mdata.add("choice");

            Mdata.add("restaurant");
            Mdata.add(girl_name);
            Mdata.add(first_name + "야(아) 조금만 쉬었다가 가자 나 발이 너무 아파");
            Mdata.add(full_name);
            Mdata.add("발 아파? 발 편한 운동화 신고 오지");
            Mdata.add(girl_name);
            Mdata.add("너한테 잘 보이고 싶어서 구두 신고 온거지... 이런 것도 몰라줘?");
            Mdata.add(full_name);
            Mdata.add("아니 난 그냥 너 아프다니까 걱정돼서 한 말이지...");
            Mdata.add(girl_name);
            Mdata.add("그렇게 걱정되면...차 살 생각은 없어? 왜 너 친구들하고 술 마시는 것만 줄여도 살 수 있을 것 같은데... 우리 차 사면 1박 2일로 놀러 갈 수도 있고 좋잖아 어때?");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(girl_name);
            Mdata.add(" ");

            Mdata.add("room");
            Mdata.add(girl_name);
            Mdata.add(first_name + "야(아) 이 여자는 누구야? 왜 너 sns에 댓글을 달아?");
            Mdata.add(full_name);
            Mdata.add("누구? 아 그냥 고등학교 때 같이 학원 다녔던 친구야");
            Mdata.add(girl_name);
            Mdata.add("친했어?");
            Mdata.add(full_name);
            Mdata.add("음 아무래도 학원 다닐 땐 좀 친했지?");
            Mdata.add(girl_name);
            Mdata.add("...난 네 sns에 다른 여자가 댓글 다는 거 싫은데 팔로잉 끊으면 안돼? 이 참에 그냥 전화번호도 지워버리자");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(girl_name);
            Mdata.add(" ");

            Mdata.add("cafe");
            Mdata.add(girl_name);
            Mdata.add(first_name + "야(아) 이 냄새 뭐야 혹시 담배 폈어?");
            Mdata.add(full_name);
            Mdata.add("아니 아까 선배가 담배 필 때 옆에 같이 있었는데 그때 냄새가 뱄나봐");
            Mdata.add(girl_name);
            Mdata.add("거짓말 하지마 솔직히 피운 거 맞지?");
            Mdata.add(full_name);
            Mdata.add("...미안해");
            Mdata.add(girl_name);
            Mdata.add("내가 내 생각해서 담배 끊으라는게 아니라 네 건강 생각해서 끊으란거잖아\n이게 그렇게 힘든 일이야? 내 친구 남친은 금방 끊었다는데 이건 그냥 의지가 부족한 거 아니야?");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(girl_name);
            Mdata.add(" ");

        } else {
            Mdata.add(full_name);
            Mdata.add("저 여자분 타투 너무 멋있다. 나도 나중에 타투하고 싶어!");
            Mdata.add(boy_name);
            Mdata.add("갑자기 타투? " + first_name + "(이) 타투하고 싶어? 후회하지 않을까?");
            Mdata.add(full_name);
            Mdata.add("에이 내가 내 몸에 원하는 거 남기는데 그게 무슨 상관이야~");
            Mdata.add(boy_name);
            Mdata.add("난 지금 타투 없는 " + first_name + "(이)가 더 좋은데...\n타투 없는게 훨씬 예뻐 " + first_name + "야(아)!\n다시 한 번 생각해봐ㅎㅎ");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(boy_name);
            Mdata.add(" ");

            Mdata.add("restaurant");
            Mdata.add(boy_name);
            Mdata.add(first_name + "야(아) 립스틱 새로 산거야? 예쁜데 평소에 바르는거랑 좀 다른 것 같아");
            Mdata.add(full_name);
            Mdata.add("응 이번에 올뤄뷰용 세일할때 같이 간 친구들이 이거 추천해줘서 샀어");
            Mdata.add(boy_name);
            Mdata.add("친구들이 " + first_name + "(이) 질투하나? 오히려 립스틱이 우리 " + first_name + "(이) 미모를 방해해 전에 바르던게 훨씬 잘 어울려");
            Mdata.add(full_name);
            Mdata.add("이것도 예쁘기만 한데... 별로야?");
            Mdata.add(boy_name);
            Mdata.add(first_name + "(이)야 뭐 다 예쁘지만 나는 " + first_name + "(이)한테 제일 어울리는 걸 찾아주고 싶어서 그런거지! 그런 의미에서 앞으로 쇼핑은 나랑만 가는걸로 약속해 " + first_name + "야(아)");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(boy_name);
            Mdata.add(" ");

            Mdata.add("sns");
            Mdata.add(full_name);
            Mdata.add("choice");

            Mdata.add("cafe");
            Mdata.add(full_name);
            Mdata.add("오랜만에 올렸는데 좋아요 많이 받았네 기분 좋다");
            Mdata.add(boy_name);
            Mdata.add("방금 sns에 올린 사진 뭐야? 우리 " + first_name + "(이) 너무 예쁜데 이런 사진은 sns에 안 올렸으면 좋겠다");
            Mdata.add(full_name);
            Mdata.add("이 사진이 왜? 잘 나오지 않았어?");
            Mdata.add(boy_name);
            Mdata.add("노출이 너무 심하잖아 바지랑 블라우스가 너무 짧아\n나랑 데이트할 때는 상관없는데 이걸 사람들 다 보는데에 올리는 건 좀 그렇지 않아?");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(boy_name);
            Mdata.add(" ");

            Mdata.add("room");
            Mdata.add(boy_name);
            Mdata.add(first_name + "야(아) 너무 늦었다 내일 일어나서 과제 마무리하는 걸로 하고 이제 자자 너무 피곤해보여");
            Mdata.add(full_name);
            Mdata.add("아니야 나 이거 끝내고 잘거야 학점 잘 받아야 좋은데 취업하지");
            Mdata.add(boy_name);
            Mdata.add("취업은 무슨 나중에 우리 결혼하면 너는 편하게 집에만 있어 돈은 내가 벌게");
            Mdata.add(full_name);
            Mdata.add("나도 취직해서 직접 돈 벌면서 살고 싶은데?");
            Mdata.add(boy_name);
            Mdata.add("나는 " + first_name + "(이) 사회생활하느라 힘든거 싫단 말이야... 그냥 취직 안 했으면 좋겠어");
            Mdata.add(full_name);
            Mdata.add("choice");
            Mdata.add(boy_name);
            Mdata.add(" ");
        }
        m_choice = new ArrayList<String>();
        m_choice.add("내가 예민했네 미안해 아영아 화풀어");
        m_choice.add("그래... 내가 요새 좀 피곤해서 그런가보다");
        m_choice.add("미리 연락 좀 해달라는게 예민한거야?");
        m_choice.add("미안해 앞으로 5분에 한번씩 카톡 확인할게");
        m_choice.add("그냥 오늘은 정신이 좀 없었어 더 신경쓸게");
        m_choice.add("내가 매일 그러는 것도 아니고 너도 연락에 너무 집착하는 것 같아");
        m_choice.add("그래 내가 대출받아서 살게");
        m_choice.add("물론 있으면 좋겠지만... 알겠어 일단 노력해볼게");
        m_choice.add("나도 아직 학생이잖아 현실적으로 차는 못 살 것 같아");
        m_choice.add("그래 요새 연락도 잘 안하는 친구니까 그냥 차단하고 지우자");
        m_choice.add("아... 많이 신경쓰여?");
        m_choice.add("그래도 내 친구인데 이렇게 갑자기 지우는 건 좀 아닌 것 같아");
        m_choice.add("알겠어 노력할게 당장 오늘부터 금연할게");
        m_choice.add("알겠어 금연 생각해 볼게");
        m_choice.add("담배 하루 아침에 끊기 힘든 거 알잖아 이해 좀 해줘");

        w_response = new ArrayList<String>();
        w_response.add("알았어 맛있는 거 먹으러 가자");
        w_response.add(first_name + "(이)가 나한테 그럴리가 없는데 어쩐지~ 다크써클이 심해졌다 했어.");
        w_response.add("아니 알았어.. 다음부터는 꼭 연락할게");
        w_response.add("역시 우리 " + first_name + "(이) 최고~ 앞으로 무조건 칼답해!");
        w_response.add("응 주의 좀 해줘 부탁할게~");
        w_response.add("내가 이상하단거야? 몰라 나는 연락 안 되면 불안하니까 어떻게든 빨리 답장해줘");
        w_response.add("좋아!! 빨리 사서 매일매일 여행 가자~");
        w_response.add("응응 최대한 빨리 사면 좋겠다!");
        w_response.add("그래 알겠어 어쩔 수 없겠네 계속 이런 데이트 하자");
        w_response.add("우리 " + first_name +  "(이) 말 잘듣고 너무 착하다~ " + first_name + "(은)는 나랑만 연락해");
        w_response.add("응 " + first_name + "(은)는 나랑만 연락했으면 좋겠어");
        w_response.add("그래 알겠어 그래도 좀 기분 나쁘다 천천히 지워줬으면 좋겠어");
        w_response.add("그래 우리 " + first_name + "(이) 금연 파이팅! 내가 금연껌 사줄게!!!");
        w_response.add("긍정적인 방향으로 생각해봐");
        w_response.add("그래도 나랑 만날 때만큼은 금연해줘 남들은 다 하던데 좀 더 노력해봐");

        w_choice = new ArrayList<String>();
        w_choice.add("다온이 말이 맞는 것 같아. 내가 무슨 타투야~ 나는 타투랑 안 어울려");
        w_choice.add("음...그런가? 다시 한 번 생각해볼게");
        w_choice.add("난 타투 너무 멋있어 보이는데... 내가 하고싶음 하는 거지 뭐 말리지마~");
        w_choice.add("역시 우리 다온이 너무 세심해 고마워 다온아");
        w_choice.add("치.. 알겠어");
        w_choice.add("나랑 잘 맞는다고 해서 이거 산건데.. 산거니까 일단 바를래");
        w_choice.add("미안해 다온아. 내가 더 조심할게");
        w_choice.add("아...그런건가?");
        w_choice.add("나는 일 하는 알바생인데 손님한테 친절하게 대해야지 화를 낼 수는 없잖아");
        w_choice.add("알았어 다온아 그럼 그냥 삭제할게");
        w_choice.add("이게 그렇게 노출이 심한가...?");
        w_choice.add("나는 이 사진 너무 마음에 드는데? 안 지울래");
        w_choice.add("역시 우리 다온이는 내 미래까지 걱정해주네 알았어 자자");
        w_choice.add("그래도 먹고 살려면 취직은 해야하지 않을까?");
        w_choice.add("아니야 난 나중에 꼭 내가 하고 싶은 일 하면서 살거야");

        m_response = new ArrayList<String>();
        m_response.add("역시 우리 " + first_name + "(이) 나랑 너무 잘 맞아~");
        m_response.add("그래그래 잘 생각했어ㅎㅎ!");
        m_response.add("그래 뭐 사람마다 다 생각은 다르지!");
        m_response.add("여자친구한테 이 정도 관심 가지는 건 당연하지^-^");
        m_response.add("착하다 " + first_name + "(이)");
        m_response.add("그래 산거니까 어쩔 수 없네!");
        m_response.add("알았어 " + first_name + "야(아) 이따가 데리러 갈게");
        m_response.add("응 괜찮아 몰랐어도 앞으로 잘하면 되니까!");
        m_response.add("그래 알겠어 그래도 앞으로는 신경써서 일 해줘");
        m_response.add(first_name + "(이) 착하네 다음부터는 아예 sns 올리기 전에 나한테 검사맡아!");
        m_response.add("응 삭제해줬으면 좋겠어");
        m_response.add("알겠어 다음부터는 같이 올릴 사진 정하자");
        m_response.add("그래 " + first_name + "야(아) 오늘 너무 늦었다");
        m_response.add("내가 일할게 나만 믿어 " + first_name + "야(아)");
        m_response.add("그래 그래도 꼭 다시 잘 생각해봐");
    }


    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn_game_next:
                if (!play) {
                    playSoundId = soundManager.playSound(1);
                    play = true;
                } else {
                    soundManager.resumeSound(playSoundId);
                    //soundManager.playSound(playSoundId);
                    play = false;
                }

                snsResult = findViewById(R.id.sns_result_img);
                //snsResult.setVisibility(v.INVISIBLE);
                snsresLayout.setVisibility(v.INVISIBLE);

                if (i == Mdata.size()) {
                    if (percent == 0) {
                        percent = 1;
                    } else if (percent == 100) {
                        percent = 99;
                    }
                    Intent r = new Intent(GameBoard.this, GameResult.class);
                    r.putExtra("sex", sex);
                    r.putExtra("percent", Integer.toString(percent));
                    startActivity(r);
                    break;
                }

                switch (Mdata.get(i++)) {
                    case "restaurant":
                        characterImg.setVisibility(v.VISIBLE);
                        name.setVisibility(v.VISIBLE);
                        ment.setVisibility(v.VISIBLE);
                        Glide.with(this)
                                .load(R.drawable.restaurant)
                                .placeholder(R.drawable.load)
                                .transition(withCrossFade())
                                .into(image);
                        break;
                   case "room":
                       characterImg.setVisibility(v.VISIBLE);
                       name.setVisibility(v.VISIBLE);
                       ment.setVisibility(v.VISIBLE);
                       Glide.with(this)
                               .load(R.drawable.room)
                               .placeholder(R.drawable.load)
                               .transition(withCrossFade())
                               .into(image);
                       break;
                    case "cafe":
                        characterImg.setVisibility(v.VISIBLE);
                        name.setVisibility(v.VISIBLE);
                        ment.setVisibility(v.VISIBLE);
                        Glide.with(this)
                                .load(R.drawable.cafe)
                                .placeholder(R.drawable.load)
                                .transition(withCrossFade())
                                .into(image);
                        break;
                    case "sns" :
                        if (!play) {
                            playSoundId = soundManager.playSound(4);
                            play = true;
                        } else {
                            soundManager.resumeSound(playSoundId);
                            //soundManager.playSound(playSoundId);
                            play = false;
                        }
                        s = 1;
                        Intent sns = new Intent(GameBoard.this, GameSns.class);
                        sns.putExtra("sex", sex);
                        startActivity(sns);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gameNext.setVisibility(View.INVISIBLE);
                                characterImg.setVisibility(v.GONE);
                                name.setVisibility(v.INVISIBLE);
                                ment.setVisibility(v.INVISIBLE);
                                snsresLayout.setVisibility(v.VISIBLE);

                                if (sex.equals("man")) {
                                    //gameNext.setVisibility(View.INVISIBLE);
                                    Glide.with(v)
                                            .load(R.drawable.m_sns6)
                                            .placeholder(R.drawable.load)
                                            .into(image);
                                } else {
                                    //gameNext.setVisibility(View.INVISIBLE);
                                    Glide.with(v)
                                            .load(R.drawable.w_sns6)
                                            .placeholder(R.drawable.load)
                                            .into(image);
                                }
                            }
                        }, 300);
                        break;
                    default:
                        i--;
                        break;
                }
                name.setText(Mdata.get(i++));
                if (Mdata.get(i).equals("choice")) {
                    i++;
                    ment.setText("");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            AlertDialog.Builder oDialog = new AlertDialog.Builder(GameBoard.this, R.style.MyAlertDialogStyle);

                            Typeface tf = getResources().getFont(R.font.sc_dream7);

                            TextView title = new TextView(GameBoard.this);
                            title.setText("나의 답변");
                            title.setWidth(0);
                            title.setHeight(150);
                            title.setGravity(Gravity.CENTER);
                            title.setTextColor(Color.BLACK);
                            title.setTextSize(20);
                            title.setTypeface(tf);
                            title.setBackgroundColor(Color.rgb(197, 180, 239));

                            switch (sex) {
                                case "man":
                                    final CharSequence[] mItems = {"· " + m_choice.get(ch), "· " + m_choice.get(ch + 1), "· " +m_choice.get(ch + 2)};
                                    final CharSequence[] wRes = {w_response.get(ch), w_response.get(ch + 1), w_response.get(ch + 2)};

                                    oDialog.setCustomTitle(title)
                                            .setItems(mItems, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (which == 0) {
                                                        percent += 20;
                                                    } else if (which == 1) {
                                                        percent += 10;
                                                    }
                                                    if (s == 1) {
                                                        gameNext.setVisibility(View.VISIBLE);
                                                        s = 0;
                                                        switch (which) {
                                                            case 0:
                                                                Glide.with(v)
                                                                        .load(R.drawable.m1)
                                                                        .placeholder(R.drawable.load)
                                                                        .into(snsResult);
                                                                break;
                                                            case 1:
                                                                Glide.with(v)
                                                                        .load(R.drawable.m2)
                                                                        .placeholder(R.drawable.load)
                                                                        .into(snsResult);
                                                                break;
                                                            case 2:
                                                                Glide.with(v)
                                                                        .load(R.drawable.m3)
                                                                        .placeholder(R.drawable.load)
                                                                        .into(snsResult);
                                                                break;
                                                        }
                                                        snsresLayout.setVisibility(v.VISIBLE);
                                                    } else {
                                                        ment.setText(m_choice.get(ch + which - 3));
                                                        Mdata.set(i + 1, wRes[which].toString());
                                                    }
                                                }
                                            })
                                            .setCancelable(false)
                                            .show();
                                    break;
                                case "woman":
                                    final CharSequence[] wItems = {"· " + w_choice.get(ch), "· " + w_choice.get(ch + 1), "· " + w_choice.get(ch +2)};
                                    final CharSequence[] mRes = {m_response.get(ch), m_response.get(ch + 1), m_response.get(ch + 2)};
                                    oDialog.setCustomTitle(title)
                                            .setItems(wItems, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (which == 0) {
                                                        percent += 20;
                                                    } else if (which == 1) {
                                                        percent += 10;
                                                    }
                                                    if (s == 1) {
                                                        gameNext.setVisibility(View.VISIBLE);
                                                        s = 0;
                                                        switch (which) {
                                                            case 0:
                                                                Glide.with(v)
                                                                        .load(R.drawable.w1)
                                                                        .placeholder(R.drawable.load)
                                                                        .into(snsResult);
                                                                break;
                                                            case 1:
                                                                Glide.with(v)
                                                                        .load(R.drawable.w2)
                                                                        .placeholder(R.drawable.load)
                                                                        .into(snsResult);
                                                                break;
                                                            case 2:
                                                                Glide.with(v)
                                                                        .load(R.drawable.w3)
                                                                        .placeholder(R.drawable.load)
                                                                        .into(snsResult);
                                                                break;
                                                        }
                                                        snsresLayout.setVisibility(v.VISIBLE);
                                                    } else {
                                                        ment.setText(w_choice.get(ch + which - 3));
                                                        Mdata.set(i + 1, mRes[which].toString());
                                                    }
                                                }
                                            })
                                            .setCancelable(false)
                                            .show();
                                    break;
                            } ch += 3;
                        }
                    }, 300);

                } else {
                    ment.setText(Mdata.get(i++));
                }
                break;
        }

    }
//    public void onStop() {
//        super.onStop();
//        finish();
//    }
}