package com.memes.funny.ui.component.recipes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import com.memes.funny.R
import com.memes.funny.databinding.HomeActivityBinding
import com.task.data.Resource
import com.task.data.dto.recipes.DictionaryEntity
import com.task.data.dto.recipes.Recipes
import com.task.data.error.SEARCH_ERROR
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.memes.funny.ui.component.recipes.adapter.MemesAdapter
import com.task.utils.*
import javax.inject.Inject


class MemeListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    @Inject
    lateinit var memeListViewModel: MemeListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var memesAdapter: MemesAdapter

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        memeListViewModel = viewModelFactory.create(MemeListViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.meme)
        val layoutManager = LinearLayoutManager(this)
        binding.rvMemeList.layoutManager = layoutManager
        binding.rvMemeList.setHasFixedSize(true)

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        val request = AdRequest.Builder().build()
        binding.adView.loadAd(request)

        memeListViewModel.getRecipes()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actions, menu)
        // Associate searchable configuration with the SearchView
        // val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        // searchView.queryHint = getString(R.string.search_by_name)
        //val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        /* searchView.apply {
             setSearchableInfo(searchManager.getSearchableInfo(componentName))
         }*/
        /* searchView.setOnQueryTextListener(object : OnQueryTextListener {
             override fun onQueryTextSubmit(query: String): Boolean {
                 handleSearch(query)
                 return false
             }

             override fun onQueryTextChange(newText: String): Boolean {
                 return false
             }
         })*/
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> memeListViewModel.getRecipes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleSearch(query: String) {
        if (query.isNotEmpty()) {
            binding.pbLoading.visibility = VISIBLE
            memeListViewModel.onSearchClick(query)
        }
    }


    private fun bindListData(recipes: Recipes) {
        if (!(recipes.recipesList.isNullOrEmpty())) {
            memesAdapter = MemesAdapter(memeListViewModel, recipes.recipesList)
            binding.rvMemeList.adapter = memesAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<DictionaryEntity>) {
     /*   navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(RECIPE_ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }*/
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
        memeListViewModel.showToastMessage(SEARCH_ERROR)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rvMemeList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvMemeList.toGone()
    }


    private fun showSearchResult(recipesItem: DictionaryEntity) {
        memeListViewModel.openRecipeDetails(recipesItem)
        binding.pbLoading.toGone()
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        binding.pbLoading.toGone()
    }

    private fun handleRecipesList(status: Resource<Recipes>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { memeListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(memeListViewModel.memesLiveData, ::handleRecipesList)
        observe(memeListViewModel.recipeSearchFound, ::showSearchResult)
        observe(memeListViewModel.noSearchFound, ::noSearchResult)
        observeEvent(memeListViewModel.openRecipeDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(memeListViewModel.showSnackBar)
        observeToast(memeListViewModel.showToast)

    }
}
