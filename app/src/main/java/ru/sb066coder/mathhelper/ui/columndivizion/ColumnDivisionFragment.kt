package ru.sb066coder.mathhelper.ui.columndivizion

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
        val params: FrameLayout.LayoutParams = binding.ivDivisionLine.layoutParams as FrameLayout.LayoutParams
        val dm = resources.displayMetrics
        var spDim: Float
        columnDivisionViewModel.result.observe(viewLifecycleOwner) {
            resultTextView.text = it
        }
        columnDivisionViewModel.remain.observe(viewLifecycleOwner) {
            remainTextView.text = it
        }
        columnDivisionViewModel.column.observe(viewLifecycleOwner) {
            if (it == "init") {
                columnTextView.text = getString(R.string.enter_both_numbers_then_press_equals)
                binding.ivDivisionLine.visibility = View.INVISIBLE
            } else if (it == "div0") {
                columnTextView.text = getString(R.string.by0)
                binding.ivDivisionLine.visibility = View.INVISIBLE
            } else {
                columnTextView.text = it
                spDim = (4.3 + columnDivisionViewModel.divDigits * 9.53).toFloat()
                params.marginStart = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spDim, dm).toInt()
//                    300/*(4.3 + columnDivisionViewModel.divDigits * 9.51).toInt()*/,
//                    params.topMargin, 300, params.bottomMargin
//                )
                binding.ivDivisionLine.layoutParams = params                          /*(4.3 + columnDivisionViewModel.divDigits * 9.51).toInt()*/
                binding.ivDivisionLine.visibility = View.VISIBLE
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