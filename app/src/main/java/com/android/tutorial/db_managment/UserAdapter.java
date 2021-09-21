package com.android.tutorial.db_managment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends ListAdapter<UserAndRole, UserAdapter.UserHolder> {

    private OnItemClickListener listener;

    public UserAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<UserAndRole> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserAndRole>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserAndRole oldItem, @NonNull UserAndRole newItem) {
            return oldItem.getUser().getId() == newItem.getUser().getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserAndRole oldItem, @NonNull UserAndRole newItem) {
            return oldItem.getUser().getName().equals(newItem.getUser().getName()) &&
                    oldItem.getUser().getEmail().equals(newItem.getUser().getEmail()) &&
                    oldItem.getUser().getPhone().equals(newItem.getUser().getPhone()) &&
                    oldItem.getRole().getName().equals(newItem.getRole().getName());
         }
    };

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        UserAndRole currentUser = getItem(position);
        holder.textViewName.setText(currentUser.getUser().getName());
        holder.textViewEmail.setText(currentUser.getUser().getEmail());
        holder.textViewPhone.setText(currentUser.getUser().getPhone());
        holder.textViewRole.setText(currentUser.getRole().getName());
    }



    public UserAndRole getUserAt(int position) {
        return getItem(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewEmail;
        private TextView textViewPhone;
        private TextView textViewRole;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
            textViewPhone = itemView.findViewById(R.id.text_view_phone);
            textViewRole = itemView.findViewById(R.id.text_view_role);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position =getAdapterPosition();
                    if ( listener !=null && position !=RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(UserAndRole user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
