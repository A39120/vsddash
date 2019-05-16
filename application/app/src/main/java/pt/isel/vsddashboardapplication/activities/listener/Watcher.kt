package pt.isel.vsddashboardapplication.activities.listener

import android.text.Editable
import android.text.TextWatcher

/**
 * Watcher
 */
class Watcher(private val function: (Editable?) -> Unit) : TextWatcher {

    override fun afterTextChanged(s: Editable?) { function.invoke(s) }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
}
