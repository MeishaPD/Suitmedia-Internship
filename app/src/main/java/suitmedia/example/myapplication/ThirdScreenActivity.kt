package suitmedia.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import suitmedia.example.myapplication.databinding.ActivitySecondScreenBinding
import suitmedia.example.myapplication.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapter: UserAdapter
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(mutableListOf()) { user ->
            val intent = Intent()
            intent.putExtra("selected", "${user.first_name} ${user.last_name}")
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            currentPage = 1
            fetchUsers(true)
        }

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    currentPage++
                    fetchUsers(false)
                }
            }
        })

        fetchUsers(false)

    }

    private fun fetchUsers(clear: Boolean) {
        RetrofitInstance.api.getUsers(currentPage, 5).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.data ?: emptyList()
                    if (clear) adapter.clear()
                    adapter.addUsers(users)
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@ThirdScreenActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        })
    }

}