package com.example.preferencedatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.preferencedatastore.Ui.MainViewModel
import com.example.preferencedatastore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        writeToLocal()

        lifecycleScope.launchWhenStarted {
            mainViewModel.readToLocal.collect {
                binding.preferenceValue.text=it
            }
        }
    }

    private fun writeToLocal() {
        binding.apply {
            ok.setOnClickListener {
                if(!TextUtils.isEmpty(name.text.toString()))
                {
                    mainViewModel.writeToLocal(name = name.text.toString())

                }else
                {
                    Toast.makeText(this@MainActivity,"Fill the Fields",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}