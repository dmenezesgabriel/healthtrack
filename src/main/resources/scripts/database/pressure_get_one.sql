SELECT
	T_HT_PRESSAO.cd_pressao,
	T_HT_PRESSAO.cd_usuario,
	T_HT_PRESSAO.dt_pressao,
	T_HT_PRESSAO.vl_pressao_sistolica,
	T_HT_PRESSAO.vl_pressao_diastolica,
	T_HT_USUARIO.nm_usuario,
	T_HT_USUARIO.dt_nascimento,
	T_HT_USUARIO.ds_genero,
	T_HT_USUARIO.ds_email,
	T_HT_USUARIO.ds_senha
FROM T_HT_PRESSAO
LEFT JOIN T_HT_USUARIO
ON T_HT_PRESSAO.cd_usuario = T_HT_USUARIO.cd_usuario
WHERE T_HT_PRESSAO.cd_pressao = ?