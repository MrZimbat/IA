package com.example.ia.Controller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    protected TextView nameText;
    protected TextView dateText;

    BookAdapter.OnNoteListener onNoteListener;

    public BookViewHolder(@NonNull View itemView, BookAdapter.OnNoteListener onNoteListener) {
        super(itemView);

        nameText = itemView.findViewById(R.id.nameTextView);
        dateText = itemView.findViewById(R.id.dateTextView);
        this.onNoteListener = onNoteListener;

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onNoteListener.onNoteClick(getAdapterPosition());

    }
}
