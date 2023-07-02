package edu.northeastern.stickers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.models.GroupMembers;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    private List<GroupMembers> groupMembers;

    public AboutAdapter(List<GroupMembers> groupMembers) {
        this.groupMembers = groupMembers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_item_group_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupMembers groupMember = groupMembers.get(position);
        String name = "Name: " + groupMember.getName();
        String githubId = "Github Id: " + groupMember.getGithubId();
        String email = "Email Id: " + groupMember.getEmail();

        holder.nameTextView.setText(name);
        holder.githubIdTextView.setText(githubId);
        holder.emailTextView.setText(email);
    }

    @Override
    public int getItemCount() {
        return groupMembers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView githubIdTextView;
        public TextView emailTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.text_name);
            githubIdTextView = itemView.findViewById(R.id.text_github_id);
            emailTextView = itemView.findViewById(R.id.text_email);
        }
    }
}