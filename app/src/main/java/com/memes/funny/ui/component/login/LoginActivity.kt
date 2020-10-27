package com.task.ui.component.login

import com.task.ui.base.BaseActivity


class LoginActivity : BaseActivity() {
    override fun initializeViewModel() {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    override fun initViewBinding() {
        TODO("Not yet implemented")
    }
    /*  @Inject
      lateinit var viewModelFactory: ViewModelFactory

      @Inject
      lateinit var loginViewModel: LoginViewModel
      private lateinit var binding: LoginActivityBinding


      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding.login.setOnClickListener { doLogin() }
      }

      override fun initViewBinding() {
          binding = LoginActivityBinding.inflate(layoutInflater)
          val view = binding.root
          setContentView(view)
      }

      override fun initializeViewModel() {
          loginViewModel = viewModelFactory.create(loginViewModel::class.java)
      }


      override fun observeViewModel() {
          observe(loginViewModel.loginLiveData, ::handleLoginResult)
          observeSnackBarMessages(loginViewModel.showSnackBar)
          observeToast(loginViewModel.showToast)
      }

      private fun doLogin() {
          loginViewModel.doLogin(binding.username.text.trim().toString(),
                  binding.password.text.toString())
      }

      private fun handleLoginResult(status: Resource<LoginResponse>) {
          when (status) {
              is Resource.Loading -> binding.loaderView.toVisible()
              is Resource.Success -> status.data?.let {
                  binding.loaderView.toGone()
                  navigateToMainScreen()
              }
              is Resource.DataError -> {
                  binding.loaderView.toGone()
                  status.errorCode?.let { loginViewModel.showToastMessage(it) }
              }
          }
      }

      private fun navigateToMainScreen() {
          val nextScreenIntent = Intent(this, RecipesListActivity::class.java)
          startActivity(nextScreenIntent)
          finish()
      }

      private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
          binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
      }

      private fun observeToast(event: LiveData<SingleEvent<Any>>) {
          binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
      }*/
}
