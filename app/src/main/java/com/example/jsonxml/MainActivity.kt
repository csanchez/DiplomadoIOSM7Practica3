package com.example.jsonxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.jsonxml.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var volleyApi: VolleyApi
    private lateinit var url:String
    private val ipPuerto="132.248.234.99:8080"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        volleyApi = VolleyApi(this)
        binding.search.setOnClickListener(){
            binding.outText.text=""
            url="https://www.google.es/search?q="+URLEncoder.encode(
                binding.searchText.text.toString(), "UTF-8"
            )
            buscar()
        }
    }


    private fun buscar(){
        var stringRequest=object: StringRequest(
            Request.Method.GET,url,Response.Listener<String>{response->
                binding.outText.text=response
            },Response.ErrorListener { binding.outText.text="No se encuentra la informaci[on, revice si conexi[on" }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers=HashMap<String,String>()
                headers["User-Agent"]="Mozilla/5.0 (Windows NT 6.1)"
                return headers
            }
        }
        volleyApi.add(stringRequest)
    }
}