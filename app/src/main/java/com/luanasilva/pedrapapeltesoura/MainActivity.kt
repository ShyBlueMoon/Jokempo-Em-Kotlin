package com.luanasilva.pedrapapeltesoura

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luanasilva.pedrapapeltesoura.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        jogarJogo()
    }

    private fun jogarJogo() {
        with(binding) {
            btnPedra.setOnClickListener {
                verificarGanhador("pedra")
            }

            btnPapel.setOnClickListener {
                verificarGanhador("papel")
            }

            btnTesoura.setOnClickListener {
                verificarGanhador("tesoura")
            }
        }
    }


    private fun gerarEscolhaAleatoriaApp():String {
        val imagemApp = binding.imageApp
        val opcoes = listOf("pedra", "papel", "tesoura")
        val numeroAleatorio =  Random.nextInt(3)

        imagemApp.setImageResource(R.drawable.padrao)



        val escolhaApp = opcoes[numeroAleatorio]



        if (escolhaApp == "pedra") {
            imagemApp.setImageResource(R.drawable.pedra)

        }
        else if (escolhaApp == "papel") {
            imagemApp.setImageResource(R.drawable.papel)

        }
        else if (escolhaApp == "tesoura") {

            imagemApp.setImageResource(R.drawable.tesoura)
        }

        return escolhaApp

    }

    private fun verificarGanhador(escolhaUsuario: String) {
        val escolhaApp = gerarEscolhaAleatoriaApp()
        val textoResultado = binding.viewResultado

        if (
            escolhaApp == "pedra" && escolhaUsuario == "tesoura"
            || escolhaApp == "tesoura" && escolhaUsuario == "papel"
            || escolhaApp == "papel" && escolhaUsuario == "pedra"
        ) {
            textoResultado.setText(R.string.voce_perdeu)
        } else if(
            escolhaUsuario == "pedra" && escolhaApp == "tesoura"
            || escolhaUsuario == "tesoura" && escolhaApp == "papel"
            || escolhaUsuario == "papel" && escolhaApp == "pedra"
        ) {
            textoResultado.setText(R.string.voce_ganhou)
        } else {
            textoResultado.setText(R.string.empate)
        }

    }

}