package com.example.tzexample.presentation.ui.main.add.addannounced

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
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
import android.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tzexample.R
import com.example.tzexample.data.locale.db.AnnouncedDbModel
import com.example.tzexample.data.locale.preferences.PreferencesHelper
import com.example.tzexample.presentation.extensions.activityNavController
import com.example.tzexample.presentation.extensions.hideActionBar
import com.example.tzexample.presentation.ui.main.add.addannounced.image.UploadImageBottomSheet
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


    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).visibility = View.GONE
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_announced, container, false)
    }
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferencesHelper = PreferencesHelper(requireContext())
        when(preferencesHelper.getTypeAnnounced()){
            1 ->{
                nedvizhimostArenda(view)
            }
            2 ->{
                nedvizhimostArenda(view)
            }
            3 ->{
                nedvizhimostArenda(view)
            }
            4 ->{
                nedvizhimostArendaHavli(view)
            }
            5 ->{
                saleNedvizhimost(view)
            }
            6 ->{
                nedvizhimostArendaHavli(view)
            }
            7 ->{
                val newLayout = layoutInflater.inflate(R.layout.layout_nedvizhimost_sale_garazh,null)
                uploadImage(newLayout)
                view.findViewById<Button>(R.id.sign_in).setOnClickListener {
                    if (view.findViewById<EditText>(R.id.value_header).text.isEmpty()){
                        Toast.makeText(requireContext(),resources.getString(R.string.error_title),Toast.LENGTH_SHORT).show()
                    }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                        Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
                    }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                        Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
                    } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                        Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
                    } else if (imageValue.value == null){
                        Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
                    } else{
                        lifecycleScope.launch(Dispatchers.IO) {
                            val announced = AnnouncedDbModel(
                                id = 0,
                                nameAnnounced = view.findViewById<EditText>(R.id.value_header).text.toString(),
                                contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                                cityAnnounced = "",
                                telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                                priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                                imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                            )
                            viewModel.insertAnnounced(
                                announced
                            )
                        }
                        activityNavController().navigate(R.id.to_main_from_add)
                        Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
                    }
                }
                view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
            }
            8 ->{
                saleCars(view)
            }
            9 ->{
                saleMotoCycle(view)
            }
            10 ->{
                saleZapjast(view)
            }
            11 ->{
                vacancies(view)
            }
            12 ->{
                vacancies(view)
            }
            13 ->{
                vacancies(view)
            }
            14 ->{
                vacancies(view)
            }
            15 ->{
                vacancies(view)
            }
            16 ->{
                vacancies(view)
            }
            17 ->{
                vacancies(view)
            }
            18 ->{
                vacancies(view)
            }
            19 ->{
                vacancies(view)
            }
            20 ->{
                vacancies(view)
            }
            21 ->{
                saleTel(view)
            }
            22 ->{
                saleZapjastTel(view)
            }
        }
    }




    private fun nedvizhimostArenda(view: View){
        val newLayout = layoutInflater.inflate(R.layout.layout_nedvizhimost_arenda,null)
        val arrayAdapterEtazh = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.etazh))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_city).setAdapter(arrayAdapterEtazh)
        val arrayAdapterComnata = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.count_home))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_count_home).setAdapter(arrayAdapterComnata)
        val arrayAdapterTypeHome = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_home))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_remont).setAdapter(arrayAdapterTypeHome)
        val arrayAdapterTypeRemont = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_home_remont))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_remont_home).setAdapter(arrayAdapterTypeRemont)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_header).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_title),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_header).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
                }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }

    private fun nedvizhimostArendaHavli(view: View){
        val newLayout = layoutInflater.inflate(R.layout.layout_nedvizhimost_sale_havli,null)
        val countKomnatAdapter = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.count_home))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_komnat).setAdapter(countKomnatAdapter)
        val countEtazhAdapter = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.count_home))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_etazh).setAdapter(countEtazhAdapter)
        val typeRemontAdapter = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_home_remont))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_remont_home).setAdapter(typeRemontAdapter)
        val typeHavliAdapter = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_havli))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_state).setAdapter(typeHavliAdapter)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_header).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_title),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_header).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }
    private fun saleNedvizhimost(view: View) {
        val newLayout = layoutInflater.inflate(R.layout.layout_nedvizhimost_sale,null)
        val arrayAdapterEtazh = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.etazh))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_city).setAdapter(arrayAdapterEtazh)
        val arrayAdapterTypeZas = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_zastoryka_remont))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_remont).setAdapter(arrayAdapterTypeZas)
        val arrayAdapterTypeRemont = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_home_remont))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_remont_home).setAdapter(arrayAdapterTypeRemont)
        val arrayAdapterTypeStateRemont = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_state_remont))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_state).setAdapter(arrayAdapterTypeStateRemont)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_header).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_title),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_header).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }

    private fun saleCars(view: View){
        val newLayout = layoutInflater.inflate(R.layout.layout_transport_legkovoy,null)
        val arrayAdapterModelCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.model_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_city).setAdapter(arrayAdapterModelCars)
        val arrayAdapterStateCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.state_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.state_cars).setAdapter(arrayAdapterStateCars)
        val arrayAdapterFoundCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.found_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.found_cars).setAdapter(arrayAdapterFoundCars)
        val arrayAdapterSquareCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.motor_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.square_cars).setAdapter(arrayAdapterSquareCars)
        val arrayAdapterKuzovCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.kuzov_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_kuzov).setAdapter(arrayAdapterKuzovCars)
        val arrayAdapterPeredajaCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.korobka_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_peredaja).setAdapter(arrayAdapterPeredajaCars)
        val arrayAdapterToplivaCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.topliva_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_topliva).setAdapter(arrayAdapterToplivaCars)
        val arrayAdapterPrivodCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.privod_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_privod).setAdapter(arrayAdapterPrivodCars)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_model).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля",Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_model).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }
    private fun saleMotoCycle(view: View) {
        val newLayout = layoutInflater.inflate(R.layout.layout_transport_motocycle,null)
        val arrayAdapterFoundCars = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.found_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.found_motocycle_select).setAdapter(arrayAdapterFoundCars)
        val arrayAdapterTypeMotocycle = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.model_motocycle))
        newLayout.findViewById<AutoCompleteTextView>(R.id.marka_select_motocycle).setAdapter(arrayAdapterTypeMotocycle)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.obem_content).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля",Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_content).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_content),Toast.LENGTH_SHORT).show()
            }else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.obem_content).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_content).text.toString(),
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }

    private fun saleZapjast(view: View){
        val newLayout = layoutInflater.inflate(R.layout.layout_transport_zapchast,null)
        val arrayAdapterStateZapjast = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.state_zapjast))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_state).setAdapter(arrayAdapterStateZapjast)
        val arrayAdapterMarkaZapjast = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.model_cars))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_zapchast).setAdapter(arrayAdapterMarkaZapjast)
        val arrayAdapterTypeZapjast = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.type_zapjast))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_zapjast_model).setAdapter(arrayAdapterTypeZapjast)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_tel),Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),resources.getString(R.string.error_price),Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = "",
                        contentAnnounced = "",
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }
    private fun vacancies(view: View) {
        val newLayout = layoutInflater.inflate(R.layout.layout_vacancies,null)
        val arrayAdapterGrafik = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.grafik_work))
        newLayout.findViewById<AutoCompleteTextView>(R.id.grafic_work_select).setAdapter(arrayAdapterGrafik)
        val arrayAdapterStazh = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.stazh_work))
        newLayout.findViewById<AutoCompleteTextView>(R.id.stazh_select_work).setAdapter(arrayAdapterStazh)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_sfera).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_company).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_count).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_sfera).text.toString(),
                        contentAnnounced = view.findViewById<EditText>(R.id.value_company).text.toString(),
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_count).text.toString().toLong(),
                        imageAnnounced = byteArrayOf(0)
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }

    private fun saleTel(view: View) {
        val newLayout = layoutInflater.inflate(R.layout.layout_telephone,null)
        val arrayAdapterModelTel = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.model_tel))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_model).setAdapter(arrayAdapterModelTel)
        val arrayAdapterSizeTel = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.ozu_tel))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_size).setAdapter(arrayAdapterSizeTel)
        val arrayAdapterStateTel = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.state_zapjast))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_state).setAdapter(arrayAdapterStateTel)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_header).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_header).text.toString(),
                        contentAnnounced = "",
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }

    private fun saleZapjastTel(view: View) {
        val newLayout = layoutInflater.inflate(R.layout.layout_telephone_zapchast,null)
        val arrayAdapterStateZap = ArrayAdapter(requireContext(),R.layout.item_select_city,resources.getStringArray(R.array.state_zapjast))
        newLayout.findViewById<AutoCompleteTextView>(R.id.select_type_state).setAdapter(arrayAdapterStateZap)
        uploadImage(newLayout)
        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            if (view.findViewById<EditText>(R.id.value_header).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_price).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (view.findViewById<EditText>(R.id.value_tell).text.isEmpty()){
                Toast.makeText(requireContext(),"Запольните поля!",Toast.LENGTH_SHORT).show()
            } else if (imageValue.value == null){
                Toast.makeText(requireContext(),resources.getString(R.string.image),Toast.LENGTH_SHORT).show()
            } else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val announced = AnnouncedDbModel(
                        id = 0,
                        nameAnnounced = view.findViewById<EditText>(R.id.value_header).text.toString(),
                        contentAnnounced = "",
                        cityAnnounced = "",
                        telAnnounced = view.findViewById<EditText>(R.id.value_tell).text.toString(),
                        priceAnnounced = view.findViewById<EditText>(R.id.value_price).text.toString().toLong(),
                        imageAnnounced = requireContext().contentResolver.openInputStream(imageValue.value!!)!!.readBytes()
                    )
                    viewModel.insertAnnounced(
                        announced
                    )
                }
                activityNavController().navigate(R.id.to_main_from_add)
                Toast.makeText(requireContext(),resources.getString(R.string.succsess),Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<NestedScrollView>(R.id.add_announced).addView(newLayout)
    }


    private fun uploadImage(view: View){
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