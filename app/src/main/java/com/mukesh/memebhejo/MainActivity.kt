package com.mukesh.memebhejo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

var url: String ?=null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "DEVELOPED BY MUKESH KUMAR", Toast.LENGTH_LONG).show()
        loadMeme()
    }

    private fun loadMeme(){             //fumction to load meme to imageview
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)    //set new request on a queue and executed fifo
        url = "https://meme-api.herokuapp.com/gimme"   //apporach the api url

    // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Method.GET, url,null, //create a json obj and get a json object from url and give back null to api
            Response.Listener { response ->
                val url= response.getString("url")   //name of thing to bring
                Glide.with(this).load(url).into(memeImage)   //glide is for to load inahe into image view
            },
            Response.ErrorListener {
            })
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
    fun shareMeme(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Check this out $url")
        val chooser=Intent.createChooser(intent,"shere this using.....")
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        loadMeme()
    }
}