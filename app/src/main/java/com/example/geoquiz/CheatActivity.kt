package com.example.geoquiz

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast



private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
private const val EXTRA_ANSWER_SHOWN = 300
private const val EXTRA_CHEATS_LEFT = "com.example.geoquiz.cheats_left"
class CheatActivity : AppCompatActivity() {
    private var answerIsTrue = false
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var versionTextView: TextView
    private  var cheatsLeft: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatsLeft = intent.getIntExtra(EXTRA_CHEATS_LEFT, 3)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        versionTextView = findViewById(R.id.version_text_view)
        versionTextView.setText(getString(R.string.api_level, android.os.Build.VERSION.SDK_INT))

        showAnswerButton.setOnClickListener {
            var answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            if (cheatsLeft == 0)
            {
                answerText  = R.string.cheat_end
            }else
            {
                Toast.makeText(this, getString(R.string.cheats_left) + (cheatsLeft-1).toString(), Toast.LENGTH_SHORT).show()
            }

            answerTextView.setText(answerText)
            setResult(EXTRA_ANSWER_SHOWN, intent)


        }
    }
    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean, cheatsLeft: Int): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply { putExtra(
                EXTRA_ANSWER_IS_TRUE,answerIsTrue)
            putExtra(EXTRA_CHEATS_LEFT, cheatsLeft)}
        }
    }


}