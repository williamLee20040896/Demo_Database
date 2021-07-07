package sg.rp.edu.c346.id20040896.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnRetrieved;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.buttonInsert);
        btnRetrieved = findViewById(R.id.buttonGetTask);
        tv = findViewById(R.id.textView);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask("Submit RJ", "7 Jul 2021");
            }
        });
        btnRetrieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<String> data = db.getTaskContent();

                String txt ="";
                for(int i = 0; i< data.size(); i ++){
                    txt += i + ". " + data.get(i) + "\n";
                }
                tv.setText(txt);
            }
        });
    }
}