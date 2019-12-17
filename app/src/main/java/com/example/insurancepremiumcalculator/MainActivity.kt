package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener { calculatePremium() }
        buttonReset.setOnClickListener { reset() }

    }

    private fun calculatePremium() {

        val age: Int = spinnerAge.selectedItemPosition

        var premium = when (age) {
            0 -> 60
            1 -> 70
            2 -> 90
            3 -> 120
            4 -> 150
            else -> 150
        }

        var extraMale = 0
        var extraSmoker = 0

        val gender = radioGroupGender.checkedRadioButtonId
        if (gender == R.string.male) {
            extraMale = when (age) {
                0 -> 0//less than 17
                1 -> 50//from 17 to 25
                2 -> 100//from 26 to 30
                3 -> 150//from 31 to 40
                else -> 200//more than 41
            }
        }

        val smoker: Boolean = checkBoxSmoker.isChecked
        if (smoker) {
            extraSmoker = when (age) {
                0 -> 0//less than 17
                1 -> 100//from 17 to 25
                2 -> 150//from 26 to 30
                3 -> 200//from 31 to 40
                4 -> 250//from 41 to 55
                else -> 300//more than 55
            }
            textViewPremium.text = String.format("%s %d", "RM ", premium)
        }

        if (gender == R.string.male) {
            textViewPremium.text = String.format(
                "Premium = %s %d \nExtra payment for male = %s %d \n Extra Smoker payment = %s %d\nTotal Payment = %s %d",
                "RM ", premium,
                "RM ", extraMale,
                "RM ", extraSmoker,
                "RM ", (premium + extraMale + extraSmoker)
            )
        } else {
            textViewPremium.text = String.format(
                "Premium = %s %d \nExtra Smoker Payment = %s %d\n" + "Total Payment = %s %d",
                "RM ", premium,
                "RM ", extraSmoker,
                "RM ", (premium + extraSmoker)
            )
        }

    }

    private fun reset() {
        spinnerAge.setSelection(0)
        checkBoxSmoker.isChecked = false
        radioButtonMale.isChecked = false
        radioButtonFemale.isChecked = false
        textViewPremium.setText("")
    }
}