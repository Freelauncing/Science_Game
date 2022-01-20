package com.sciencegame.game

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.sciencegame.R
import com.sciencegame.databinding.FragmentGameHomeBinding

class GameHomeFragment : Fragment() {

    private lateinit var binding : FragmentGameHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_home,container,false)

        setHasOptionsMenu(true)

        binding.playButton.setOnClickListener {
            it.findNavController().navigate(GameHomeFragmentDirections.actionTitleFragmentToGameFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Id at menu item and Fragment must be same
        // otherwise we have to implement it own ourself like switch()/when() by id
        return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}