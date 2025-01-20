package suitmedia.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import suitmedia.example.myapplication.databinding.ActivityMainBinding
import suitmedia.example.myapplication.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibBack.setOnClickListener {
            finish()
        }

        binding.tvUser.text = intent.getStringExtra("name")

        binding.btChoose.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedUser = data?.getStringExtra("selected")
            binding.tvSelectedUser.text = "Selected User: $selectedUser"
        }
    }
}

