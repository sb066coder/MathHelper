package ru.sb066coder.mathhelper.ui.columndivizion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sb066coder.mathhelper.R
import ru.sb066coder.mathhelper.databinding.FragmentColumnDivisionBinding

class ColumnDivisionFragment : Fragment() {

    private var _binding: FragmentColumnDivisionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val columnDivisionViewModel =
            ViewModelProvider(this)[ColumnDivisionViewModel::class.java]

        _binding = FragmentColumnDivisionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val resultTextView: TextView = binding.tvResult
        val remainTextView: TextView = binding.tvRemain
        val columnTextView: TextView = binding.tvColumn
        columnDivisionViewModel.result.observe(viewLifecycleOwner) {
            resultTextView.text = it
        }
        columnDivisionViewModel.remain.observe(viewLifecycleOwner) {
            remainTextView.text = it
        }
        columnDivisionViewModel.column.observe(viewLifecycleOwner) {
            if (it == "init") {
                columnTextView.text = getString(R.string.enter_both_numbers_then_press_equals)
            } else if (it == "div0") {
                columnTextView.text = getString(R.string.by0)
            } else {
                columnTextView.text = it
            }
        }

        binding.btnEquals.setOnClickListener{
            with(binding) {
                columnDivisionViewModel.divide(etDivided.text.toString(), etDivisor.text.toString())
            }
        }
        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}