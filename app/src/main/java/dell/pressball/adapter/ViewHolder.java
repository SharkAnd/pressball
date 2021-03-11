package dell.pressball.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dell.pressball.R;


public  class ViewHolder extends RecyclerView.ViewHolder {

    TextView timePost;
    TextView rybrika;
    TextView title_post;
    TextView text_post;
    TextView author;

    ImageView img_pbonline;

    CardView item_postPB;

    public ViewHolder(View itemView) {
        super(itemView);

        item_postPB = (CardView) itemView.findViewById(R.id.item_postPB);
        img_pbonline = (ImageView ) itemView.findViewById(R.id.img_pbonline);
        timePost = (TextView) itemView.findViewById(R.id.time_post);
        rybrika = (TextView) itemView.findViewById(R.id.rybrika);
        title_post = (TextView) itemView.findViewById(R.id.title_post);
        text_post = (TextView) itemView.findViewById(R.id.text_post);
        author = (TextView) itemView.findViewById(R.id.author);
    }
}