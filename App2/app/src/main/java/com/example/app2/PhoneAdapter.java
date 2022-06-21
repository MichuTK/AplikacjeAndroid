package com.example.app2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder> {

    private List<Phone> phones = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_item, parent, false);
        return new PhoneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
        Phone currentPhone = phones.get(position);
        holder.textViewProducent.setText(currentPhone.getProducent());
        holder.textViewModel.setText(currentPhone.getModel());
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
        notifyDataSetChanged();
    }

    public Phone getPhoneAt(int position) {
        return phones.get(position);
    }

    class PhoneHolder extends RecyclerView.ViewHolder {
        private TextView textViewProducent;
        private TextView textViewModel;

        public PhoneHolder(@NonNull View itemView) {
            super(itemView);
            textViewProducent = itemView.findViewById(R.id.producent);
            textViewModel = itemView.findViewById(R.id.model);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(phones.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Phone phone);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
