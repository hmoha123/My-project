package com.example.androidlabs

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

abstract class BaseActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            android.R.string.ok,
            android.R.string.cancel
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
    }

    fun loadActivityLayout(layoutResId: Int) {
        val frame = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(layoutResId, frame, true)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                if (this !is MainActivity) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }

            R.id.nav_dad_joke -> {
                if (this !is DadJoke) {
                    startActivity(Intent(this, DadJoke::class.java))
                }
            }

            R.id.nav_exit -> {
                finishAffinity()
            }
        }

        drawerLayout.closeDrawers()
        return true
    }
}