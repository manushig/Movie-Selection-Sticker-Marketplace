package edu.northeastern.stickers;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import edu.northeastern.movieapi.R;
import edu.northeastern.movieapi.databinding.FragmentItemListDialogListDialogBinding;
import edu.northeastern.stickers.models.ReceivingInfo;
import edu.northeastern.stickers.models.SendingInfo;
import edu.northeastern.stickers.models.Users;

public class ItemListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_STICKER_ID = "sticker_id";
    private static final String ARG_STICKER_PATH = "sticker_path";
    private FragmentItemListDialogListDialogBinding binding;

    ArrayList<Users> userList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private String stickerId;
    private String stickerPath;

    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance(String stickerId, String stickerPath) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_STICKER_ID, stickerId);
        args.putString(ARG_STICKER_PATH, stickerPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false);
        adapter = new ItemAdapter(userList);
        stickerId = getArguments().getString(ARG_STICKER_ID);
        stickerPath = getArguments().getString(ARG_STICKER_PATH);
        String auth = FirebaseAuth.getInstance().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = new Users(snapshot.getKey(), snapshot.child("email").getValue().toString(),
                            snapshot.child("name").getValue().toString());
                    if(!Objects.equals(user.getUserId(), auth)){
                    userList.add(user);}

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView sendCounttextView = view.findViewById(R.id.sendCounttextView);
        final RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        ImageView sendStickerImage = view.findViewById(R.id.sendimageView);

        Glide.with(view.getContext())
                .load(stickerPath)
                .apply(new RequestOptions().override(150, 150))
                .into(sendStickerImage);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .child("SentStickerCount").child(stickerId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int count = 0;
                        if (snapshot.getValue() != null) {
                            count = Integer.parseInt(snapshot.getValue().toString());
                        }

                        sendCounttextView.setText(view.getResources()
                                .getQuantityString(R.plurals.you_have_sent_this_sticker_n_times, count, count));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        final View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            text = itemView.findViewById(R.id.text);
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<Users> items;

        ItemAdapter(List<Users> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item_list_dialog_list_dialog_item, parent, false));

        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {


            holder.text.setText(items.get(holder.getBindingAdapterPosition()).getName());
            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                    String currentUserUid = FirebaseAuth.getInstance().getUid();
                    String otherUserUid = items.get(holder.getBindingAdapterPosition()).getUserId();

                    updateCurrentUserStickerCount(databaseReference, currentUserUid);

                    updateCurrentUserHistory(databaseReference, currentUserUid, otherUserUid);
                    sendToOtherUser(databaseReference, otherUserUid, currentUserUid);

                }
            });
        }

        private void updateCurrentUserStickerCount(DatabaseReference databaseReference, String currentUserUid) {
            DatabaseReference stickerRef = databaseReference.child(currentUserUid).child("SentStickerCount")
                    .child(stickerId);
            stickerRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.getResult().exists()) {
                        stickerRef.setValue(Integer.parseInt(task.getResult().getValue().toString()) + 1);
                    } else {
                        stickerRef.setValue(1);
                    }
                }
            });
        }

        private void sendToOtherUser(DatabaseReference databaseReference, String otherUserUid, String currentUserUid) {
            databaseReference.child(otherUserUid).child("ReceivedHistory")
                    .child(UUID.randomUUID().toString())
                    .setValue(new ReceivingInfo(currentUserUid, stickerId,
                            String.valueOf(Calendar.getInstance().getTime()),stickerPath));

        }

        private void updateCurrentUserHistory(DatabaseReference databaseReference, String currentUserUid, String otherUserUid) {
            databaseReference.child(currentUserUid).child("SentHistory")
                    .child(UUID.randomUUID().toString())
                    .setValue(new SendingInfo(otherUserUid, stickerId,
                            String.valueOf(Calendar.getInstance().getTime()),stickerPath))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference databaseRef =  databaseReference.child(currentUserUid);
                                databaseRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        testNotificationCode(snapshot, stickerPath, otherUserUid);
                                        databaseRef.removeEventListener(this);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                Toast.makeText(getContext(), "Sent Successfully!", Toast.LENGTH_SHORT).show();
                                dismiss();


                            }
                        }
                    });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private void testNotificationCode(DataSnapshot dataSnapshot, String stickerPath, String otherUserUid) {
            String title = "You've Got a Sticker!";
            Users currentUser = dataSnapshot.getValue(Users.class);
            String message = "\"A new sticker has been received from " + currentUser.getName() + ".";
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(otherUserUid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users otherUser = snapshot.getValue(Users.class);
                            NotificationSender notificationSender = new NotificationSender();
                            notificationSender.sendNotification(otherUser.getFcmToken(), title, message, stickerPath);
                            databaseReference.removeEventListener(this);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
        }
    }
}