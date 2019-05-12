package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables

object VRSEnumerables {

    enum class VscCurrentState  {
        PRIMARY,
        SECONDARY
    }

    enum class VscConfigState  {
        PRIMARY,
        SECONDARY
    }

    enum class Status  {
        ADMIN_DOWN,
        DOWN,
        UP
    }

    enum class Role  {
        MASTER,
        NONE,
        SLAVE
    }

    enum class Personality  {
        HARDWARE_VTEP,
        NONE,
        NSG,
        NSGBR,
        NSGDUC,
        NUAGE_210_WBX_32_Q,
        NUAGE_210_WBX_48_S,
        VRS,
        VRSB,
        VRSG
    }

    enum class LicensedState  {
        LICENSED,
        UNLICENSED
    }

    enum class HypervisorConnectionState  {
        ADMIN_DOWN,
        DOWN,
        UP
    }

    enum class EntityScope  {
        ENTERPRISE,
        GLOBAL
    }

    enum class ClusterNodeRole  {
        NONE,
        PRIMARY,
        SECONDARY
    }

    enum class JSONRPCConnectionState  {
        ADMIN_DOWN,
        DOWN,
        UP
    }

}