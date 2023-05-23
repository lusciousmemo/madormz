package kenji.pilotprojects.madormz.feature

import android.content.Context
import android.widget.Toast
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Named

class DemoClass @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    fun getContextPackageName(): String {
        return context.packageName
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}