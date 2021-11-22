SELECT
	T_HT_IMC.cd_imc,
	T_HT_IMC.cd_altura,
	T_HT_IMC.cd_peso,
	T_HT_IMC.cd_usuario,
	T_HT_IMC.dt_imc,
	T_HT_IMC.vl_imc,
	T_HT_USUARIO.nm_usuario,
	T_HT_USUARIO.dt_nascimento,
	T_HT_USUARIO.ds_genero,
	T_HT_USUARIO.ds_email,
	T_HT_USUARIO.ds_senha,
	T_HT_ALTURA.dt_altura,
	T_HT_ALTURA.vl_altura,
	T_HT_PESO.dt_peso,
	T_HT_PESO.vl_peso
FROM T_HT_IMC
LEFT JOIN T_HT_USUARIO
ON T_HT_IMC.cd_usuario = T_HT_USUARIO.cd_usuario
LEFT JOIN T_HT_ALTURA
ON T_HT_IMC.cd_altura = T_HT_ALTURA.cd_altura
LEFT JOIN T_HT_PESO
ON T_HT_IMC.cd_peso = T_HT_PESO.cd_peso
WHERE T_HT_IMC.cd_imc = ?
