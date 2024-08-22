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
           CategorySelect( 5,"Электроника"),
           CategorySelect( 6,"Все для дома")
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
              CategorySelectSecond(1,"Легковые автомобили"),
              CategorySelectSecond(2,"Мото транспорт"),
              CategorySelectSecond(3,"Коммерческий транспорт"),
              CategorySelectSecond(4,"Запчасти и принадлежности"),
              CategorySelectSecond(5,"Автосервис и ремонт"),
              CategorySelectSecond(6,"Другой транспорт"),
       )

      private fun vacancies() = listOf(
              CategorySelectSecond(1,"HR,кадры"),
              CategorySelectSecond(2,"IT,телеком,компьтеры"),
              CategorySelectSecond(3,"Банки,лизинг"),
              CategorySelectSecond(4,"Бухгалтерия,финансы,юристы"),
              CategorySelectSecond(5,"Государственные службы"),
              CategorySelectSecond(6,"Красота,фитнес,спорт"),
              CategorySelectSecond(7,"Маркетинг,фармация"),
              CategorySelectSecond(8,"Охрана,безопасность"),
              CategorySelectSecond(9,"Продажи,розничная торговля"),
              CategorySelectSecond(10,"Руководители"),
              CategorySelectSecond(11,"СМИ,издательство"),
              CategorySelectSecond(12,"Транспорт,логистика"),
              CategorySelectSecond(13,"СМИ,издательство"),
              CategorySelectSecond(14,"Работа за рубежом"),
       )

      private fun telephone() = listOf(
              CategorySelectSecond(1,"Мобильные телефоны"),
              CategorySelectSecond(2,"Аксессуары для телефонов"),
              CategorySelectSecond(3,"Ремонт и сервис телефонов"),
              CategorySelectSecond(4,"Запчасти и инструменты"),
              CategorySelectSecond(5,"Другая техника связи"),
       )

     private  fun elektronika() = listOf(
              CategorySelectSecond(1,"Фото и видеокамеры"),
              CategorySelectSecond(2,"TV,DVD и видео"),
              CategorySelectSecond(3,"Аудио и стерео"),
              CategorySelectSecond(4,"Техника для дома и кухни"),
              CategorySelectSecond(5,"Другая техника"),
              CategorySelectSecond(6,"Климатическая техника")
       )

     private  fun forHome() = listOf(
              CategorySelectSecond(1,"Мебель"),
              CategorySelectSecond(2,"Текстиль и интерьер"),
              CategorySelectSecond(3,"Пищевые продукты"),
              CategorySelectSecond(4,"Посуда и кухонная утварь"),
              CategorySelectSecond(5,"Сад и огород"),
              CategorySelectSecond(6,"Сейфы"),
              CategorySelectSecond(7,"Другие товары для дома"),
       )

       fun showSelectCategory(id:Int):List<CategorySelectSecond> {
              return when(id){
                     1 -> house()
                     2 -> cars()
                     3 -> vacancies()
                     4 -> telephone()
                     5 -> elektronika()
                     6 -> forHome()
                     else -> emptyList()
              }
       }
}