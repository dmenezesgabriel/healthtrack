SELECT
	T_HT_ALTURA.cd_altura,
	T_HT_ALTURA.cd_usuario,
	T_HT_ALTURA.dt_altura,
	T_HT_ALTURA.vl_altura,
	T_HT_USUARIO.nm_usuario,
	T_HT_USUARIO.dt_nascimento,
	T_HT_USUARIO.ds_genero,
	T_HT_USUARIO.ds_email,
	T_HT_USUARIO.ds_senha
FROM T_HT_ALTURA
LEFT JOIN T_HT_USUARIO
ON T_HT_ALTURA.cd_usuario = T_HT_USUARIO.cd_usuario