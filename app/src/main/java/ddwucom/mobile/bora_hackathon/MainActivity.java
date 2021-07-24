package ddwucom.mobile.bora_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyAdapter myAdapter;
    private ListView listView;
    private ArrayList<Board> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieList);
        listView = (ListView)findViewById(R.id.customListView);
        listView.setAdapter(myAdapter);
    }
    //수정
}