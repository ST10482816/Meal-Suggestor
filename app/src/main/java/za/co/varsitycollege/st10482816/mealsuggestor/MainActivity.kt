package za.co.varsitycollege.st10482816.mealsuggestor

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//      Buttons
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnReset = findViewById<Button>(R.id.btnReset)
//      Text Fields (Edit & View)
        var txtSuggestionResult = findViewById<TextView>(R.id.txtSuggestionResult)
        var timeOfDay = findViewById<EditText>(R.id.txeTimeOfDay)

        btnSubmit.setOnClickListener{

//          checks if user input is not blank nor whitespaces
            if (timeOfDay.text.isNotBlank()) {
                try {
//              tries to stringify user input and store in timeOfDayText variable
                    var timeOfDayText = timeOfDay.text.toString()

                    txtSuggestionResult.text = timeOfDayText
                    println("btnSubmit: " + timeOfDayText)
                    generateMeal(timeOfDayText)
                } catch (e: Exception){
//                  Logs the error in console
                    Log.e("btnSubmit", "Error:  ${e.message}")

//                  Displays error message to user
                    Toast.makeText(this, "Make sure you chose an appropriate time of day", Toast.LENGTH_SHORT).show()
//                  resetInputFields(timeOfDay, txtSuggestionResult)
                }
            }

        }

//        Reset Fields Button
        btnReset.setOnClickListener {
            resetInputFields(timeOfDay, txtSuggestionResult)

        }


    }



//    function generates a meal based on the user's chosen input
    private fun generateMeal(timeOfDayText: String){
        Log.i("MainActivity", "Time of day text: $timeOfDayText")
        println("Generate Meal: " + timeOfDayText)
    }

//    function resets fields when called
    private fun resetInputFields(timeOfDay: EditText, txtSuggestionResult: TextView){
        timeOfDay.text.clear()
        txtSuggestionResult.text = ""
    }
}