UPDATE T_HT_PRESSAO
  SET cd_usuario = ?,
  dt_pressao = ?,
	vl_pressao_sistolica = ?,
	vl_pressao_diastolica = ?
WHERE cd_pressao = ?
