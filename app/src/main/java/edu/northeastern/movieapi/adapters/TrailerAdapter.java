package edu.northeastern.movieapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.northeastern.movieapi.OnItemActionListener;
import edu.northeastern.movieapi.R;
import edu.northeastern.movieapi.model.Movie;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private final List<Movie> movieList;
    private Context context;
    private OnItemActionListener onItemActionListener;
    public TrailerAdapter(List<Movie> movieList,OnItemActionListener onItemActionListener) {
        this.movieList = movieList;
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);
        View itemLayout = inflater.inflate(R.layout.trailer_card_detail, parent, false);
        return new TrailerViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int pos) {
        int position=holder.getAdapterPosition();
        String title = movieList.get(position).getTitle();
        String detail = movieList.get(position).getGenres();
        String image = movieList.get(position).getImage();
        holder.movieTitle.setText(title);
        Picasso.get().load(image).resize(200, 300).into(holder.movieImage);
        holder.movieDetail.setText(detail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemActionListener.onClick(movieList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
