package com.example.firestoreapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = FirebaseFirestore.getInstance()


button_next.setOnClickListener{
    startActivity(Intent(this,DetailActivity::class.java))
}

        button_save.setOnClickListener(this)
    }

   private fun  validateInputs(name:String, age:String, address:String):Boolean {
        if (name.isEmpty()) {
            edt_name.error = "Name required"
            edt_name.requestFocus()
            return true
        }

        if (age.isEmpty()) {
            edt_age.error = """Brand required"""
            edt_age.requestFocus()
            return true
        }

        if (address.isEmpty()) {
            edt_address.error = "Description required"
            edt_address.requestFocus()
            return true
        }
        return false
   }

    override fun onClick(p0: View?) {
        val name = edt_name.text.toString().trim()
        val age = edt_age.text.toString().trim()
        val add = edt_address.text.toString().trim()
        if (!validateInputs(name, age, add)) {
            pb.visibility = View.VISIBLE

            val dbProducts = db.collection("Detail")

            val detail =  Detail(
                name,
                age,
                add    )

            dbProducts.add(detail)

                .addOnSuccessListener {
                    pb.visibility = View.GONE
                    edt_name.text.clear()
                    edt_age.text.clear()
                    edt_address.text.clear()

                    Toast.makeText(applicationContext, "Product Added", Toast.LENGTH_LONG).show(); }
                .addOnFailureListener { p0 -> Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show(); }


        }

    }
}
