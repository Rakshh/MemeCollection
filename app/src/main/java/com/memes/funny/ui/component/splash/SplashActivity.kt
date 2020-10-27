package com.memes.funny.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.memes.funny.databinding.SplashLayoutBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.SPLASH_DELAY
import com.memes.funny.ui.component.recipes.MemeListActivity
import com.task.ui.component.splash.SplashViewModel
import javax.inject.Inject


class SplashActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var splashViewModel: SplashViewModel

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        splashViewModel = viewModelFactory.create(splashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {

    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, MemeListActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }


}
