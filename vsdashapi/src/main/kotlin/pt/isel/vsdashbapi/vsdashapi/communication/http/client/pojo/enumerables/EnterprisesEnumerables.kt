package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables

object EnterprisesEnumerables {

    enum class FlowCollectionEnabled {
        DISABLED,
        ENABLED

    }

    enum class EncryptionManagementMode {
        DISABLED,
        MANAGED

    }

    enum class AvatarType {
        BASE64,
        COMPUTEDURL,
        URL
    }

    enum class AllowedForwardingMode {
        DISABLED,
        LOCAL_AND_REMOTE,
        LOCAL_ONLY
    }

    enum class AllowedForwardingClasses {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        NONE
    }
}