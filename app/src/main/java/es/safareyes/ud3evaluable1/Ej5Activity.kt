package es.safareyes.ud3evaluable1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.safareyes.ud3evaluable1.databinding.ActivityEj5Binding

class Ej5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEj5Binding
    private var numeroActual = ""
    private val numeros = mutableListOf<Double>()
    private val operadores = mutableListOf<Char>()
    private var expresion = ""   // lo que muestra el TextView

    private fun pulsarDigito(d: String) {
        numeroActual += d
        expresion += d
        binding.calculo.text = expresion
    }

    private fun pulsarComa() {
        if (numeroActual.contains(",") || numeroActual.isEmpty()) return

        numeroActual += ","
        expresion += ","

        binding.calculo.text = expresion
    }

    private fun pulsarOperador(op: Char) {
        if (numeroActual.isEmpty() || numeroActual.endsWith(",")) return  // número inválido, evitar doble coma y operador
        numeros.add(numeroActual.replace(',', '.').toDouble())  // Kotlin necesita el punto para transformar a Double
        operadores.add(op)
        numeroActual = ""
        expresion += op
        binding.calculo.text = expresion
    }

    private fun restablecer() {
        numeroActual = ""
        numeros.clear()
        operadores.clear()
        expresion = ""
        binding.calculo.text = ""
    }

    private fun pulsarIgual() {
        if (numeroActual.isEmpty() || numeroActual.endsWith(",")) {
            binding.calculo.text = resources.getString(R.string.error)
            return
        }
        numeros.add(numeroActual.replace(',', '.').toDouble())

        val resultado = calcular(numeros, operadores)
        if (resultado == null) {  // división entre 0
            binding.calculo.text = resources.getString(R.string.error)
            restablecerSinPantalla()
            return
        }

        // mostrar sin ".0" si es entero y con coma decimal
        val texto = if (resultado % 1 == 0.0) resultado.toLong().toString()
        else resultado.toString().replace('.', ',')

        binding.calculo.text = texto

        // dejamos el resultado como punto de partida para seguir operando
        numeros.clear()
        operadores.clear()
        numeroActual = texto
        expresion = texto
    }

    private fun restablecerSinPantalla() {
        numeroActual = ""
        numeros.clear()
        operadores.clear()
        expresion = ""
    }

    private fun calcular(nums: MutableList<Double>, ops: MutableList<Char>): Double? {
        // 1ª pasada: * y / (de izquierda a derecha)
        var i = 0
        while (i < ops.size) {
            if (ops[i] == '*' || ops[i] == '/') {
                if (ops[i] == '/' && nums[i + 1] == 0.0) return null
                val r = if (ops[i] == '*') nums[i] * nums[i + 1] else nums[i] / nums[i + 1]
                nums[i] = r          // el resultado sustituye al primer número
                nums.removeAt(i + 1) // quitamos el segundo número
                ops.removeAt(i)      // quitamos el operador ya resuelto
                // no incrementamos i: ahora en esa posición hay otro operador
            } else {
                i++
            }
        }

        // 2ª pasada: + y - (de izquierda a derecha)
        var resultado = nums[0]
        for (j in ops.indices) {
            resultado = if (ops[j] == '+') resultado + nums[j + 1] else resultado - nums[j + 1]
        }
        return resultado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEj5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.num0.setOnClickListener { pulsarDigito("0") }
        binding.num1.setOnClickListener { pulsarDigito("1") }
        binding.num2.setOnClickListener { pulsarDigito("2") }
        binding.num3.setOnClickListener { pulsarDigito("3") }
        binding.num4.setOnClickListener { pulsarDigito("4") }
        binding.num5.setOnClickListener { pulsarDigito("5") }
        binding.num6.setOnClickListener { pulsarDigito("6") }
        binding.num7.setOnClickListener { pulsarDigito("7") }
        binding.num8.setOnClickListener { pulsarDigito("8") }
        binding.num9.setOnClickListener { pulsarDigito("9") }
        binding.sumar.setOnClickListener { pulsarOperador('+') }
        binding.restar.setOnClickListener { pulsarOperador('-') }
        binding.dividir.setOnClickListener { pulsarOperador('/') }
        binding.multi.setOnClickListener { pulsarOperador('*') }
        binding.decimal.setOnClickListener { pulsarComa() }
        binding.igual.setOnClickListener { pulsarIgual() }
        binding.restablecer.setOnClickListener { restablecer() }

        binding.volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}