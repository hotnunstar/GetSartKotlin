package ipca.example.noticiasfrescas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class ChooseLanguageActivity : AppCompatActivity() {

    val languages = arrayOf("Select your language", "AR - ARABIC", "DE - GERMAN", "EN - ENGLISH",
        "ES - SPANISH", "FR - FRENCH", "HE - HEBREW", "IT - ITALIAN", "NL - DUTCH", "NO - NORWEGIAN",
        "PT - PORTUGUESE", "RU - RUSSIAN", "SV - SWEDISH", "ZH - CHINESE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_language)

        var language = "en"

        val spinner = findViewById<Spinner>(R.id.spinnerLanguage)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                language = languages[position].substring(0, languages[position].indexOf(' ')).lowercase()
                if (language == "select") language = "en"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                language = "en"
            }
        }

        val onButtonStartPressed : ((View)->Unit) = {
            Log.d("Language:", language)

            val intent = Intent(this@ChooseLanguageActivity, NewsCategoryActivity::class.java)
            intent.putExtra("language",  language)
            startActivity(intent)
        }

        val buttonStart = findViewById<Button>(R.id.buttonStart)
        buttonStart.setOnClickListener(onButtonStartPressed)
    }
}

// LANGUAGES AVAILABLE:
// AR - ARABIC
// DE - GERMAN
// EN - ENGLISH
// ES - SPANISH
// FR - FRENCH
// HE - HEBREW
// IT - ITALIAN
// NL - DUTCH
// NO - NORWEGIAN
// PT - PORTUGUESE
// RU - RUSSIAN
// SV - SWEDISH
// ZH - CHINESE
