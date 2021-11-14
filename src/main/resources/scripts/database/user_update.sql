UPDATE T_HT_USUARIO
  SET nm_usuario = ?,
  dt_nascimento = TO_DATE(?, 'DD/MM/YYYY'),
  ds_genero = UPPER(?),
  ds_email = ?,
  ds_senha = ?
WHERE cd_usuario = ?
