package com.example.mysololife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysololife.R
import com.example.mysololife.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private lateinit var binding: FragmentTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        binding.storeTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_tipFragment_to_storeFragment)
        }

        binding.talkTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_tipFragment_to_talkFragment)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_tipFragment_to_homeFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_tipFragment_to_bookmarkFragment)
        }

        return binding.root
    }
}