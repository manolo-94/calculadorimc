package com.levp.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.levp.calculadoraimc.CalculadoraImcActivity.Companion.IMC_KEY
import org.w3c.dom.Text

class ResultImcActivity : AppCompatActivity() {

    private lateinit var tvResultado:TextView
    private lateinit var tvDescripcion:TextView
    private lateinit var btnRecalcular:Button
    private lateinit var tvSubTitulo:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)

        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponet()
        initUI(result)
        initListener()

    }

    private fun initListener() {
        btnRecalcular.setOnClickListener { onBackPressed() }
    }

    private fun initComponet(){
        tvSubTitulo = findViewById(R.id.tvSubTitulo)
        tvResultado = findViewById(R.id.tvResultado)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        btnRecalcular = findViewById(R.id.btnRecalcular)
    }

    private fun initUI(result:Double){

        tvResultado.text = result.toString()

        when(result){
            in 0.00 .. 18.50 ->{
                /*Peso bajo*/
                tvSubTitulo.setTextColor(ContextCompat.getColor(this, R.color.colorYellow))
                tvSubTitulo.text = getString(R.string.str_subTituloPesoBajo)
                tvDescripcion.text = getString(R.string.str_descripcionPesoBajo)
            }
            in 18.51 .. 24.99 -> {
                /*Peso normal*/
                tvSubTitulo.setTextColor(ContextCompat.getColor(this,R.color.colorGreen))
                tvSubTitulo.text = getString(R.string.str_subTituloPesoNormal)
                tvDescripcion.text = getString(R.string.str_descripcionPesoNormal)
            }
            in 25.00 .. 29.99 -> {
                /*Sobrepeso*/
                tvSubTitulo.setTextColor(ContextCompat.getColor(this,R.color.colorOrange))
                tvSubTitulo.text = getString(R.string.str_subTituloSobrepeso)
                tvDescripcion.text = getString(R.string.str_descripcionSobrePeso)
            }
            in 30.00 .. 99.00 -> {
                /*Obesidad*/
                tvSubTitulo.setTextColor(ContextCompat.getColor(this,R.color.colorRed))
                tvSubTitulo.text = getString(R.string.str_subTituloObesidad)
                tvDescripcion.text = getString(R.string.str_descripcionObesidad)
            }
            else -> {
                /*Error*/
                tvResultado.text = getString(R.string.str_error).toString()
                tvSubTitulo.text = getString(R.string.str_error).toString()
                tvDescripcion.text = getString(R.string.str_error).toString()
            }
        }

    }

}