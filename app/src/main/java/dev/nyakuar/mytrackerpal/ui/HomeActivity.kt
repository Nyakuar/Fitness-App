package dev.nyakuar.mytrackerpal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.Lavyne.mytrackerpal.R
import dev.Lavyne.mytrackerpal.databinding.ActivityHomeBinding
import dev.nyak.mytrackerpal.ui.PlanFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    fun setupBottomNav(){
        binding.bottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, HomeFragment()).commit()
                    true
                }
                R.id.plan ->{
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fcvHome,
                            PlanFragment ()
                        ).commit()
                        true
                }
                R.id.track ->{
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fcvHome,
                            TrackFragment()
                        ).commit()
                        true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }

    }
}
