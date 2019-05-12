package pt.isel.vsdashbapi.vsdashapi.authentication.cache

import net.nuagenetworks.vspk.v5_0.Me
import org.apache.activemq.util.LRUCache
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component()
class SessionMapper {

    private val logger = LoggerFactory.getLogger(SessionMapper::class.java)

    private val cache = LRUCache<String, Me>()
    //private val root = LinkedList<VsdApi>()

    private val users = LinkedList<Me>()

    fun addUser(api: String, organization: String, session: Me) {
        logger.info("Adding user ${session.userName} from $organization (API: $api)")

        users.add(session)
        /*
        if(!root.any{ it.api == api}){
            root.add(VsdApi(api))
        }

        root.find { it.api == api }
            .run { this?.addUser(organization, session) }
        */
    }


    fun getUser(api: String? = null, organization: String? = null, apiKey: String) : Me? {
        logger.debug("Getting user with API Key: $apiKey")
        return users.find { it.apiKey == apiKey }
        /*
        return root
                .filter { api == null || it.api == api }
                .flatMap{ it.organizations }
                .filter { organization == null || it.organization == organization }
                .flatMap { it.users }
                .find { it.apiKey == apiKey }
        */
    }

    fun removeUser(api: String? = null, organization: String? = null, apiKey: String) {
        logger.debug("Removing user with API Key: $apiKey")
        users.remove(users.find { apiKey == it.apiKey })

        /*
        val api = root.filter { api == null || api == it.api }

        val org = api
                .flatMap { it.organizations }
                .filter { organization == null || it.organization == organization }

        val user = org
                .flatMap { it.users }
                .filter { it.apiKey == apiKey }
        */
    }

}