-------------------------------------------------------------------------------
-- DDL
-------------------------------------------------------------------------------
-- Delete tables
-------------------------------------------------------------------------------
DROP TABLE IF EXISTS T_HT_USUARIO;
DROP TABLE IF EXISTS T_HT_PRESSAO;
DROP TABLE IF EXISTS T_HT_PESO;
DROP TABLE IF EXISTS T_HT_ALTURA;
DROP TABLE IF EXISTS T_HT_SESSAO_TREINO;
DROP TABLE IF EXISTS T_HT_REFEICAO;
DROP TABLE IF EXISTS T_HT_EXERCICIO;
DROP TABLE IF EXISTS T_HT_ALIMENTO;
-------------------------------------------------------------------------------
-- Create tables and constraints
-------------------------------------------------------------------------------
-- Usuario
-------------------------------------------------------------------------------
CREATE TABLE T_HT_USUARIO (
	cd_usuario SERIAL,
	nm_usuario VARCHAR NOT NULL,
	dt_nascimento DATE NOT NULL,
	ds_genero VARCHAR NOT NULL,
	ds_email VARCHAR NOT NULL,
	ds_senha VARCHAR NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_USUARIO
	ADD CONSTRAINT PK_HT_USUARIO PRIMARY KEY (cd_usuario);
-------------------------------------------------------------------------------
-- Pressao
-------------------------------------------------------------------------------
CREATE TABLE T_HT_PRESSAO (
	cd_pressao SERIAL,
	cd_usuario INT NOT NULL,
	dt_pressao DATE NOT NULL,
	vl_pressao_sistolica INT NOT NULL,
	vl_pressao_diastolica INT NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_PRESSAO
	ADD CONSTRAINT PK_HT_PRESSAO PRIMARY KEY (cd_pressao);
-------------------------------------------------------------------------------
-- Peso
-------------------------------------------------------------------------------
CREATE TABLE T_HT_PESO (
	cd_peso SERIAL,
	cd_usuario INT NOT NULL,
	dt_peso DATE NOT NULL,
	vl_peso INT NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_PESO
	ADD CONSTRAINT PK_HT_PESO PRIMARY KEY (cd_peso);
-------------------------------------------------------------------------------
-- Altura
-------------------------------------------------------------------------------
CREATE TABLE T_HT_ALTURA (
	cd_altura SERIAL,
	cd_usuario INT NOT NULL,
	dt_altura DATE NOT NULL,
	vl_altura INT NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_ALTURA
	ADD CONSTRAINT PK_HT_ALTURA PRIMARY KEY (cd_altura);
-------------------------------------------------------------------------------
-- Sessao Treino
-------------------------------------------------------------------------------
CREATE TABLE T_HT_SESSAO_TREINO (
	cd_sessao_treino SERIAL,
	cd_usuario INT NOT NULL,
	cd_exercicio INT NOT NULL,
	dt_sessao_treino DATE NOT NULL,
	dt_duracao DATE NOT NULL,
	vl_calorias INT,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_SESSAO_TREINO
	ADD CONSTRAINT PK_HT_SESSAO_TREINO PRIMARY KEY (cd_sessao_treino);
-------------------------------------------------------------------------------
-- Refeicao
-------------------------------------------------------------------------------
CREATE TABLE T_HT_REFEICAO (
	cd_refeicao SERIAL,
	cd_usuario INT NOT NULL,
	cd_alimento INT NOT NULL,
	dt_refeicao DATE NOT NULL,
	qtd_alimento INT NOT NULL,
	vl_calorias INT,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_REFEICAO
	ADD CONSTRAINT PK_HT_REFEICAO PRIMARY KEY (cd_refeicao);
-------------------------------------------------------------------------------
-- Exercicio
-------------------------------------------------------------------------------
CREATE TABLE T_HT_EXERCICIO (
	cd_exercicio SERIAL,
	nm_exercicio VARCHAR NOT NULL,
	vl_calorias INT NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_EXERCICIO
	ADD CONSTRAINT PK_HT_EXERCICIO PRIMARY KEY (cd_exercicio);
-------------------------------------------------------------------------------
-- Alimento
-------------------------------------------------------------------------------
CREATE TABLE T_HT_ALIMENTO (
	cd_alimento SERIAL,
	nm_alimento VARCHAR NOT NULL,
	vl_calorias INT NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_ALIMENTO
	ADD CONSTRAINT PK_HT_ALIMENTO PRIMARY KEY (cd_alimento);
-------------------------------------------------------------------------------
-- Create relationships
-------------------------------------------------------------------------------
ALTER TABLE T_HT_PRESSAO
	ADD CONSTRAINT FK_HT_USUARIO_PRESSAO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_PESO
	ADD CONSTRAINT FK_HT_USUARIO_PESO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_ALTURA
	ADD CONSTRAINT FK_HT_USUARIO_ALTURA FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_SESSAO_TREINO
	ADD CONSTRAINT FK_HT_USUARIO_EXERCICIO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_REFEICAO
	ADD CONSTRAINT FK_HT_USUARIO_REFEICAO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_SESSAO_TREINO
	ADD CONSTRAINT FK_HT_EXERCICIO_SESSAO_TREINO FOREIGN KEY (cd_exercicio)
	REFERENCES T_HT_EXERCICIO (cd_exercicio)
	ON DELETE SET NULL;

ALTER TABLE T_HT_REFEICAO
	ADD CONSTRAINT FK_HT_ALIMENTO_REFEICAO FOREIGN KEY (cd_alimento)
	REFERENCES T_HT_ALIMENTO(cd_alimento)
	ON DELETE SET NULL;
