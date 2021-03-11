package dell.pressball.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dell.pressball.activity.MainView;
import dell.pressball.R;
import dell.pressball.activity.NewsActivity;
import dell.pressball.activity.NewsView;
import dell.pressball.parser.model.News;
import dell.pressball.parser.ParserNews;
import dell.pressball.presenter.MainPresenter;

public class MyCustomAdapterS extends RecyclerView.Adapter<ViewHolder>{

    ArrayList<News> arrayList;

    private Context context;
    private int itemId;
    public static HashMap<Integer,String> map;

    public MyCustomAdapterS(ArrayList newsMies, int itemId) {
        this.arrayList=newsMies;
        this.itemId=itemId;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pbonline, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.timePost.setText(arrayList.get(position).getData());
        holder.rybrika.setText(arrayList.get(position).getRybrika());
        holder.title_post.setText(arrayList.get(position).getTitle());
        if (arrayList.get(position).getImg()!=null){
            Picasso.with(context).load(arrayList.get(position).getImg()).into(holder.img_pbonline);
        } else {
            holder.img_pbonline.setVisibility(View.GONE);
        }
        if (arrayList.get(position).getNews()!=null){
            holder.text_post.setText(arrayList.get(position).getNews());
        } else {
            holder.text_post.setVisibility(View.GONE);
        }
        if (arrayList.get(position).getAuthor()!=null){
            holder.author.setText(arrayList.get(position).getAuthor());
        } else {
            holder.author.setVisibility(View.GONE);
        }

        holder.item_postPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map = new HashMap<>();
                map.put(itemId,arrayList.get(position).getUrl());
                Intent intent = new Intent(context, NewsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
