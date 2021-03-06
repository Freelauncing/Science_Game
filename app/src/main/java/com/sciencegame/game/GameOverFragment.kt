package com.sciencegame.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.sciencegame.R
import com.sciencegame.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_over, container, false)
        binding.tryAgainButton.setOnClickListener{
//            it.findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
            it.findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment())

        }
        return binding.root
    }

}