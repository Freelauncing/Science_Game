package com.sciencegame.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import com.sciencegame.R
import com.sciencegame.data.Guide
import com.sciencegame.data.SampleData
import com.sciencegame.databinding.FragmentLearningBinding

class LearningFragment : Fragment() {

    private lateinit var binding : FragmentLearningBinding

    val guides: MutableList<Guide> = SampleData.guides

    var isAnimationImage = ObservableBoolean(false)
    var isAnimationText = ObservableBoolean(false)
        // To track Current Question and Answer
    lateinit var currentguide: Guide

    // TO track question index and total guides
    private var questionIndex = 0
    private val numguides = 11

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_learning,container,false)

        binding.guide = this

        setQuestion()

        isAnimationImage.set(true)
        isAnimationText.set(true)

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener { view: View ->

                isAnimationImage.set(true)
                isAnimationText.set(true)

                questionIndex++
                // Advance to the next question
                if (questionIndex < numguides) {
                    currentguide = guides[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                    if(questionIndex == 10){
                        binding.submitButton.setText("Learn Again")
                    }else{
                        binding.submitButton.setText("Next")
                    }
                } else {
                    questionIndex=0
                    currentguide = guides[questionIndex]
                    binding.invalidateAll()
                    if(questionIndex == 10){
                        binding.submitButton.setText("Learn Again")
                    }else{
                        binding.submitButton.setText("Next")
                    }
                }
            }
        // Inflate the layout for this fragment
        return binding.root
    }

// Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentguide = guides[questionIndex]
        // randomize the answers into a copy of the array
    }

}

@BindingAdapter("animationImage")
fun setAnimationImage(view: ImageView, isAnimation: Boolean) {
    if (isAnimation) {
        val animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.fadein)
        animation.setRepeatMode(Animation.INFINITE)
        view.startAnimation(animation)
    }
}

@BindingAdapter("animationText")
fun setAnimation(view: TextView, isAnimation: Boolean) {
    if (isAnimation) {
        val animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.slideinleft)
        animation.setRepeatMode(Animation.INFINITE)
        view.startAnimation(animation)
    }
}