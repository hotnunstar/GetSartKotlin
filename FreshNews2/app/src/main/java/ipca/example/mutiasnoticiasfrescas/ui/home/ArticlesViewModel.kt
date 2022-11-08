package ipca.example.mutiasnoticiasfrescas.ui.home

import androidx.lifecycle.*
import ipca.example.mutiasnoticiasfrescas.Article
import ipca.example.mutiasnoticiasfrescas.Backend

class ArticlesViewModel : ViewModel() {

    private val category : MutableLiveData<String> = MutableLiveData()

    fun setCategory(category: String) {
        val update = category
        if (this.category.value == update) return
        this.category.value = update
    }

    var getArticles : LiveData<List<Article>> = Transformations.switchMap(category){
        Backend.fetchTopHeadlines("pt",it)
    }

}