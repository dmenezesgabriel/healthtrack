-- DML
-------------------------------------------------------------------------------
-- Usuario
-------------------------------------------------------------------------------
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
  );

UPDATE T_HT_USUARIO
  SET nm_usuario = ?,
  dt_nascimento = TO_DATE(?, 'DD/MM/YYYY'),
  ds_genero = ?,
  ds_email = ?,
  ds_senha = ?
  WHERE cd_usuario = ?;


DELETE FROM T_HT_USUARIO
  WHERE cd_usuario = ?;
-------------------------------------------------------------------------------
-- Pressao
-------------------------------------------------------------------------------
INSERT INTO T_HT_PRESSAO (
  cd_pressao,
  cd_usuario,
  dt_pressao,
  vl_pressao_sistolica,
  vl_pressao_diastolica
)
VALUES (
  SEQ_PRESSAO.NEXTVAL,
  ?,
  TO_DATE(?, 'DD/MM/YYYY'),
  ?,
  ?
  );

UPDATE T_HT_PRESSAO
  SET cd_usuario = ?,
  dt_pressao = TO_DATE(?, 'DD/MM/YYYY'),
  vl_pressao_sistolica = ?,
  vl_pressao_diastolica = ?
  WHERE cd_pressao = ?;

DELETE FROM T_HT_PRESSAO
  WHERE cd_pressao = ?;
-------------------------------------------------------------------------------
-- Peso
-------------------------------------------------------------------------------
INSERT INTO T_HT_PESO (
  cd_peso,
  cd_usuario,
  dt_peso,
  vl_peso,
)
VALUES (
  SEQ_PESO.NEXTVAL,
  ,
  ?,
  TO_DATE(?, 'DD/MM/YYYY'),
  ?
  );

UPDATE T_HT_PESO
  SET cd_usuario = ?,
  dt_peso = TO_DATE(?, 'DD/MM/YYYY'),
  vl_peso = ?,
  WHERE cd_peso = ?;

DELETE FROM T_HT_PESO
  WHERE cd_peso = ?;
-------------------------------------------------------------------------------
-- Altura
-------------------------------------------------------------------------------
INSERT INTO T_HT_ALTURA (
  cd_altura,
  cd_usuario,
  dt_altura,
  vl_altura,
)
VALUES (
  SEQ_ALTURA.NEXTVAL,
  ?,
  TO_DATE(?, 'DD/MM/YYYY'),
  ?
  );

UPDATE T_HT_ALTURA
  SET cd_usuario = ?,
  dt_altura = TO_DATE(?, 'DD/MM/YYYY'),
  vl_altura = ?,
  WHERE cd_altura = ?;

DELETE FROM T_HT_ALTURA
  WHERE cd_altura = ?;
-------------------------------------------------------------------------------
-- Sessao Treino
-------------------------------------------------------------------------------
INSERT INTO T_HT_SESSAO_TREINO (
  cd_sessao_treino,
  cd_usuario,
  cd_exercicio,
  dt_sessao_treino,
  dt_duracao,
  vl_calorias,
)
VALUES (
  SEQ_SESSAO_TREINO.NEXTVAL,
  ?,
  ?,
  TO_DATE(?, 'DD/MM/YYYY'),
  TO_DATE(?, 'DD/MM/YYYY'),
  ?
  );

UPDATE T_HT_SESSAO_TREINO
  SET cd_usuario = ?,
  cd_exercicio = ?,
  dt_sessao_treino = TO_DATE(?, 'DD/MM/YYYY'),
  dt_duracao = TO_DATE(?, 'DD/MM/YYYY'),
  vl_calorias = ?,
  WHERE cd_sessao_treino = ?;

DELETE FROM T_HT_SESSAO_TREINO
  WHERE cd_sessao_treino = ?;
-------------------------------------------------------------------------------
-- Refeicao
-------------------------------------------------------------------------------
INSERT INTO T_HT_REFEICAO (
  cd_refeicao,
  cd_usuario,
  cd_alimento,
  dt_refeicao,
  dt_duracao,
  qtd_alimento,
  vl_calorias,
)
VALUES (
  SEQ_REFEICAO.NEXTVAL,
  ?,
  ?,
  TO_DATE(?, 'DD/MM/YYYY'),
  ?,
  ?
  );

UPDATE T_HT_REFEICAO
  SET cd_usuario = ?,
  cd_alimento = ?,
  dt_refeicao = TO_DATE(?, 'DD/MM/YYYY'),
  qtd_alimento = ?,
  vl_calorias = ?,
  WHERE cd_refeicao = ?;

DELETE FROM T_HT_REFEICAO
  WHERE cd_refeicao = ?;
-------------------------------------------------------------------------------
-- Exercicio
-------------------------------------------------------------------------------
INSERT INTO T_HT_EXERCICIO (
  cd_exercicio,
  nm_exercicio,
  vl_calorias,
)
VALUES (
  SEQ_EXERCICIO.NEXTVAL,
  ?,
  ?
  );

UPDATE T_HT_EXERCICIO
  SET nm_exercicio = ?,
  vl_calorias = ?,
  WHERE cd_exercicio = ?;

DELETE FROM T_HT_EXERCICIO
  WHERE cd_exercicio = ?;
-------------------------------------------------------------------------------
-- Alimento
-------------------------------------------------------------------------------
INSERT INTO T_HT_ALIMENTO (
  cd_alimento,
  nm_alimento,
  vl_calorias,
)
VALUES (
  SEQ_ALIMENTO.NEXTVAL,
  ?,
  ?
  );

UPDATE T_HT_ALIMENTO
  SET nm_alimento = ?,
  vl_calorias = ?,
  WHERE cd_alimento = ?;

DELETE FROM T_HT_ALIMENTO
  WHERE cd_alimento = ?;
