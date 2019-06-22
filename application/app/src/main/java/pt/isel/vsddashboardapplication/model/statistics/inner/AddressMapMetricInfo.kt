package pt.isel.vsddashboardapplication.repository.pojo.statistics.inner


import com.squareup.moshi.Json

data class AddressMapMetricInfo(
    @Json(name = "masq_egress_byte_cnt") val masqEgressByteCnt: Int? = 0,
    @Json(name = "masq_egress_pkt_cnt") val masqEgressPktCnt: Int? = 0,
    @Json(name = "masq_ingress_byte_cnt") val masqIngressByteCnt: Int? = 0,
    @Json(name = "masq_ingress_pkt_cnt") val masqIngressPktCnt: Int? = 0
)