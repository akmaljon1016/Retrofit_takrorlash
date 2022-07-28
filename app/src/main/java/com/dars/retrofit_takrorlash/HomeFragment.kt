package com.dars.retrofit_takrorlash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dars.retrofit_takrorlash.databinding.FragmentHomeBinding
import com.dars.retrofit_takrorlash.model.UserItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter()
        binding.recyclerview.adapter = userAdapter

        userAdapter.setOnUserItemClickListener(object : UserAdapter.OnUserClickListener {
            override fun onUserClick(userItem: UserItem) {
                val action =
                    HomeFragmentDirections.actionDestinationHomeToDetailedFragment(userItem)
                findNavController().navigate(action)
            }
        })
        val api = RetrofitHelper.getInstance().create(NetworkApi::class.java)
        val call = api.getUser()

        call.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                userAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        requireActivity().
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.VISIBLE
    }
}