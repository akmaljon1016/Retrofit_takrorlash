package com.dars.retrofit_takrorlash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.dars.retrofit_takrorlash.databinding.FragmentDetailedBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetailedFragment : Fragment() {
    lateinit var binding: FragmentDetailedBinding
    val args: DetailedFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textview.text = args.userDaetail.toString()
        requireActivity().
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE
    }
}