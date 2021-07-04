package com.dicoding.picodiploma.githubuserapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuserapp.data.model.UsersFav
import com.dicoding.picodiploma.githubuserapp.databinding.ItemRowUsersBinding
import com.dicoding.picodiploma.githubuserapp.databinding.ItemRowUsersBinding.inflate
import com.dicoding.picodiploma.githubuserapp.ui.FavActivity

class FavUserAdapter(
    private val listGithubUser: ArrayList<UsersFav>
) : RecyclerView.Adapter<FavUserAdapter.ListViewHolder>() {
    private lateinit var clickListener: OnItemListener
    var mIntent: Intent? = null
    val id = mIntent?.getIntExtra(FavActivity.EXTRA_ID, 0)

    @Suppress("DEPRECATION")
    inner class ListViewHolder(private val binding: ItemRowUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UsersFav) {
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
        fun onUserClick(listUser: UsersFav, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(view)
    }

    fun onList(dataUser: List<UsersFav>) {
        listGithubUser.clear()
        listGithubUser.addAll(dataUser)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listGithubUser[position])
    }

    override fun getItemCount(): Int = listGithubUser.size


//    fun fav(user: UsersFav){
//        CoroutineScope(Dispatchers.IO).launch {
//            val final = id.let { model.githubUserCheck(user) }
//            withContext(Dispatchers.Main){
//                if (final != null) {
//                    if (final > 0){
//                        true.also { binding.tbFav.isChecked = it }
//                        checked = true
//                    }else{
//                        false.also { binding.tbFav.isChecked = it }
//                        checked = false
//                    }
//                }
//            }
//        }
//
//        binding..setOnClickListener{
//            checked = !checked
//            if (checked){
//                detailModel.addFav(username, id, avaGithubUser)
//            }else{
//                detailModel.githubUserFavUncheck(id)
//            }
//            binding.tbFav.isChecked = checked
//        }
//    }
}