package ddwucom.mobile.bora_hackathon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardActivity extends AppCompatActivity {

    private ListView customListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customListview = findViewById(R.id.customListView);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    String title = jsonObject.getString("title");
                    String context = jsonObject.getString("context");
                    Intent intent = new Intent(BoardActivity.this, LoginResultActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("context", context);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

    }

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                break;
            case R.id.btn_center:
                break;
        }
    }
}
