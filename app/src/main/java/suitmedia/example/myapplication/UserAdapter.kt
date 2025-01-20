package suitmedia.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(
    private val users: MutableList<UserModel>,
    private val onItemClick: (UserModel) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.tvFullname)
        val emailTextView: TextView = view.findViewById(R.id.tvEmail)
        val avatarImageView: ImageView = view.findViewById(R.id.ivUserAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.nameTextView.text = "${user.first_name} ${user.last_name}"
        holder.emailTextView.text = user.email
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .centerCrop()
            .circleCrop()
            .into(holder.avatarImageView)

        holder.itemView.setOnClickListener { onItemClick(user) }
    }

    override fun getItemCount(): Int = users.size

    fun addUsers(newUsers: List<UserModel>) {
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun clear() {
        users.clear()
        notifyDataSetChanged()
    }
}