package com.sciencegame.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.sciencegame.R
import com.sciencegame.data.Question
import com.sciencegame.data.SampleData
import com.sciencegame.databinding.FragmentGameBinding
import android.view.animation.Animation
import android.view.animation.AnimationUtils

import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.databinding.BindingAdapter

import androidx.databinding.ObservableBoolean
import org.w3c.dom.Text


private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val INCORRECT_BUZZ_PATTERN = longArrayOf(0, 400)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 1000)

class GameFragment : Fragment() {

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        INCORRECT(INCORRECT_BUZZ_PATTERN),
    }

    var isAnimation = ObservableBoolean(false)
    var isAnimationText = ObservableBoolean(false)

    private lateinit var binding : FragmentGameBinding

    private val questions: MutableList<Question> = SampleData.questions

    // To track Current Question and Answer
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>


    // TO track question index and total questions
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 5)

    private var correct:Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,container,false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Must to View Everything means Dynamic Data
        // Bind this fragment class to the layout
        binding.game = this

        isAnimation.set(true)
        isAnimationText.set(true)
        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener {
            view:View ->
            isAnimation.set(true)
            isAnimationText.set(true)

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }

                // The first answer in the original question is always the correct one,
                // currentQuestion.answers[0]
                // so if our answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    buzz(BuzzType.CORRECT.pattern)
                    correct++
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.

                           buzz(BuzzType.GAME_OVER.pattern)
                            if(correct==0){
                                view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(),
                                    NavOptions.Builder().setPopUpTo(R.id.gameFragment,true).build())
                                correct=0
                            }else {
                                view.findNavController().navigate(
                                    GameFragmentDirections.actionGameFragmentToGameWonFragment(
                                        numQuestions,
                                        correct
                                    ),
                                    NavOptions.Builder().setPopUpTo(R.id.gameFragment, true).build()
                                )
                                correct=0
                            }
                    }
                } else {
                    buzz(BuzzType.INCORRECT.pattern)
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.

                        buzz(BuzzType.GAME_OVER.pattern)

                        if(correct==0){
                            view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(),
                                NavOptions.Builder().setPopUpTo(R.id.gameFragment,true).build())
                            correct=0
                        }else {
                            view.findNavController().navigate(
                                GameFragmentDirections.actionGameFragmentToGameWonFragment(
                                    numQuestions,
                                    correct
                                ),
                                NavOptions.Builder().setPopUpTo(R.id.gameFragment, true).build()
                            )
                            correct=0
                        }
                    }

                }
            }
        }

        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(com.sciencegame.R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }

    fun buzz(pattern: LongArray) {
        val buzzer = context!!.getSystemService<Vibrator>()
        buzzer?.let {
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

}

@BindingAdapter("animation")
fun setAnimation(view: ConstraintLayout, isAnimation: Boolean) {
    if (isAnimation) {
        val animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.fadein)
        animation.setRepeatMode(Animation.INFINITE)
        view.startAnimation(animation)
    }
}

@BindingAdapter("animationText")
fun setAnimationText(view: TextView, isAnimation: Boolean) {
    if (isAnimation) {
        val animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.slideinleft)
        animation.setRepeatMode(Animation.INFINITE)
        view.startAnimation(animation)
    }
}