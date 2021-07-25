package ddwucom.mobile.bora_hackathon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    Board board;
    EditText date;
    EditText title;
    EditText context;
    MyAdapter_comment myAdapter_comment;
    ListView listView;
    ArrayList<Comment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        board = (Board) getIntent().getSerializableExtra("board");

        date = findViewById(R.id.et_boardDate);
        title = findViewById(R.id.et_boardTitle1);
        context = findViewById(R.id.et_boardContext1);
        listView = findViewById(R.id.customListView);

        date.setText(board.getDate());
        title.setText(board.getTitle());
        context.setText(board.getContext());

        commentList = new ArrayList();
        myAdapter_comment = new MyAdapter_comment(this, R.layout.custom_adapter_view_comment, commentList);
        listView.setAdapter(myAdapter_comment);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                String message = "댓글을 삭제하겠습니까?";
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle("삭제 확인")
                        .setMessage(message)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }
}