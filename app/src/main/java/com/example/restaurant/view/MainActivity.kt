package com.example.restaurant.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.example.restaurant.BuildConfig
import com.example.restaurant.R
import com.example.restaurant.model.MenuItem
import com.example.restaurant.model.Restaurant
import com.example.restaurant.model.di.RestaurantViewModelFactory
import com.example.restaurant.model.states.StatesAPI
import com.example.restaurant.utils.connectionChecking.ConnectionChecker
import com.example.restaurant.viewModel.RestaurantViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var viewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVM()
        initView()
        callAPI()
    }

    private fun initVM() {
        viewModel = ViewModelProvider(this, RestaurantViewModelFactory.newInstance())
            .get(RestaurantViewModel::class.java)
    }

    private fun initView() {
        connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        startObservingSuccessState()
        startObservingErrorState()
    }

    private fun callAPI() {
        if (!viewModel.hasASuccessfulCallAlreadyBeenMade()) {
            if (ConnectionChecker.handleInternetConnectionAvailability(connectivityManager)) {
                viewModel.getRestaurant(BuildConfig.API_KEY)
            } else {
                showErrorToast(R.string.internet_connection_error)
            }
        }
    }

    private fun startObservingSuccessState() {
        viewModel.successLiveData.observe(this, {
            handleSuccess(it)
        })
    }

    private fun startObservingErrorState() {
        viewModel.errorLiveData.observe(this, {
            handleError(it)
        })
    }

    private fun handleSuccess(result: Restaurant) {
        val tabs = arrayListOf<String>()
        val itemsList = arrayListOf<ArrayList<MenuItem>>()
        restaurantName.text = result.result.restaurantName
        result.result.menus.map { menu ->
            menuName.text = menu.menuName
            menu.menuSections.forEach {
                tabs.add(it.sectionName)
                itemsList.add(it.menuItems)
            }
        }

        val pagerAdapter = RestaurantPagerAdapter(this, itemsList)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
        hideLoading()
    }

    private fun handleError(error: StatesAPI) {
        val errorMessage = if (error is StatesAPI.ServerErrorViewState) {
            R.string.server_error
        } else {
            R.string.client_error
        }
        hideLoading()
        showErrorToast(errorMessage)
    }

    private fun showErrorToast(@StringRes errorMessage: Int) {
        Toast.makeText(applicationContext, getString(errorMessage), Toast.LENGTH_LONG).show()
    }

    private fun hideLoading() {
        loading.isGone = true
    }
}
