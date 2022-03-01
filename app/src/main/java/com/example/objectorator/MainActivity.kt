package com.example.objectorator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.objectorator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        binding.apply {
            val getSpan = createSpan(textTitle, 6, 12)
            textTitle.text = getSpan
            val getSpanTwo = createSpan(generalText, 0, 10)
            generalText.text = getSpanTwo
        }

        setContentView(binding.root)
    }

    private fun createSpan(text: TextView, start: Int, end: Int): SpannableStringBuilder {
        val spannable = SpannableStringBuilder(text.text)
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF5656")),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

}