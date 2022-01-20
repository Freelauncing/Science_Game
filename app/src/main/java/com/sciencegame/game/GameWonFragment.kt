package com.sciencegame.game

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.sciencegame.R
import com.sciencegame.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false)

        var args = GameWonFragmentArgs.fromBundle(requireArguments())

        binding.yourscore.setText("Your Score: " + args.numCorrect)

        binding.totalscore.setText("Total Score: "+args.numQuestions)

        Toast.makeText(context,"NumCorrect = ${args.numCorrect} , Numquestions = ${args.numQuestions}",Toast.LENGTH_SHORT).show()

        binding.nextMatchButton.setOnClickListener{
            it.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu,menu)
        if(null == getShareIntent().resolveActivity(requireActivity()!!.packageManager)){
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    private fun getShareIntent() :Intent{
        var args = GameWonFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent

    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}