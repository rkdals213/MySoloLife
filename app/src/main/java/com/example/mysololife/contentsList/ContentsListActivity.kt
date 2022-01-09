package com.example.mysololife.contentsList

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysololife.R
import com.example.mysololife.utils.FBAuth
import com.example.mysololife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentsListActivity : AppCompatActivity() {

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter: ContentRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val database = Firebase.database

        val items = mutableListOf<ContentModel>()
        val itemKeyList = mutableListOf<String>()
        rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        val category = intent.getStringExtra("category")
        var myRef: DatabaseReference = database.getReference("contents")

        if (category == "category1") {
            myRef = database.getReference("contents")
        } else if (category == "category2") {
            myRef = database.getReference("contents2")
        }

        val postListener = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity", items.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        myRef.addValueEventListener(postListener)
        val rv: RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()
    }

    private fun getBookmarkData() {
        val postListener = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                bookmarkIdList.clear()
                
                for (dataModel in dataSnapshot.children) {
                    bookmarkIdList.add(dataModel.key.toString())
                }
                Log.d("ContentListActivity", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.bookmarkRef
            .child(FBAuth.getUid())
            .addValueEventListener(postListener)
    }
}