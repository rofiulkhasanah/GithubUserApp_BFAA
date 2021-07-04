package com.dicoding.picodiploma.consumerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.consumerapp.databinding.ItemRowUsersBinding
import com.dicoding.picodiploma.consumerapp.databinding.ItemRowUsersBinding.inflate
import com.dicoding.picodiploma.consumerapp.model.User

class ListUserAdapter(
    private val listGithubUser: ArrayList<User>
) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var clickListener: OnItemListener

    inner class ListViewHolder(private val binding: ItemRowUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgAva)

                tvName.text = user.login
            }
            binding.root.setOnClickListener {
                clickListener.onUserClick(user, adapterPosition)
            }
        }
    }

    fun setGithubUserClick(onItemListener: OnItemListener) {
        this.clickListener = onItemListener
    }

    interface OnItemListener {
        fun onUserClick(listUser: User, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(view)
    }

    fun onList(dataUser: List<User>) {
        listGithubUser.clear()
        listGithubUser.addAll(dataUser)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listGithubUser[position])
    }

    override fun getItemCount(): Int = listGithubUser.size
}