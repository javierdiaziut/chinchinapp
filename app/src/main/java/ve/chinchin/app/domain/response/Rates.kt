package gt.tribal.app.domain.response

data class Rates(
    val usd: GeneralRateBean,
    val eur: GeneralRateBean,
    val vef: GeneralRateBean,
    val cny: GeneralRateBean
)