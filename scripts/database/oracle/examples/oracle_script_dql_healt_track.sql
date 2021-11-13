-------------------------------------------------------------------------------
-- DQL
-------------------------------------------------------------------------------
-- Usuario
-------------------------------------------------------------------------------
SELECT
	cd_usuario,
	nm_usuario,
	dt_nascimento,
	ds_genero,
	ds_email,
	ds_senha
FROM T_HT_USUARIO
WHERE
	cd_usuario = ?;
-------------------------------------------------------------------------------
-- Peso
-------------------------------------------------------------------------------
SELECT
	cd_peso,
	dt_peso,
	vl_peso
FROM T_HT_PESO
WHERE
	cd_usuario = ?
ORDER BY dt_peso DESC;

SELECT
	dt_peso,
	vl_peso
FROM T_HT_PESO
WHERE
	cd_peso = ?
	AND cd_usuario = ?;
-------------------------------------------------------------------------------
-- Pressao
-------------------------------------------------------------------------------
SELECT
	cd_pressao,
	dt_pressao,
	vl_pressao_sistolica,
	vl_pressao_diastolica
FROM T_HT_PRESSAO
WHERE
	cd_usuario = ?
ORDER BY dt_pressao DESC;

SELECT
	dt_pressao,
	vl_pressao_sistolica,
	vl_pressao_diastolica
FROM T_HT_PRESSAO
WHERE
	cd_pressao = ?
	AND cd_usuario = ?;
-------------------------------------------------------------------------------
-- Sessao Treino
-------------------------------------------------------------------------------
SELECT
	cd_sessao_treino,
	cd_exercicio,
	dt_sessao_treino,
	dt_duracao,
	vl_calorias
FROM T_HT_SESSAO_TREINO
WHERE
	cd_usuario = ?
ORDER BY dt_sessao_treino DESC;

SELECT
	cd_exercicio,
	dt_sessao_treino,
	dt_duracao,
	vl_calorias
FROM T_HT_SESSAO_TREINO
WHERE
	cd_sessao_treino = ?
	AND cd_usuario = ?;
-------------------------------------------------------------------------------
-- Alimento
-------------------------------------------------------------------------------
SELECT
	cd_refeicao,
	cd_alimento,
	dt_refeicao,
	qtd_alimento,
	vl_calorias
FROM T_HT_REFEICAO
WHERE
	cd_usuario = ?
ORDER BY dt_refeicao;


SELECT
	cd_alimento,
	dt_refeicao,
	qtd_alimento,
	vl_calorias
FROM T_HT_REFEICAO
WHERE
	cd_refeicao = ?
	AND cd_usuario = ?;
-------------------------------------------------------------------------------
-- Join
-------------------------------------------------------------------------------
SELECT
	thtUsuario.nm_usuario,
	thtUsuario.dt_nascimento,
	thtUsuario.ds_genero,
	thtUsuario.ds_email,
	thtUsuario.ds_senha,
	max(thtPeso.vl_peso) AS vl_max_peso,
	max(thtPressao.vl_pressao_sistolica) AS vl_max_pressao_sistolica,
	max(thtPressao.vl_pressao_diastolica) AS vl_max_pressao_diastolica
FROM T_HT_USUARIO thtUsuario
LEFT JOIN T_HT_PESO thtPeso
	ON thtUsuario.cd_usuario = thtPeso.cd_usuario
LEFT JOIN T_HT_PRESSAO thtPressao
	ON thtUsuario.cd_usuario = thtPressao.cd_usuario
WHERE
	thtUsuario.cd_usuario = ?
GROUP BY
	thtUsuario.nm_usuario,
	thtUsuario.dt_nascimento,
	thtUsuario.ds_genero,
	thtUsuario.ds_email,
	thtUsuario.ds_senha;
