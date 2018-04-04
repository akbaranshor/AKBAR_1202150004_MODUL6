package com.example.android.akbar_1202150004_modul6;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abay on 4/1/2018.
 */

public class PotoSaya extends Fragment {
    // Deklarasi variabel
    DatabaseReference databaseReference;
    List<Post> list;
    MyAdapter adapter;
    RecyclerView rv;
    FirebaseAuth firebaseAuth;

    // Konstruktor kosong
    public PotoSaya() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inisiasi variabel
        databaseReference = FirebaseDatabase.getInstance().getReference("post");
        list = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        // Menempelkannya ke dengan fragment blank
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        // inisiasi recycleview
        rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    if (post.getEmail().equals(firebaseAuth.getCurrentUser().getEmail())) {
                        // Menambahkannya ke dalam list
                        list.add(post);
                    }
                }
                // Memasukkan kedalam adapter
                adapter = new MyAdapter(getContext(), list);
                // Setting adapter pada recyclerview
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Set ke layout
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        return rootView;
    }
}

