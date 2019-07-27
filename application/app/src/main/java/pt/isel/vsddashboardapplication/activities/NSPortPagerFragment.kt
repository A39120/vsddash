package pt.isel.vsddashboardapplication.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentPagerAdapter
import pt.isel.vsddashboardapplication.activities.adapter.NSPortViewPagerAdapter
import pt.isel.vsddashboardapplication.activities.base.BasePagerFragment

class NSPortPagerFragment : BasePagerFragment() {
    companion object {
        private const val TAG = "ACT/NSPORT"

        private const val NSG_ID = "nsgId"
        private const val PORT_ID = "portId"

        /**
         * Starts the NSPortPagerFragment
         * @param portId: the ID of the port the activity is focused on;
         * @param nsgId: the ID of the parent NSG, that the port belongs to;
         * @param context: Context that starts the activity
         */
        fun startInstance(portId: String, nsgId: String, context: Context) {
            Log.d(TAG, "Starting NSPort activity")
            val bundle = Bundle()
            bundle.apply {
                putString(PORT_ID, portId)
                putString(NSG_ID, nsgId)
            }
            val intent = Intent(context, NSPortPagerFragment::class.java)
            intent.putExtras(bundle)
            startActivity(context, intent, null)
        }

    }

    override fun getPager(): FragmentPagerAdapter = NSPortViewPagerAdapter(this.childFragmentManager)

    fun getNsgId() = arguments?.getString(NSG_ID)
    fun getPortId() = arguments?.getString(PORT_ID)

}