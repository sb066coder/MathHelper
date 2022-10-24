package ru.sb066coder.mathhelper.ui.factorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sb066coder.mathhelper.R
import ru.sb066coder.mathhelper.databinding.FragmentFactorizationBinding

class FactorizationFragment : Fragment() {

    private var _binding: FragmentFactorizationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factorizationViewModel =
            ViewModelProvider(this)[FactorizationViewModel::class.java]

        _binding = FragmentFactorizationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvResult
        factorizationViewModel.text.observe(viewLifecycleOwner) {
            textView.text = if (it != "PRM") it else getString(R.string.PRM)
        }

        binding.btnCalculate.setOnClickListener {
            factorizationViewModel.calculate(
                binding.rbFactorize.isChecked,
                binding.edNumber.text.toString()
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}