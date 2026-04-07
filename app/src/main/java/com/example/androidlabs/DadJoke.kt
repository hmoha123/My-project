package com.example.androidlabs

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class DadJoke : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadActivityLayout(R.layout.activity_dad_joke_content)
        title = "Dad Joke"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_1 -> {
                Toast.makeText(this, "You clicked on item 1", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.item_2 -> {
                Toast.makeText(this, "You clicked on item 2", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.item_3 -> {
                Toast.makeText(this, "You clicked on item 3", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}