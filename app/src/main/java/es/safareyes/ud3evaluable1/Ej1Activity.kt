package es.safareyes.ud3evaluable1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.safareyes.ud3evaluable1.databinding.ActivityEj1Binding
import androidx.core.net.toUri

class Ej1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEj1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEj1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ej1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.volver.setOnClickListener {
            val volverAMain = Intent(this, MainActivity::class.java)
            startActivity(volverAMain)
        }

        binding.comprobar.setOnClickListener {
            val url = binding.entradaTexto.text.toString()

            if (Patterns.WEB_URL.matcher(url).matches() && url.isNotBlank()) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this, "No se detecta ningún navegador.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Introduce una URL válida.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}