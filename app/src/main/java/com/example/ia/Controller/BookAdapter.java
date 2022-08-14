package com.example.ia.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.Modal.Medicine.Med;
import com.example.ia.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder>{

    ArrayList<Med> mData;
    OnNoteListener onNoteListener;

    private OnNoteListener mNoteListener;

    public BookAdapter(ArrayList<Med> medList, OnNoteListener onNoteListener) {
        this.mData = medList;
        this.onNoteListener= onNoteListener;
        this.mNoteListener = onNoteListener;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);

        BookViewHolder holder = new BookViewHolder(myView, onNoteListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.nameText.setText(""+mData.get(position).getMedName());
        holder.dateText.setText(""+mData.get(position).getMaExpDate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
