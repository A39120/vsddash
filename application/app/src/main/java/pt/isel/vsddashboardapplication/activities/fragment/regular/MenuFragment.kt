package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding : FragmentMenuBinding

    private val args : MenuFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        val enterpriseId = args.enterpriseId
        binding.nsgListOption.setOnClickListener {
            val directions = MenuFragmentDirections.actionMenuFragmentToNSGatewayListFragment(enterpriseId)
            Navigation.findNavController(it).navigate(directions)
        }
        binding.vspListOption.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_vspParentFragment) }
        binding.vrsListOption.setOnClickListener {
            val directions = MenuFragmentDirections.actionMenuFragmentToVrsListFragment(null)
            Navigation.findNavController(it).navigate(directions)
        }
        binding.settings.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_settingsFragment) }
        return binding.root
    }

}
