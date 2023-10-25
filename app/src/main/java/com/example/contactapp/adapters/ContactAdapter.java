package com.example.contactapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.databinding.ContactItemBinding;
import com.example.contactapp.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Holder> {

    private List<ModelContact> list;

    OnItemClick onItemClicked;



    public void setOnItemClicked(OnItemClick onItemClicked) {
        this.onItemClicked = onItemClicked;
    }
    public void setList(List<ModelContact> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemBinding binding = ContactItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.bind(list.get(position).getName() , list.get(position).getPhone()     );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        ContactItemBinding binding;

        public Holder(ContactItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicked.onIconClicked(list.get(getLayoutPosition()));
                }
            });
            binding.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicked.editContact(list.get(getLayoutPosition()));
                }
            });

            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicked.deleteItem(getLayoutPosition());
                }
            });


        }

        public void bind(String name , String pass ){
            binding.name.setText(name);
            binding.phone.setText(pass);
        }


    }

    public interface OnItemClick{
        public void onIconClicked(ModelContact modelContact);
        public void editContact(ModelContact modelContact);
        public void deleteItem(int pos);


    }



}
