package id.ac.ubaya.informatika.adv160420078week2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var score = 0
        var answer = 0

        var randomX = (0..1000).random()
        var randomY = (0..1000).random()

        answer = randomX + randomY

        if(answer != null){
            txtQuestion.text = "$randomX + $randomY"
        }


        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
        btnSubmit.setOnClickListener {
            var playerAnswer = txtAnswer.text
            if(playerAnswer.toString() == answer.toString()){
                score++

                randomX = (0..1000).random()
                randomY = (0..1000).random()

                answer = randomX + randomY
                txtQuestion.text = "$randomX + $randomY"
                Toast.makeText(activity, "Well Done, You Answered Correctly", Toast.LENGTH_SHORT).show()
                txtAnswer.setText("")
            }else{
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}