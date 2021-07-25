package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    final int READ_CODE = 100;
    final int SEARCH_CODE = 200;

    private MyAdapter_board myAdapter;
    private ListView listView;
    private ArrayList<Board> boardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        myAdapter = new MyAdapter_board(this, R.layout.custom_adapter_view, boardList);
        listView = (ListView)findViewById(R.id.customListView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Board board = boardList.get(position);
                Intent intent = new Intent(BoardActivity.this, DetailActivity.class);
                intent.putExtra("board", board);
                startActivityForResult(intent, READ_CODE);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        boardList.clear();
//        boardList.addAll(boardDBManager.getAllBoard());
        myAdapter.notifyDataSetChanged();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_search:
                Intent intent = new Intent(BoardActivity.this, BoardSearchActivity.class);
                startActivityForResult(intent, SEARCH_CODE);
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }
}
