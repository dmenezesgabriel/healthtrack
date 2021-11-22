SELECT
	cd_peso,
	cd_usuario,
	dt_peso,
	vl_peso
FROM T_HT_PESO
WHERE cd_usuario = ?
