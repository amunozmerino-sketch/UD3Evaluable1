package es.safareyes.ud3evaluable1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.safareyes.ud3evaluable1.databinding.ActivityEj3Binding

class Ej3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEj3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEj3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.act1.setOnClickListener {
            val intent = Intent(this, LL1Activity::class.java)
            startActivity(intent)
        }

        binding.act2.setOnClickListener {
            val intent = Intent(this, LL2Activity::class.java)
            startActivity(intent)
        }

        binding.act3.setOnClickListener {
            val intent = Intent(this, LL3Activity::class.java)
            startActivity(intent)
        }

        binding.volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}