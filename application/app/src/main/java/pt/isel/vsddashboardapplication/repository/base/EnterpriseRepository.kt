package pt.isel.vsddashboardapplication.repository.base

import pt.isel.vsddashboardapplication.model.Enterprise

interface EnterpriseRepository : IBaseRepository<Enterprise> {

    fun setup(user: String, organization: String, vsd: String)
}