package com.example.submission4gdk.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission4gdk.R;
import com.example.submission4gdk.models.FavoriteTv;
import com.example.submission4gdk.network.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvAdapterFav extends RecyclerView.Adapter<TvAdapterFav.MovieViewHolder> {

    private List<FavoriteTv> favmovie;
    private List<FavoriteTv> backup;
    private Context context;

    public TvAdapterFav(Context context) {
        this.context = context;
        favmovie = new ArrayList<>();
        backup = new ArrayList<>();
    }

    private void add(FavoriteTv item) {
        favmovie.add(item);
        backup.add(item);
        notifyItemInserted(favmovie.size() - 1);
        notifyDataSetChanged();
    }

    public void addAll(List<FavoriteTv> favmovie) {
        for (FavoriteTv favorite : favmovie) {
            add(favorite);
        }
        notifyDataSetChanged();
    }

    public void remove(int item) {
        favmovie.remove(item);
        notifyItemRemoved(item);
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem());
        }
    }

    private void remove(FavoriteTv item) {
        int position = favmovie.indexOf(item);
        if (position > -1) {
            favmovie.remove(position);
            notifyItemRemoved(position);
        }
    }

    private FavoriteTv getItem() {
        return favmovie.get(0);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_fav, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final FavoriteTv favorite = favmovie.get(position);
        holder.bind(favorite);
    }

    @Override
    public int getItemCount() {
        return favmovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;
        private TextView posterTitle,
                posterReleaseDate,
                posterDescription;
        private RatingBar posterRating;
        public RelativeLayout viewForeground;


        @SuppressLint("CutPasteId")
        private MovieViewHolder(View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.posterImage);
            posterTitle = itemView.findViewById(R.id.posterTitle);
            posterRating = itemView.findViewById(R.id.rating);
            posterDescription = itemView.findViewById(R.id.posterDescription);
            posterReleaseDate = itemView.findViewById(R.id.posterReleaseDate);
            viewForeground = itemView.findViewById(R.id.view_foreground);

        }

        private void bind(FavoriteTv favorite) {
            Picasso.get()
                    .load(Constant.IMG_URL + favorite.getPosterPath())
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(posterImage);
            posterTitle.setText(favorite.getTitle());
            posterRating.setRating((float) (favorite.getVoteAverage() / 2));
            posterDescription.setText(favorite.getOverview());
            posterReleaseDate.setText(String.format("%s  %s",
                    context.getString(R.string.txt_detail_category), favorite.getReleaseDate()));
        }
    }


}
