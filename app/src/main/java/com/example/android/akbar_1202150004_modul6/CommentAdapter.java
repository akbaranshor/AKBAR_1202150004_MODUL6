package com.example.android.akbar_1202150004_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.List;

/**
 * Created by Abay on 4/2/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    // Deklarasi variabel
    Context context;
    List<Comment> comments;

    /**
     * Konstruktor
     *
     * @param context Context aktivitas
     * @param comments List yang akan dipassing
     */
    public CommentAdapter(Context context, final List<Comment> comments) {
        // Inisiasi pada variabel
        this.context = context;
        this.comments = comments;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Menempelkan layout pada recycleview
     *
     * @param parent
     * @param viewType
     * @return Memanggil constructor view holder
     */
    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
    }

    /**
     *
     * @param holder Perwujuddan dari kelas ViewHolder
     * @param position posisi pada layout
     */
    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment c = comments.get(position);
        // Set pada card
        holder.email.setText(c.getEmail());
        holder.comment.setText(c.getComment());
    }

    /**
     * Mengembalikkan nilai list
     * @return besar list
     */
    @Override
    public int getItemCount() {
        return comments.size();
    }

    /**
     * Inner class
     */

    class CommentViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi variabel
        TextView email, comment;

        /**
         *
         * @param itemView dari view
         */
        public CommentViewHolder(View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.comment_author);
            comment = itemView.findViewById(R.id.comment_body);
        }
    }
}
