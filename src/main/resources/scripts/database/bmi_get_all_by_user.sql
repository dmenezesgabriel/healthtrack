SELECT
	cd_imc,
	cd_altura,
	cd_peso,
	cd_usuario,
	dt_imc,
	vl_imc
FROM T_HT_IMC
WHERE cd_usuario = ?
