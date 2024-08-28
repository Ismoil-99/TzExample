package com.example.tzexample.presentation.ui.main.add.addannounced

import androidx.lifecycle.ViewModel
import com.example.tzexample.data.locale.db.AnnouncedDbModel
import com.example.tzexample.data.locale.db.RepositoryDb
import com.example.tzexample.data.models.Category
import com.example.tzexample.data.models.CategorySelect
import com.example.tzexample.data.models.CategorySelectSecond
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAnnouncedViewModel @Inject constructor(private val repositoryDb: RepositoryDb) :ViewModel() {
       suspend  fun insertAnnounced(announcedDbModel: AnnouncedDbModel) = repositoryDb.insertMedicine(announcedDbModel)

       fun showCategories() = listOf(
              CategorySelect( 1,"Недвижимость"),
           CategorySelect(2,"Транспорт"),
           CategorySelect( 3,"Вакансии"),
           CategorySelect(4,"Телефоны"),
       )

      private fun house() = listOf(
              CategorySelectSecond(1,"Аренда комнат"),
              CategorySelectSecond(2,"Аренда квартир"),
              CategorySelectSecond(3,"Аренда для дома"),
              CategorySelectSecond(4,"Аренда дач"),
              CategorySelectSecond(5,"Продажа квартир"),
              CategorySelectSecond(6,"Продажа домов(хавли) и дач"),
              CategorySelectSecond(7,"Продажа,аренда гаражей и стоянок")
       )

     private  fun cars() = listOf(
              CategorySelectSecond(8,"Легковые автомобили"),
              CategorySelectSecond(9,"Мото транспорт"),
              CategorySelectSecond(10,"Запчасти и принадлежности"),
         )

      private fun vacancies() = listOf(
              CategorySelectSecond(11,"HR,кадры"),
              CategorySelectSecond(12,"IT,телеком,компьтеры"),
              CategorySelectSecond(13,"Банки,лизинг"),
              CategorySelectSecond(14,"Бухгалтерия,финансы,юристы"),
              CategorySelectSecond(15,"Государственные службы"),
              CategorySelectSecond(16,"Охрана,безопасность"),
              CategorySelectSecond(17,"Продажи,розничная торговля"),
              CategorySelectSecond(18,"Руководители"),
              CategorySelectSecond(19,"СМИ,издательство"),
              CategorySelectSecond(20,"Транспорт,логистика"),
       )

      private fun telephone() = listOf(
              CategorySelectSecond(21,"Мобильные телефоны"),
              CategorySelectSecond(22,"Аксессуары для телефонов"),
       )


       fun showSelectCategory(id:Int):List<CategorySelectSecond> {
              return when(id){
                     1 -> house()
                     2 -> cars()
                     3 -> vacancies()
                     4 -> telephone()
                     else -> emptyList()
              }
       }
}