package com.asoscodetest.ui.base

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.asoscodetest.AsosApplication
import com.asoscodetest.R
import com.asoscodetest.di.AppComponent

/**
 * Created by David C. on 18/11/2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AsosApplication).appComponent
    }

    internal fun notifyWithAction(viewContainer: View, message: String, @StringRes actionText: Int, action: () -> Any) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(ContextCompat.getColor(applicationContext,
                R.color.colorAccent))
        snackBar.show()
    }

}