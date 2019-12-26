package com.novc21.esoftwarica.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novc21.esoftwarica.EditStudent;
import com.novc21.esoftwarica.R;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>{

    private Context context;
    private List<Students> studentsList;


    public StudentsAdapter(Context context, List<Students> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_students,parent,false);
        return new StudentsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, final int position) {
        final Students students = studentsList.get(position);
        holder.tvName.setText(students.getName());
        holder.tvAge.setText(students.getAge()+"");
        holder.tvAddress.setText(students.getAddress());
        holder.tvGender.setText(students.getGender());
        holder.imgDelete.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_delete_black_24dp));
        holder.imgEdit.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_edit_black_24dp));
        String gender = students.getGender();
        if (gender == "male") {
            holder.imgProfile.setImageResource(R.drawable.male);
        } else if (gender == "female") {
            holder.imgProfile.setImageResource(R.drawable.female);
        } else {
            holder.imgProfile.setImageResource(R.drawable.other);
        }

        holder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is : "+ students.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Students delStudents = studentsList.get(position);
                studentsList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, delStudents + "is removed", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Students edtStudents = studentsList.get(position);
                int index = studentsList.indexOf(edtStudents);
                EditStudent.index = index;
                Intent intent = new Intent(context, EditStudent.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvAge, tvAddress, tvGender;
        ImageView imgProfile,imgDelete,imgEdit;
        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvGender = itemView.findViewById(R.id.tvGender);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
        }
    }



}
