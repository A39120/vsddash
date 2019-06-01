package pt.isel.vsddashboardapplication.viewmodel

import androidx.lifecycle.ViewModel;
import pt.isel.vsddashboardapplication.repository.NSGatewayRepository

class NsgatewayViewModel(id: String, repo: NSGatewayRepository) : ViewModel() {
    val nsg = repo.get(id)
    val str = "Hello"
}
