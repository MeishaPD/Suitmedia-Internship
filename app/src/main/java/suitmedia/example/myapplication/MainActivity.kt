package suitmedia.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import suitmedia.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCheck.setOnClickListener {
            val palCheck = binding.etPalindrome.text.toString().trim()

            if(palCheck.isEmpty()) {
                AlertDialog.Builder(this)
                    .setMessage("Palindrome cannot be empty")
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                val clean = palCheck.replace(" ", "").lowercase()
                val isPalindrome = clean == clean.reversed()

                val dialog = if (isPalindrome) "isPalindrome" else "Not Palindrome"
                AlertDialog.Builder(this)
                    .setMessage(dialog)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        binding.btNext.setOnClickListener {
            val name = binding.etName.text.toString()
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
            finish()
        }
    }
}