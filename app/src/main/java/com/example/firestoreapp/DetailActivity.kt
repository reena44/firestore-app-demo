package com.example.firestoreapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private var adapter: DetailAdapter? = null
    private var productList: ArrayList<Detail>? = null
    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        this.recyclerview_products!!.setHasFixedSize(true)
        recyclerview_products.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        productList = ArrayList()
        adapter = DetailAdapter(this, productList as ArrayList<Detail>)
        recyclerview_products.adapter = adapter
        db = FirebaseFirestore.getInstance()

        db!!.collection("Detail").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                progressbar.visibility = View.GONE

                if (!queryDocumentSnapshots!!.isEmpty) {
                    val list: List<DocumentSnapshot> = queryDocumentSnapshots.documents
                    for (DocumentSnapshot in list) {
                        val p = DocumentSnapshot.toObject(Detail::class.java)
                        (productList as ArrayList<Detail>).add(p!!)

                    }

                    adapter!!.notifyDataSetChanged()

                }
            }

    }
}
