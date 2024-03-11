package com.levp.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class CalculadoraImcActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var pesoActual: Int = 50
    private var edadActual: Int = 20
    private var alturaActual: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvAltura: TextView
    private lateinit var rsAltura: RangeSlider
    private lateinit var fabMas: FloatingActionButton
    private lateinit var fabMenos: FloatingActionButton
    private lateinit var tvPeso: TextView
    private lateinit var fabMasEdad: FloatingActionButton
    private lateinit var fabMenosEdad: FloatingActionButton
    private lateinit var tvEdad: TextView
    private lateinit var btnCalcular: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_imc)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.cvMale)
        viewFemale = findViewById(R.id.cvFemale)
        tvAltura = findViewById(R.id.tvAltura)
        rsAltura = findViewById(R.id.rsAltura)
        fabMas = findViewById(R.id.fabMas)
        fabMenos = findViewById(R.id.fabMenos)
        tvPeso = findViewById(R.id.tvPeso)
        fabMasEdad = findViewById(R.id.fabMasEdad)
        fabMenosEdad = findViewById(R.id.fabMenosEdad)
        tvEdad = findViewById(R.id.tvEdad)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsAltura.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            alturaActual = df.format(value).toInt()
            tvAltura.text = "$alturaActual cm"
        }

        fabMas.setOnClickListener {
                pesoActual += 1
                setPeso()
        }

        fabMenos.setOnClickListener {
        if (pesoActual >= 1) {
            pesoActual -= 1
            setPeso()
        }
        }

        fabMasEdad.setOnClickListener {
            edadActual += 1
            setEdad()
        }

        fabMenosEdad.setOnClickListener {
            if (edadActual >= 1){
                edadActual -= 1
                setEdad()
            }
        }

        btnCalcular.setOnClickListener {
            val result = calcularIMC()
            navigateToResult(result)
        }
    }

    private fun setPeso(){
        tvPeso.text = pesoActual.toString()
    }

    private fun setEdad() {
        tvEdad.text = edadActual.toString()
    }

    private fun calcularIMC():Double{
        val df = DecimalFormat("#.##")
        val imc = pesoActual / ( alturaActual.toDouble()/100 * alturaActual.toDouble()/100)
        return df.format(imc).toDouble()
//        Log.i("manoloDev", "$result")
    }

    private fun navigateToResult(result:Double){
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(isFemaleSelected))
    }

    private fun getBackGroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.colorPrimary
        } else {
            R.color.colorPrimaryLight
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setPeso()
        setEdad()
    }
}

