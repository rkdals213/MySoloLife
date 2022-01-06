package com.example.mysololife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysololife.R
import com.example.mysololife.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.tipTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_homeFragment_to_tipFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_homeFragment_to_talkFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_bookmarkFragment_to_storeFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }

        return binding.root
    }
}