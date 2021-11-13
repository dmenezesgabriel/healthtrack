INSERT INTO T_HT_USUARIO (
  cd_usuario,
  nm_usuario,
  dt_nascimento,
  ds_genero,
  ds_email,
  ds_senha
)
VALUES (
  SEQ_USUARIO.NEXTVAL,
  ?,
  TO_DATE(?, 'DD/MM/YYYY'),
  UPPER(?),
  ?,
  ?
  )