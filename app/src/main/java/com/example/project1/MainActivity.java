package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name, id, amp , previous , present , total;
    TextView kwa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        kwa = findViewById(R.id.kwa);
        amp = findViewById(R.id.ampere);
        previous = findViewById(R.id.prevkwa);
        present = findViewById(R.id.preskwa);
        total = findViewById(R.id.consume);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    public void calculatekwa(View view) {
        int diff;
        diff = Integer.parseInt(present.getText().toString()) - Integer.parseInt(previous.getText().toString());
        kwa.setText("The Total Consume is : "  + diff + "Kwa");
        Toast.makeText(this, "The total consume : " + diff , Toast.LENGTH_SHORT).show();
    }


    public void calculbill(View view) {
        double bills;
        if(Integer.parseInt(amp.getText().toString()) == 5) {
            bills = (Double.parseDouble(total.getText().toString()) * 0.75) + 5;
        } else {
            bills = (Double.parseDouble(total.getText().toString()) * 0.75) + 15;
        }
        Intent i = new Intent(this , showbills.class);

        i.putExtra("NAME" , name.getText().toString());
        i.putExtra("ID" , id.getText().toString());
        i.putExtra("AMP" , amp.getText().toString());
        i.putExtra("TOTAL" , total.getText().toString());
        i.putExtra("BILLS",bills);

        name.setText("");
        id.setText("");
        amp.setText("");
        total.setText("");
        present.setText("");
        previous.setText("");

        startActivity(i);
    }


}