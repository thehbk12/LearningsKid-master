package com.example.jcj.learningskid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserEnglishContentGrammar extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_english_content_grammar);
        listView = findViewById(R.id.listviewUserEnglishGrammar);

//        String[] productList = {"mouse","keyboard","monitor","cat","hehe"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,productList);
//        listView.setAdapter(adapter);
        List<EnglishGrammar> list =  new ArrayList<>();
        list.add(new EnglishGrammar("p1","100"));
        list.add(new EnglishGrammar("p2","200"));
        list.add(new EnglishGrammar("p3","300"));
        list.add(new EnglishGrammar("p4","hehe"));
        AdapterUser_english_content myAdapter = new AdapterUser_english_content(this,list);
        listView.setAdapter(myAdapter);
    }
    public void logout(View v){

    }


}
