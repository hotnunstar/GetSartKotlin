package ipca.budget.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewWarning = findViewById<TextView>(R.id.textViewWarning)
        val verticalSliderL = findViewById<VerticalSlider>(R.id.verticalSliderL)
        val verticalSliderR = findViewById<VerticalSlider>(R.id.verticalSliderR)
        val switchLock = findViewById<Switch>(R.id.switchLock)

        verticalSliderL.setOnValueChange {
            if (it >= 80 || verticalSliderR.percentage >= 80) {
                textViewWarning.text = "O volume está alto!"
            }else{
                textViewWarning.text = ""
            }
            if (switchLock.isChecked) {
                verticalSliderR.percentage = verticalSliderL.percentage
            }
        }

        verticalSliderR.setOnValueChange {
            if (it >= 80 || verticalSliderL.percentage >= 80) {
                textViewWarning.text = "O volume está alto!"
            }else{
                textViewWarning.text = ""
            }
            if (switchLock.isChecked) {
                verticalSliderL.percentage = verticalSliderR.percentage
            }
        }
    }
}