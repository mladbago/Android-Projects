package com.example.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.ln

class MainActivity : AppCompatActivity() {
    lateinit var textOnCalc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textOnCalc = findViewById(R.id.calcText)
    }

    fun onNumber(view: View) {
        textOnCalc.append((view as Button).text)
    }
    fun onDot(view: View) {
        textOnCalc.append((view as Button).text)
    }
    fun onOperator(view: View) {
        textOnCalc.append((view as Button).text)
    }
    fun onClear(view: View) {
        textOnCalc.text = ""
    }
    fun onUnaryMinus(view: View) {
        var onScreen = textOnCalc.text.toString()
        val calc = ExpressionBuilder("$onScreen*(-1)").build()
        val result = calc.evaluate()
        textOnCalc.text = textFormatter(result.toString())
    }
    fun onLog(view: View) {
        var onScreen = textOnCalc.text.toString()
        val calc = ln(onScreen.toDouble());
        textOnCalc.text = calc.toString()
    }
    fun onEquals(view: View) {
        var onScreen = textOnCalc.text.toString()
        val findPercent : Int = onScreen.indexOf("%", 0)
        onScreen = onScreen.replace('x', '*')
        if (findPercent >= 0) {
            onScreen = onScreen.replace("%", "/100*")
//            val percent = ExpressionBuilder(onScreen).build()
//            onScreen = percent.evaluate().toString()
        }
        val calc = ExpressionBuilder(onScreen).build()
        val result: Double = calc.evaluate()
        textOnCalc.text = textFormatter(result.toString())
    }

    private fun textFormatter(stringToChange: String) : String {
        val replacer : String = stringToChange.replace('x', '*')
        if (replacer[0] == '-' && replacer[1] == '0')
            return "0"
        if (replacer[replacer.length - 2] == '.' && replacer[replacer.length - 1] == '0') {
            return replacer.substring(0, replacer.length - 2)
        }
        return replacer;
    }

}