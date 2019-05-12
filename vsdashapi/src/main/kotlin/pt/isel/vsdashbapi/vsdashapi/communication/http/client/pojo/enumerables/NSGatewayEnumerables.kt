package pt.isel.vsdashbapi.vsdashapi.communication.http.client.pojo.enumerables

object NSGatewayEnumerables{
    enum class NSGPersonality {
        NSG,
        NSGBR,
        NSGDUC
    }

    enum class PermittedAction {
        ALL, DEPLOY,
        EXTEND, INSTANTIATE,
        READ, USE
    }

    enum class NetworkAcceleration {
        NONE,
        PERFORMANCE
    }
    enum class InheritedSSHServiceState {
        DISABLED,
        ENABLED
    }

    enum class Functions {
        GATEWAY,
        UBR
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
        NSG_X200
    }

    enum class EntityScope {
        ENTERPRISE,
        GLOBAL
    }

    enum class DerivedSSHServiceState {
        INHERITED_DISABLED,
        INHERITED_ENABLED,
        INSTANCE_DISABLED,
        INSTANCE_ENABLED,
        UNKNOWN
    }

    enum class ConfigurationStatus {
        FAILURE,
        SUCCESS,
        UNKNOWN
    }

    enum class ConfigurationReloadState {
        APPLIED,
        FAILED_TO_APPLY,
        PENDING,
        SENT,
        UNKNOWN
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
        NSGATEWAY_ID,
        SERIAL_NUMBER,
        UUID
    }

    enum class TPMStatus {
        DISABLED,
        ENABLED_NOT_OPERATIONAL,
        ENABLED_OPERATIONAL,
        UNKNOWN
    }

    enum class SSHService {
        DISABLED,
        ENABLED,
        INHERITED
    }
}