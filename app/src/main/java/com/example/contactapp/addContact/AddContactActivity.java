package com.example.contactapp.addContact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityAddContactBinding;
import com.example.contactapp.home.MainActivity;
import com.example.contactapp.models.ModelContact;

public class AddContactActivity extends AppCompatActivity {

    private AddContactViewModel viewModel;
    ActivityAddContactBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AddContactViewModel.class);



        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = binding.name.getText().toString().trim();
                String phone = binding.phone.getText().toString().trim();
                validate(name,phone);



            }
        });



    }

    private void validate(String name, String phone) {
        if(name.isEmpty()){
            binding.name.setError("required");
        }else if (phone.isEmpty()){
            binding.phone.setError("required");
        } else if (phone.length()<11||phone.length()>12) {
            binding.phone.setError("must be 11 num ");
        }else {

            viewModel.addContact(new ModelContact(name, phone));
            viewModel.setTestString(new SuccessMessage() {
                @Override
                public void onClickRoot(String test) {
                    Toast.makeText(AddContactActivity.this, test, Toast.LENGTH_SHORT).show();
                }
            });

            clear();
            startActivity(new Intent(AddContactActivity.this, MainActivity.class));
            finish();
        }
    }


    private void clear() {
        binding.phone.setText("");
        binding.name.setText("");
    }


}