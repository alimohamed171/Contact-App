package com.example.contactapp.editContact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactapp.R;
import com.example.contactapp.addContact.AddContactActivity;
import com.example.contactapp.addContact.SuccessMessage;
import com.example.contactapp.databinding.ActivityEditBinding;
import com.example.contactapp.home.MainActivity;
import com.example.contactapp.models.ModelContact;

public class EditActivity extends AppCompatActivity {

    ModelContact modelContact;
    EditViewModel viewModel = new EditViewModel();
    ActivityEditBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         modelContact = (ModelContact) getIntent()
                .getSerializableExtra("modelContact");


         binding.name.setText(modelContact.getName());
         binding.phone.setText(modelContact.getPhone().toString());

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


           modelContact.setName(name);
           modelContact.setPhone(phone);

           viewModel.editContact(modelContact);

            clear();
            startActivity(new Intent(EditActivity.this, MainActivity.class));
            finish();
        }
    }


    private void clear() {
        binding.phone.setText("");
        binding.name.setText("");
    }


}