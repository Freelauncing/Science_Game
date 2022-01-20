package com.sciencegame.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.sciencegame.R
import com.sciencegame.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        binding.moveToLoarn.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToLearningFragment(), NavOptions.Builder().setPopUpTo(R.id.homeFragment,true).build())
        }
        binding.movetoPlay.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToGameHomeFragment(), NavOptions.Builder().setPopUpTo(R.id.homeFragment,true).build())
        }
        return binding.root;
    }

}