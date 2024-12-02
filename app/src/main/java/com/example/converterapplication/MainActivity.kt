package com.example.converterapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinner:Spinner
    private lateinit var inputValue:EditText
    private lateinit var convertButton:Button
    private lateinit var resultLabel:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference UI elements
        spinner = findViewById<Spinner>(R.id.spnOption)
        inputValue = findViewById<EditText>(R.id.txtValue)
        convertButton = findViewById<Button>(R.id.btnConvert)
        resultLabel = findViewById<TextView>(R.id.lblResult)

        // Spinner options
        val options = arrayOf(
            "Distance(Kilometers to meters)",
            "Distance(Meters to kilometers)",
            "Weight(Kilograms to grams)",
            "Weight(Grams to kilograms)",
            "Temperature(Celsius to Fahrenheit)",
            "Temperature(Fahrenheit to Celsius)"
        )

        // Set up the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Handle button click
        convertButton.setOnClickListener {
            val selectedOption = spinner.selectedItem.toString()
            val input = inputValue.text.toString()

            if (input.isEmpty()) {
                resultLabel.text = "Please enter a value."
                return@setOnClickListener
            }

            val inputValue = input.toDoubleOrNull()
            if (inputValue == null) {
                resultLabel.text = "Invalid input. Enter a numeric value."
                return@setOnClickListener
            }

            val (result, unit) = when (selectedOption) {
                "Distance(Kilometers to meters)" -> inputValue * 1000 to "meters"
                "Distance(Meters to kilometers)" -> inputValue / 1000 to "kilometers"
                "Weight(Kilograms to grams)" -> inputValue * 1000 to "grams"
                "Weight(Grams to kilograms)" -> inputValue / 1000 to "kilograms"
                "Temperature(Celsius to Fahrenheit)" -> (inputValue * 9 / 5) + 32 to "°F"
                "Temperature(Fahrenheit to Celsius)" -> (inputValue - 32) * 5 / 9 to "°C"
                else -> null to null
            }

            if (result != null && unit != null) {
                resultLabel.text = "%.2f %s".format(result, unit)
            } else {
                resultLabel.text = "Conversion failed."
            }
        }
    }
}