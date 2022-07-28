package com.dars.retrofit_takrorlash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dars.retrofit_takrorlash.databinding.UserItemBinding
import com.dars.retrofit_takrorlash.model.UserItem

class UserAdapter : ListAdapter<UserItem, UserAdapter.MyViewHolder>(diffUtil) {

    var listener: OnUserClickListener? = null

    class MyViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bittaUser = getItem(position)
        holder.binding.apply {
            txtId.text = bittaUser.id.toString()
            txtCity.text = bittaUser.address.city
            txtEmail.text = bittaUser.email
            txtUsername.text = bittaUser.username
            txtPhone.text = bittaUser.phone
            txtName.text = bittaUser.name
        }
        holder.itemView.setOnClickListener {
            listener?.onUserClick(bittaUser)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

    interface OnUserClickListener {
        fun onUserClick(userItem: UserItem)
    }

    fun setOnUserItemClickListener(onUserClickListener: OnUserClickListener) {
        listener = onUserClickListener
    }

}