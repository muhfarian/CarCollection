package com.example.carcollection

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCar: RecyclerView
    private val list = ArrayList<Car>()
    private lateinit var about: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCar = findViewById(R.id.rv_cars)
        rvCar.setHasFixedSize(true)

        list.addAll(getListCars())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    @SuppressLint("Recycle")
    private fun getListCars(): ArrayList<Car> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCar = ArrayList<Car>()
        for (i in dataName.indices) {
            val car = Car(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listCar.add(car)
        }
        return listCar
    }
    private fun showRecyclerList(){
        rvCar.layoutManager = LinearLayoutManager(this)
        val listCarAdapter = ListCarAdapter(list)
        rvCar.adapter = listCarAdapter

        listCarAdapter.setOnItemClickCallback(object : ListCarAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Car) {

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("selected_car", data)
                startActivity(intent)
            }
        })
    }

}