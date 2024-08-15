package com.example.tzexample.presentation.ui.main.more.myannounced.showannounced

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tzexample.R
import com.example.tzexample.presentation.ui.main.menu.detail.AnnouncedFragmentArgs
import com.example.tzexample.presentation.ui.main.more.myannounced.MyAnnouncedViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ShowMyAnnouncedFragment : Fragment() {
    private val viewModel : MyAnnouncedViewModel by viewModels()
    private val args: ShowMyAnnouncedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_my_announced, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAnnouncedItem(args.id).observe(viewLifecycleOwner){item ->
            val bitmap = BitmapFactory.decodeByteArray(
                item.imageAnnounced,0,item.imageAnnounced.size
            )
            view.findViewById<ImageView>(R.id.image).setImageBitmap(bitmap)
            view.findViewById<TextView>(R.id.price_announced).text = "${item.priceAnnounced} с."
            view.findViewById<TextView>(R.id.title_announced).text = item.nameAnnounced
            view.findViewById<TextView>(R.id.content_announced).text = item.contentAnnounced
        }
        view.findViewById<Button>(R.id.delete_announced).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(resources.getString(R.string.delete_announced_text))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->

                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    lifecycleScope.launch {
                        viewModel.deleteAnnounced(args.id.toInt())
                        findNavController().navigateUp()
                    }
                }
                .show()
        }
    }

}