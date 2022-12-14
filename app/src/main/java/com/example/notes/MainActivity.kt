package com.example.notes

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.model.Note
import com.example.notes.model.NotesListener
import com.example.notes.model.NotesService
import com.example.notes.screens.AboutFragment
import com.example.notes.screens.NoteDetailsFragment
import com.example.notes.screens.NotesListFragment

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NotesListFragment())
                .commit()
        }

        binding.apply {
            navigationView.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.action_drawer_about -> {
                        supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragmentContainer, AboutFragment())
                            .commit()
//                        Toast.makeText(this@MainActivity, "О приложениии", Toast.LENGTH_SHORT).show()
                    }
                    R.id.action_drawer_setting -> {
                        Toast.makeText(this@MainActivity, "Данная функция пока не доступна", Toast.LENGTH_SHORT).show()
                    }
                    R.id.action_drawer_exit -> {
                        createDialog()
//                        finish()
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    override fun showDetails(note: Note) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, NoteDetailsFragment.newInstance(note.id))
            .commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun toast(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }

    private fun createDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выход из приложения")
        builder.setMessage("Вы уверены что хотите выйти?")
        builder.setPositiveButton("Выйти",DialogInterface.OnClickListener { dialog, which -> finish() })
        builder.setNegativeButton("Остаться",DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(this, "Вы решили помучать себя еще немного", Toast.LENGTH_SHORT).show()
        })
        builder.show()
    }
}