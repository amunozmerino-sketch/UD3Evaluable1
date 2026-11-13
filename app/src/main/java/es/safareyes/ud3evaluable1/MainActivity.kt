package es.safareyes.ud3evaluable1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.safareyes.ud3evaluable1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnEj1.setOnClickListener {
            val comprobarUrl = Intent(this, Ej1Activity::class.java)
            startActivity(comprobarUrl)
        }

        binding.btnEj2.setOnClickListener {
            val navegarConDelay = Intent(this, Ej2Activity::class.java)
            startActivity(navegarConDelay)
        }

        binding.btnEj3.setOnClickListener {
            val linearLayouts = Intent(this, Ej3Activity::class.java)
            startActivity(linearLayouts)
        }

        binding.btnEj4.setOnClickListener {
            val frameLayout = Intent(this, Ej4Activity::class.java)
            startActivity(frameLayout)
        }

        binding.btnEj5.setOnClickListener {
            val calculadora = Intent(this, Ej5Activity::class.java)
            startActivity(calculadora)
        }

        binding.btnEj6.setOnClickListener {
            val perfil = Intent(this, Ej6Activity::class.java)
            startActivity(perfil)
        }
    }
}