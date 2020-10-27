package com.task.di

//import com.memes.funny.ui.component.details.DetailsActivity
import com.task.ui.component.login.LoginActivity
import com.memes.funny.ui.component.recipes.MemeListActivity
import com.memes.funny.ui.component.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeHomeActivity(): MemeListActivity

    /*@ContributesAndroidInjector()
    abstract fun contributeDetailsActivity(): DetailsActivity
*/
    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity
}
