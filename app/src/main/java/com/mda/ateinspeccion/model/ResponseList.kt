package com.mda.ateinspeccion.model

data class ResponseMysqlTemp(
    val datos: List<ResponseDataSQL_ind_temp>,
)
data class ResponseMysqlInd(
    val datos: List<ResponseDataSQL_ind_temp>,
)
data class ResponseGoogleRiesgoD2023(
    val datos: List<ResponseDataGoogleSheeets_riesgo_desastre>,
)
data class ResponseMySQLRiesgoD2022(
    val datos: List<ResponseDataSQL_ind_temp>,
)
data class ResponseGoogleSGHUE(
    val datos: List<ResponseDataGoogleSheeets_sghue>,
)
