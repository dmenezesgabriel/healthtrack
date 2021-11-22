SELECT
	T_HT_PESO.cd_peso,
	T_HT_PESO.cd_usuario,
	T_HT_PESO.dt_peso,
	T_HT_PESO.vl_peso,
	T_HT_USUARIO.nm_usuario,
	T_HT_USUARIO.dt_nascimento,
	T_HT_USUARIO.ds_genero,
	T_HT_USUARIO.ds_email,
	T_HT_USUARIO.ds_senha
FROM T_HT_PESO
LEFT JOIN T_HT_USUARIO
ON T_HT_PESO.cd_usuario = T_HT_USUARIO.cd_usuario
WHERE T_HT_PESO.cd_usuario = ?
