package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CenterActivity extends AppCompatActivity {
    final static String TAG = "CenterActivity";
    private MyAdapter_center myAdapter_center;
    private ListView listView;
    private ArrayList<Center> centerList;
    CenterDBManager centerDBManager;
    int pos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        // 타이틀바 로고 넣기
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        centerDBManager = new CenterDBManager(this);
        centerList = centerDBManager.getAllCenter();
        myAdapter_center = new MyAdapter_center(this, R.layout.custom_adapter_view_center, centerList);
        listView = (ListView)findViewById(R.id.center_customListView);
        listView.setAdapter(myAdapter_center);
    }

    public void onResume() {
        super.onResume();
        centerList.clear();
        centerList.addAll(centerDBManager.getAllCenter());
        myAdapter_center.notifyDataSetChanged();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_centerCancel:
                finish();
                break;
        }
    }
}
