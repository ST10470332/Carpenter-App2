

package vcmsa.ci.carpenterapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnInstallDoor: RadioButton
    private lateinit var btnBuildShelf: RadioButton
    private lateinit var btnAssembleFurniture: RadioButton

    private lateinit var checkWood: CheckBox
    private lateinit var checkNails: CheckBox
    private lateinit var checkHinges: CheckBox

    private lateinit var editTotalHours: EditText
    private lateinit var btnCalculate: Button
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize Views
        btnInstallDoor = findViewById(R.id.radioInstallDoor)
        btnBuildShelf = findViewById(R.id.radioBuildShelf)
        btnAssembleFurniture = findViewById(R.id.radioAssembleFurniture)

        checkWood = findViewById(R.id.checkboxWood)
        checkNails = findViewById(R.id.checkboxNails)
        checkHinges = findViewById(R.id.checkboxHinges)

        editTotalHours = findViewById(R.id.editTextNumberDecimal)
        btnCalculate = findViewById(R.id.btnCalculate)
        textResult = findViewById(R.id.textView)

        // Button Click Listener
        btnCalculate.setOnClickListener {
            calculateCost()
        }
    }

    private fun calculateCost() {
        val hours = editTotalHours.text.toString().toDoubleOrNull()
        if (hours == null || hours <= 0) {
            textResult.text = "Please enter a valid number of hours"
            return
        }

        // Selected materials and their cost
        val materials = listOf(
            Pair(checkWood, 500),
            Pair(checkNails, 20),
            Pair(checkHinges, 30)
        )

        var materialCost = 0
        for ((checkBox, cost) in materials) {
            if (checkBox.isChecked) {
                materialCost += cost
            }
        }

        if (materialCost == 0) {
            textResult.text = "Please select at least one material"
            return
        }

        val totalCost = materialCost * hours
        textResult.text = "Total Estimated Cost: R${"%.2f".format(totalCost)}"
    }
}
