package com.t_ovchinnikova.android.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.t_ovchinnikova.android.composition.R
import com.t_ovchinnikova.android.composition.databinding.FragmentGameBinding
import com.t_ovchinnikova.android.composition.databinding.FragmentGameFinishedBinding
import com.t_ovchinnikova.android.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
    get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //parseArgs()
        gameResult = args.gameResult
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindViews() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text =
                String.format(getString(R.string.required_score),
                    gameResult.gameSettings.minCountOfRightAnswers)
            tvScoreAnswers.text =
                String.format(getString(R.string.score_answers),
                    gameResult.countOfRightAnswers)
            tvRequiredPercentage.text =
                String.format(getString(R.string.required_percentage),
                    gameResult.gameSettings.minPercentOfRightAnswer)
            tvScorePercentage.text =
                String.format(getString(R.string.score_percentage),
                    getPercentOfRightsAnswers())
        }
    }

    private fun getPercentOfRightsAnswers() = with(gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    private fun setupClickListeners() {
        /*requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        })*/
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    /*private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }*/

    private fun retryGame() {
        //requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        findNavController().popBackStack()
    }

    companion object {
        const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}