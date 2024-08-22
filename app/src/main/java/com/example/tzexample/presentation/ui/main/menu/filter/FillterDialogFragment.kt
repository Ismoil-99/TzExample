package com.example.tzexample.presentation.ui.main.menu.filter

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.presentation.ui.main.menu.MenuViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FillterDialogFragment : BottomSheetDialogFragment() {
    private val viewModel : MenuViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fillter_dialog_list_dialog, container, false)

    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.filt_search).setOnClickListener {
            dismiss()
        }
        view.findViewById<ImageView>(R.id.close_filter).setOnClickListener {
            dismiss()
        }

        viewModel.getRubricsDb().observe(viewLifecycleOwner){rubrics ->
            rubrics.map {
                val textView = TextView(requireContext())
                textView.width = 350
                textView.text = "${it.nameRubric}"
                textView.setBackgroundResource(R.drawable.ramka_for_filtr)
                textView.textSize = 16f
                val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                layoutParams.width = 0
                layoutParams.setMargins(20,10,20,10)
                textView.setPadding(10,10,10,10)
                view.findViewById<GridLayout>(R.id.categories).addView(textView,layoutParams)
            }
        }
    }
    @SuppressLint("RestrictedApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.disableShapeAnimations()

        dialog.setOnShowListener { dialogInterface ->

            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it1 ->
                val behaviour = BottomSheetBehavior.from(it1)
                it1.layoutParams.also {
                    it.height =
                        context?.resources?.displayMetrics?.heightPixels?.times(1)?.toInt()!!
                }
                behaviour.peekHeight =
                    context?.resources?.displayMetrics?.heightPixels?.times(1)?.toInt()!!
            }
        }
        return dialog
    }

}
