package com.example.jcj.learningskid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterUser_english_content extends BaseAdapter {
    private UserEnglishContentGrammar activity;
    private List<EnglishGrammar> listProduct;
    public AdapterUser_english_content(UserEnglishContentGrammar activity, List<EnglishGrammar> listProduct) {
        this.activity = activity;
        this.listProduct = listProduct;
    }
    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return  listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.layout_english_grammar,null);
            myHolder =  new MyHolder();
            myHolder.textViewName = convertView.findViewById(R.id.textViewEnglishGrammar);

            convertView.setTag(myHolder);
        }else{
            myHolder = (MyHolder) convertView.getTag();
        }

        myHolder.textViewName.setText(listProduct.get(position).getName());

        return convertView;
    }

    class MyHolder{
        public TextView textViewName;


    }
}
