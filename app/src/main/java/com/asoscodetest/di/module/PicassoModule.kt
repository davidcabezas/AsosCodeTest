package com.asoscodetest.di.module

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David C. on 20/11/2018.
 */
@Module(includes = [ContextModule::class])
class PicassoModule {

    @Provides
    @Singleton
    fun picasso(context: Context): Picasso = Picasso.Builder(context).build()

}