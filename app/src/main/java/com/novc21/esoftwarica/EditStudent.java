package com.novc21.esoftwarica;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditStudent extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    EditText etReName, etReAge, etReAddress;
    RadioGroup rgReGender;
    RadioButton rbReMale, rbReFemale, rbReOther;
    Button btnReSave;
    String name, address, gender;
    int age;
    public static int index;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

        etReName = findViewById(R.id.etReName);
        etReAge = findViewById(R.id.etReAge);
        etReAddress = findViewById(R.id.etReAddress);
        btnReSave = findViewById(R.id.btnReSave);
        rgReGender = findViewById(R.id.rgReGender);
        rbReMale = findViewById(R.id.rbReMale);
        rbReOther = findViewById(R.id.rbReOther);
        rbReFemale = findViewById(R.id.rbReFemale);
        etReName.setText(MainActivity.studentsList.get(index).getName());
        etReAge.setText(MainActivity.studentsList.get(index).getAge() + "");
        etReAddress.setText(MainActivity.studentsList.get(index).getAddress());
        gender = MainActivity.studentsList.get(index).getGender();
        if (gender == "male") {
            rbReMale.setChecked(true);
            rbReFemale.setChecked(false);
            rbReOther.setChecked(false);
        } else if (gender == "female") {
            rbReMale.setChecked(false);
            rbReFemale.setChecked(true);
            rbReOther.setChecked(false);
        }
        if (gender == "other") {
            rbReMale.setChecked(false);
            rbReFemale.setChecked(false);
            rbReOther.setChecked(true);
        }
        rgReGender.setOnCheckedChangeListener(this);
        btnReSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    name = etReName.getText().toString();
                    address = etReAddress.getText().toString();
                    age = Integer.parseInt(etReAge.getText().toString());
                    try {
                        MainActivity.studentsList.get(index).setName(name);
                        MainActivity.studentsList.get(index).setAddress(address);
                        MainActivity.studentsList.get(index).setAge(age);
                        MainActivity.studentsList.get(index).setGender(gender);
                        Toast.makeText(EditStudent.this, "Student updated", Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(EditStudent.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean validate() {
        if (TextUtils.isEmpty(etReName.getText())) {
            etReName.setError("Enter full name");
            etReName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etReAge.getText())) {
            etReAge.setError("Enter the age");
            etReAge.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etReAddress.getText())) {
            etReAddress.setError("Enter the address");
            etReAddress.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(gender)) {
            Toast.makeText(EditStudent.this, "Select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbReMale) {
            gender = "male";
        }
        if (checkedId == R.id.rbReFemale) {
            gender = "female";
        }
        if (checkedId == R.id.rbReOther) {
            gender = "other";
        }
    }
}

