package com.example.tzexample.presentation.ui.main.add.addannounced

import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tzexample.R
import com.example.tzexample.data.locale.db.AnnouncedDbModel
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController
import com.example.tzexample.presentation.extensions.backToFlow
import com.example.tzexample.presentation.extensions.overrideOnBackPressed
import com.example.tzexample.presentation.ui.main.add.addannounced.image.UploadImageBottomSheet
import com.example.tzexample.presentation.ui.main.menu.MenuViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddAnnouncedFragment : Fragment() {
    private val viewModel : AddAnnouncedViewModel by viewModels()

    private val cityValue = MutableStateFlow("")
    private val imageValue = MutableStateFlow<Uri?>(null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.GONE
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_announced, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = arrayOf("Душанбе","Худжанд","Бохтар","Куляб","Дангара","Хорог")
        val arrayAdapter = ArrayAdapter<String>(requireContext(),R.layout.item_select_city,city)
        view.findViewById<AutoCompleteTextView>(R.id.select_city).apply {
            setAdapter(arrayAdapter)
            inputType = InputType.TYPE_NULL
           setOnItemClickListener { adapterView, view, i, l ->
               lifecycleScope.launch {
                   cityValue.emit(city[i])
               }
            }
        }
        view.findViewById<ImageView>(R.id.back).setOnClickListener {
            activityNavController().navigateUp()
        }
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_title).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_title),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
            }else if (cityValue.value.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.city),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_title).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                        cityAnnounced = cityValue.value,
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigateUp()
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
                }
        }
        view.findViewById<MaterialCardView>(R.id.upload_image).setOnClickListener {
            val selectUpload = UploadImageBottomSheet{ uri ->
                view.findViewById<ImageView>(R.id.show_image).setImageURI(uri)
                if (uri != null){
                    lifecycleScope.launch {
                        imageValue.emit(uri)
                    }
                }
            }

            selectUpload.show(requireActivity().supportFragmentManager, "SELECTIMAGE")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.VISIBLE
    }
}