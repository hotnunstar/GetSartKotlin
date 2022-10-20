package ipca.example.noticiasfrescas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivityStart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_start)

        val onButtonNewsPressed : ((View)->Unit)? = {
            val buttonPressed = it as? Button
            val newsPressed : String = buttonPressed?.text.toString()

            val category = newsPressed.substring(0, newsPressed.indexOf(' ')) // Obtém a primeira palavra da string para definir a categoria
            Log.d(MainActivity.TAG, category)

            val intent = Intent(this@MainActivityStart, MainActivity::class.java)
            intent.putExtra("category", category)
            startActivity(intent)
        }

        val buttonSports = findViewById<Button>(R.id.buttonSports)
        buttonSports.setOnClickListener(onButtonNewsPressed)
        val buttonTechnology = findViewById<Button>(R.id.buttonTechnology)
        buttonTechnology.setOnClickListener(onButtonNewsPressed)
        val buttonEntertainment = findViewById<Button>(R.id.buttonEntertainment)
        buttonEntertainment.setOnClickListener(onButtonNewsPressed)
        val buttonScience = findViewById<Button>(R.id.buttonScience)
        buttonScience.setOnClickListener(onButtonNewsPressed)
    }
}

// CATEGORIES AVAILABLE:
// sports
// technology
// entertainment
// science