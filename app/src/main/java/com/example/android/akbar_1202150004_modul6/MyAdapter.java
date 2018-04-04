package com.example.android.akbar_1202150004_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Abay on 4/2/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // Deklarasi variabel
    Context context;
    List<Post> MainImageUploadInfoList;

    /**
     * Konstruktor
     *
     * @param context context dari aktivitas
     * @param TempList List yang nanti akan dipassing
     */
    public MyAdapter(Context context, List<Post> TempList) {
        // Inisiasi pada variabel
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }

    /**
     * Menempelkan card_item pada layout
     *
     * @param parent
     * @param viewType
     * @return viewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Mnsetting pada variabel pada card
     *
     * @param holder ViewHolder dari inner class di bawah
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post UploadInfo = MainImageUploadInfoList.get(position);
        // Setting pada posisi tertentu
        holder.imageNameTextView.setText(UploadInfo.getEmail());
        holder.postTitle.setText(UploadInfo.getTitle());
        holder.postBody.setText(UploadInfo.getDescription());

    }

    /**
     * Mengembalikkan besar list
     *
     * @return besar list
     */
    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    /**
     * Inner class
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Deklarasi variabel
        public TextView imageNameTextView, postTitle, postBody;

        /**
         * Konstruktor yang harus mewarisi sifat ViewHolder
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            // Inisiasi variabel
            imageNameTextView = (TextView) itemView.findViewById(R.id.tv_text);
            postTitle = itemView.findViewById(R.id.post_title);
            postBody = itemView.findViewById(R.id.post_body);
            // Mengaktifkan onClickListener
            itemView.setOnClickListener(this);
        }

        /**
         * Pindah ke detil, jika mengclick pada list
         *
         * @param view
         */
        @Override
        public void onClick(View view) {
            Post current = MainImageUploadInfoList.get(getAdapterPosition());
            // Set intent
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", current.getId());
            // Pindah aktivitas
            context.startActivity(intent);
        }
    }

}
