package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables


object GatewayEnumerables {

    enum class PermittedAction {
        ALL,
        DEPLOY,
        EXTEND,
        INSTANTIATE,
        READ,
        USE
    }

    enum class BootstrapStatus {
        ACTIVE,
        CERTIFICATE_SIGNED,
        INACTIVE,
        NOTIFICATION_APP_REQ_ACK,
        NOTIFICATION_APP_REQ_SENT
    }

    enum class ZFBMatchAttribute {
        HOSTNAME,
        IP_ADDRESS,
        MAC_ADDRESS,
        NONE,
        SERIAL_NUMBER,
        UUID
    }


    enum class Personality {
        DC7X50,
        EVDF,
        EVDFB,
        HARDWARE_VTEP,
        NETCONF_7X50,
        NETCONF_THIRDPARTY_HW_VTEP,
        NUAGE_210_WBX_32_Q,
        NUAGE_210_WBX_48_S,
        OTHER,
        VDFG,
        VRSB,
        VRSG,
        VSA,
        VSG
    }

    enum class Family {
        ANY,
        NSG_AMI,
        NSG_AZ,
        NSG_C,
        NSG_E,
        NSG_E200,
        NSG_E300,
        NSG_V,
        NSG_X,
        NSG_X200,
        VRS
    }

}
