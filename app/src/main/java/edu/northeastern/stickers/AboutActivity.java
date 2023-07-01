package edu.northeastern.stickers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.AboutAdapter;
import edu.northeastern.stickers.models.GroupMembers;

public class AboutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AboutAdapter aboutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        recyclerView = findViewById(R.id.recyclerViewAbout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<GroupMembers> groupMembers = getGroupMembers();


        aboutAdapter = new AboutAdapter(groupMembers);
        recyclerView.setAdapter(aboutAdapter);
    }

    private List<GroupMembers> getGroupMembers() {
        // Create a list of GroupMember objects
        List<GroupMembers> groupMembers = new ArrayList<>();

        // Add group member details
        groupMembers.add(new GroupMembers(". Manushi", "manushig", "manushi.f@northeastern.edu"));
        groupMembers.add(new GroupMembers("Komal Upadhyay", "komalup1991", "upadhyay.ko@northeastern.edu"));
        groupMembers.add(new GroupMembers("Rakshit Soni", "rakshitsoni05", "soni.rak@northeastern.edu"));
        groupMembers.add(new GroupMembers("Komal Upadhyay", "Ruby-8", "liang.ziy@northeastern.edu"));

        return groupMembers;
    }
}