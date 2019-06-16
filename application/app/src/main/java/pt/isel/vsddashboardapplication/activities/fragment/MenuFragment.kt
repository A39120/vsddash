package pt.isel.vsddashboardapplication.activities.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import pt.isel.vsddashboardapplication.databinding.MenuFragmentBinding
import pt.isel.vsddashboardapplication.R



class MenuFragment : Fragment() {


    private lateinit var binding : MenuFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false)

        binding.nsgListOption.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_menuFragment_to_NSGatewayListFragment)
        }

        return binding.root
    }


}
