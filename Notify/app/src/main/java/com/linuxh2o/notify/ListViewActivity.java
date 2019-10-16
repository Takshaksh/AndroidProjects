package com.linuxh2o.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {

    private ListView myListView;
    private ArrayAdapter myArrayAdapter;
    private String[] languages = { "Programming Languages", "Assembly", "BASH", "C/C++", "Dart", "Erlang", "Fortran", "GO", "Haskell", "IO", "Java", "Kotlin", "Lips", "Matlab"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        myListView = findViewById(R.id.listView);
        myArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, languages);
        myListView.setAdapter(myArrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = myArrayAdapter.getItem(position).toString();
                Toast.makeText(getApplicationContext(), clickedItem, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
