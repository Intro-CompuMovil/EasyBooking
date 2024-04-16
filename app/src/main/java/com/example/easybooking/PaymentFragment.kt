package com.example.easybooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.text.InputFilter
import android.text.Spanned

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PaymentFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_payment, container, false)

        val editTextCardNumber = rootView.findViewById<EditText>(R.id.editTextCardNumber)
        val editTextExpiryMonth = rootView.findViewById<EditText>(R.id.editTextExpiryMonth)
        val editTextExpiryYear = rootView.findViewById<EditText>(R.id.editTextExpiryYear)
        val editTextCVV = rootView.findViewById<EditText>(R.id.editTextCVV)
        val buttonPay = rootView.findViewById<Button>(R.id.buttonPay)

        // Set filter to enforce format on expiry month and year (MM/YY)
        val expiryMonthFilter = MonthInputFilter()
        val expiryYearFilter = YearInputFilter()
        editTextExpiryMonth.filters = arrayOf(expiryMonthFilter, InputFilter.LengthFilter(2))
        editTextExpiryYear.filters = arrayOf(expiryYearFilter, InputFilter.LengthFilter(2))

        buttonPay.setOnClickListener {
            val cardNumber = editTextCardNumber.text.toString()
            val expiryMonth = editTextExpiryMonth.text.toString()
            val expiryYear = editTextExpiryYear.text.toString()
            val cvv = editTextCVV.text.toString()

            // Perform validation here
            if (cardNumber.isEmpty() || expiryMonth.isEmpty() || expiryYear.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Payment logic goes here
                // For simulation purpose, just show a toast indicating successful payment
                Toast.makeText(requireContext(), "Payment successful!", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

private class MonthInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        // Allow only numbers between 1 and 12
        for (i in start until end) {
            if (!Character.isDigit(source!![i])) {
                return ""
            }
            val input = (dest.toString() + source.subSequence(start, end)).toInt()
            if (input < 1 || input > 12) {
                return ""
            }
        }
        return null
    }
}

private class YearInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        // Allow only numbers and limit input to 2 digits (YY)
        for (i in start until end) {
            if (!Character.isDigit(source!![i])) {
                return ""
            }
        }
        return null
    }
}
