package gt.tribal.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.tabs.TabLayout
import gt.tribal.app.R
import gt.tribal.app.databinding.ActivityMainBinding
import gt.tribal.app.ui.fragment.FavoritesFragment
import gt.tribal.app.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(
                                R.id.main_container,
                                HomeFragment()
                            )
                            .commit()
                    }
                    1->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(
                                R.id.main_container,
                                FavoritesFragment()
                            )
                            .commit()
                    }
                }

            }
        })


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    HomeFragment()
                )
                .commit()
        }

    }
}
