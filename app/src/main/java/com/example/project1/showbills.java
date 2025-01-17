package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Month;

public class showbills extends AppCompatActivity {
TextView bills;

Spinner month;
ImageButton exit;
RadioButton usd , lbp;
ListView ls;
String[] subscriberarray ={"ALI" , "MOHAMAD" , "SAMI"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showbills);

        bills = findViewById(R.id.tvbills);
        ls = findViewById(R.id.subscriberlist);

        ArrayAdapter adapter = new ArrayAdapter<String>(this , R.layout.list_view , subscriberarray);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent index = new Intent(showbills.this , Details.class);
                index.putExtra("I" , position);
                startActivity(index);
            }
        });
        usd = findViewById(R.id.rdusd);
        lbp = findViewById(R.id.rdlbp);

        Intent j = getIntent();
        bills.setText("\nName : " + j.getStringExtra("NAME")+
                        "\nId : " + j.getStringExtra("ID")+
                        "\nAmpere : " + j.getStringExtra("AMP")+
                        "\nTotal Consume : " + j.getStringExtra("TOTAL")+
                    "\nThe Total Bill is : $" + j.getDoubleExtra("BILLS",0) );

        month = findViewById(R.id.spmonth);
        exit = findViewById(R.id.btnexit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void btnsubmit(View view) {
        String s = month.getSelectedItem().toString();

        if(usd.isChecked()){
            Toast.makeText(this, "BILL PAID IN USD , " + "THE BILL PAID OF THE MONTH : " + month.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

        }
        if(lbp.isChecked()){
            Toast.makeText(this, "BILL PAID IN LBP , " + "THE BILL PAID OF THE MONTH : " + month.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        }

    }
}