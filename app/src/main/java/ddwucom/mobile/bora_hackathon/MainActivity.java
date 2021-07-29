package ddwucom.mobile.bora_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyAdapter_board myAdapterBoard;
    private ListView listView;
    private ArrayList<Board> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_game:
                break;
            case R.id.button_board:
                Intent intent = new Intent(this, BoardActivity.class);
                startActivity(intent);
                break;
        }
    }
}