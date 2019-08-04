package pt.isel.vsddashboardapplication.activities.fragment.regular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.R
import pt.isel.vsddashboardapplication.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding : FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        binding.grid.useDefaultMargins = true
        binding.nsgListOption.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_NSGatewayListFragment) }
        binding.vspListOption.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_vspParentFragment) }
        binding.vrsListOption.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_vrsListFragment)}
        binding.settings.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_settingsFragment) }
        return binding.root
    }

}
