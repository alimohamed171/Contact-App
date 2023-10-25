package com.example.contactapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactapp.adapters.ContactAdapter;
import com.example.contactapp.addContact.AddContactActivity;
import com.example.contactapp.addContact.AddContactViewModel;
import com.example.contactapp.databinding.ActivityMainBinding;
import com.example.contactapp.editContact.EditActivity;
import com.example.contactapp.models.ModelContact;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ContactAdapter adapter = new ContactAdapter();

    List<ModelContact> list;
    HomeViewModel viewModel;


    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);


        viewModel.getContact();





        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, AddContactActivity.class));


            }
        });


        adapter.setOnItemClicked(new ContactAdapter.OnItemClick() {

            @Override
            public void onIconClicked(ModelContact modelContact) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+modelContact.getPhone()));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                  startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                }

            }

            @Override
            public void editContact(ModelContact modelContact) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);

                intent.putExtra("modelContact", modelContact);
                startActivity(intent);
            }

            @Override
            public void deleteItem(int pos) {
                viewModel.delete(list.get(pos).getId());
                list.remove(pos);
                adapter.notifyItemRemoved(pos);

            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();

        viewModel.contactList.observe(this, new Observer<List<ModelContact>>() {
            @Override
            public void onChanged(List<ModelContact> modelContacts) {
                list = modelContacts;
                adapter.setList(list);
                binding.recyclerView.setAdapter(adapter);
            }

        });

    }









}