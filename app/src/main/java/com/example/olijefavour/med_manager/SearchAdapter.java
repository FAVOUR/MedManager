package com.example.olijefavour.med_manager;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Olije favour on 4/13/2018.
 */

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private ArrayList<String> list;


    public SearchAdapter(Context context, ArrayList<String> list)
    {
        this.context = context;
        this.list = list;
//        copyList = new ArrayList<String>();
//        copyList.addAll(list);
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list,parent,false);
        return new SearchViewHolder(view) ;
    }


    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        String name = list.get(position);
        holder.searchName.setText(name);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
//
//    //***************************************************************************//
//    //          FILETER LOGIC
//    //***************************************************************************//
//
//    public void filter(String queryText)
//    {
//        list.clear();
//
//        if(queryText.isEmpty())
//        {
//            list.addAll(copyList);
//        }
//        else
//        {
//
//            for(String name: copyList)
//            {
//                if(name.toLowerCase().contains(queryText.toLowerCase()))
//                {
//                    list.add(name);
//                }
//            }
//
//        }
//
//
//    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        public TextView searchName;

        public SearchViewHolder(View itemView) {
            super(itemView);
            searchName = (TextView)itemView.findViewById(R.id.search_name);
        }
    }
}

