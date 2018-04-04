package com.example.android.akbar_1202150004_modul6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    // Deklarasi variabel
    FirebaseDatabase firebaseDatabase;
    TextView email, postBody, postTitle;
    EditText commentEdit;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase;
    RecyclerView rv;
    List<Comment> list;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Inisiasi variabel
        postBody = findViewById(R.id.post_body);
        postTitle = findViewById(R.id.post_title);
        email = findViewById(R.id.tv_text);
        firebaseAuth = FirebaseAuth.getInstance();
        commentEdit = findViewById(R.id.field_comment_text);
        firebaseDatabase = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        rv = findViewById(R.id.rec_comment);

        // Menampilkan post ketika masuk kedetail post
        DatabaseReference mFirebase = firebaseDatabase
                .getReference("post")
                .child(getIntent().getStringExtra("id"));
        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Post changedPost = dataSnapshot.getValue(Post.class);
                // Meensetting pada variabel yang sudah dideklarasi
                postTitle.setText(changedPost.getTitle());
                postBody.setText(changedPost.getDescription());
                email.setText(changedPost.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Maasuk ke key Comment
        mDatabase = firebaseDatabase.getReference("comment").child(getIntent().getStringExtra("id"));

        /*
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Comment com = postSnapshot.getValue(Comment.class);
                    list.add(com);
                }
                adapter = new CommentAdapter(DetailActivity.this, list);

                adapter.notifyDataSetChanged();
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */

        // Melihat isi dari child dan mengaturnya ke dalam komentar
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Comment comment = dataSnapshot.getValue(Comment.class);
                // Menambahkan ke list
                list.add(comment);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Comment newComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String commentKey = dataSnapshot.getKey();

                int commentIn = list.indexOf(commentKey);
                // jika ada yang sama, lalu hapus
                if (commentIn > -1) {
                    // hapus list
                    list.remove(commentIn);
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Comment movedComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        // Memanggil method dan menerapkan ChildEventListener
        mDatabase.addChildEventListener(childEventListener);
        // Masukkkan nilai pada adapter
        adapter = new CommentAdapter(DetailActivity.this, list);

        adapter.notifyDataSetChanged();
        // Setting adapter pada RecyclerView
        rv.setAdapter(adapter);
        // Menempatkannya pada layout
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    public void comment(View view) {
        // Menggunakan hashmap
        Map<String, String> userData = new HashMap<String, String>();
        // Memetakkan Key dan Value agar mudah pada saat displaynya
        userData.put("email", firebaseAuth.getCurrentUser().getEmail());
        userData.put("comment", commentEdit.getText().toString());
        // Menaruhnya ke dalam Database
        mDatabase.push().setValue(userData);
        // Meampilkan pesan toast
        Toast.makeText(this, "Berhasil comment", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
