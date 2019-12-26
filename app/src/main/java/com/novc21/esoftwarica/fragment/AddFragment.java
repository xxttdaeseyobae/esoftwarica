package com.novc21.esoftwarica.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.novc21.esoftwarica.MainActivity;
import com.novc21.esoftwarica.R;
import com.novc21.esoftwarica.adapter.Students;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    EditText etName, etAge, etAddress;
    RadioGroup rgGender;
    Button btnSave;
    String name, address, gender = "male";
    int age;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etAddress = view.findViewById(R.id.etAddress);
        rgGender = view.findViewById(R.id.rgGender);
        btnSave = view.findViewById(R.id.btnSave);
        rgGender.setOnCheckedChangeListener(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    name = etName.getText().toString();
                    address = etAddress.getText().toString();
                    age = Integer.parseInt(etAge.getText().toString());
                    MainActivity.studentsList.add(new Students(name,gender,address,age));
                    Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbMale) {
            gender = "male";
        }
        if (checkedId == R.id.rbFemale) {
            gender = "female";
        }
        if (checkedId == R.id.rbOther) {
            gender = "other";
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etName.getText())) {
            etName.setError("Enter full name");
            etName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etAge.getText())) {
            etAge.setError("Enter the age");
            etAge.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etAddress.getText())) {
            etAddress.setError("Enter the address");
            etAddress.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(gender)) {
            Toast.makeText(getContext(), "Select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
