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
        val btnGenerateMeal = findViewById<Button>(R.id.btnGenerateMeal)
        val btnReset = findViewById<Button>(R.id.btnReset)
//      Text Fields (Edit & View)
        var txtSuggestionResult = findViewById<TextView>(R.id.txtSuggestionResult)
        var timeOfDay = findViewById<EditText>(R.id.txeTimeOfDay)

//      Generate Meal Button Listener
        btnGenerateMeal.setOnClickListener{
//          checks if user input is not blank nor whitespaces
            if (timeOfDay.text.isNotBlank()) {
                try {
//              tries to stringify user input and store in timeOfDayText variable
                    var timeOfDayText = timeOfDay.text.toString()

//                    txtSuggestionResult.text = timeOfDayText
//                  Calls GenerateMeal Function
                    val suggestedMeal = generateMeal(timeOfDayText)
                    if (suggestedMeal == "invalid selection"){
                        Toast.makeText(this, "Please choose one of the options listed above.", Toast.LENGTH_SHORT).show()
                    } else {
                        txtSuggestionResult.text = suggestedMeal
                    }
                } catch (e: Exception){
//                  Logs the error in console
                    Log.e("btnSubmit", "Error:  ${e.message}")

//                  Displays error message to user if they don't have correct time of day chosen out of instructions
                    Toast.makeText(this, "Make sure you chose an appropriate time of day", Toast.LENGTH_SHORT).show()
                }
            } else {
//              Displays message to user if they click on Generate Meal button if it is empty or has only whitespaces
                Toast.makeText(this, "The input cannot be left empty or whitespaces only", Toast.LENGTH_SHORT).show()
            }

        }

//      Reset Fields Button Listener; calls resetInputFunctions
        btnReset.setOnClickListener {
            resetInputFields(timeOfDay, txtSuggestionResult)
        }


    }



//    function generates a meal based on the user's chosen input
    private fun generateMeal(timeOfDayText: String): String {
            val suggestedMeal: String = when (timeOfDayText.uppercase()) {
                "MORNING" -> "Banana"
                "MID-MORNING" -> "Egg"
                "AFTERNOON" -> "Chicken"
                "AFTERNOON SNACK" -> "Protein Shake"
                "DINNER" -> "Beef"
                else -> {
                "invalid selection"}
            }
            return suggestedMeal
    }

//    function resets fields when called
    private fun resetInputFields(timeOfDay: EditText, txtSuggestionResult: TextView){
        try {
//            clears both timeOfDay input text and the SuggestionResult text
            timeOfDay.text.clear()
            txtSuggestionResult.text = ""
        }  catch (e: Exception){
//            Displays potential error to user
            Toast.makeText(this, "Something went wrong when trying to reset the time of day and/or meal suggestion zone. Please try again, if it continues report this to the bugtracker.", Toast.LENGTH_SHORT).show()
            Log.e("reset Button Function", "Error resetting fields: ${e.message}")
        }

    }
}