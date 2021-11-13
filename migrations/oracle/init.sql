-------------------------------------------------------------------------------
-- DDL
-------------------------------------------------------------------------------
-- Create tables and constraints
-------------------------------------------------------------------------------
-- Usuario
-------------------------------------------------------------------------------
CREATE TABLE T_HT_USUARIO (
	cd_usuario NUMBER(5) NOT NULL,
	nm_usuario VARCHAR2(60) NOT NULL,
	dt_nascimento DATE NOT NULL,
	ds_genero VARCHAR2(1) NOT NULL,
	ds_email VARCHAR2(60) NOT NULL,
	ds_senha VARCHAR2(60) NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_USUARIO
	ADD CONSTRAINT PK_HT_USUARIO PRIMARY KEY (cd_usuario);

CREATE SEQUENCE	SEQ_HT_USUARIO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;
-------------------------------------------------------------------------------
-- Pressao
-------------------------------------------------------------------------------
CREATE TABLE T_HT_PRESSAO (
	cd_pressao NUMBER(5) NOT NULL,
	cd_usuario NUMBER(5) NOT NULL,
	dt_pressao DATE NOT NULL,
	vl_pressao_sistolica NUMBER(3) NOT NULL,
	vl_pressao_diastolica NUMBER(3) NOT NULL,
  	dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE	SEQ_HT_PRESSAO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

ALTER TABLE T_HT_PRESSAO
	ADD CONSTRAINT PK_HT_PRESSAO PRIMARY KEY (cd_pressao);
-------------------------------------------------------------------------------
-- Peso
-------------------------------------------------------------------------------
CREATE TABLE T_HT_PESO (
	cd_peso NUMBER(5) NOT NULL,
	cd_usuario NUMBER(5) NOT NULL,
	dt_peso DATE NOT NULL,
	vl_peso NUMBER(4) NO_T NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE SEQUENCE	SEQ_HT_PESO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

ALTER TABLE T_HT_PESO
	ADD CONSTRAINT PK_HT_PESO PRIMARY KEY (cd_peso);
-------------------------------------------------------------------------------
-- Altura
-------------------------------------------------------------------------------
CREATE TABLE T_HT_ALTURA (
	cd_altura NUMBER(5) NOT NULL,
	cd_usuario NUMBER(5) NOT NULL,
	dt_altura DATE NOT NULL,
	vl_altura NUMBER(4) NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

_
CREATE SEQUENCE	SEQ_HT_ALTURA
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

ALTER TABLE T_HT_ALTURA
	ADD CONSTRAINT PK_HT_ALTURA PRIMARY KEY (cd_altura);
-------------------------------------------------------------------------------
-- Sessao Treino
-------------------------------------------------------------------------------
CREATE TABLE T_HT_SESSAO_TREINO (
	cd_sessao_treino NUMBER(5) NOT NULL,
	cd_usuario NUMBER(5) NOT NULL,
	cd_exercicio NUMBER(5) NOT NULL,
	dt_sessao_treino DATE NOT NULL,
	dt_duracao DATE NOT NULL,
	vl_calorias NUMBER(5),
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);_


CREATE SEQUENCE	SEQ_HT_SESSAO_TREINO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

ALTER TABLE T_HT_SESSAO_TREINO
	ADD CONSTRAINT PK_HT_SESSAO_TREINO PRIMARY KEY (cd_sessao_treino);
-------------------------------------------------------------------------------
-- Refeicao
-------------------------------------------------------------------------------
CREATE TABLE T_HT_REFEICAO (
	cd_refeicao NUMBER(5) NOT NULL,
	cd_usuario NUMBER(5) NOT NULL,
	cd_alimento NUMBER(5) NOT NULL,
	dt_refeicao DATE NOT NULL,
	qtd_alimento NUMBER(5) NOT NULL,
	vl_calorias NUMBER(5),
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE SEQUENCE	SEQ_HT_REFEICAO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

ALTER TABLE T_HT_REFEICAO
	ADD CONSTRAINT PK_HT_REFEICAO PRIMARY KEY (cd_refeicao);
-------------------------------------------------------------------------------
-- Exercicio
-------------------------------------------------------------------------------
CREATE TABLE T_HT_EXERCICIO (
	cd_exercicio NUMBER(5) NOT NULL,
	nm_exercicio VARCHAR2(60) NOT NULL,
	vl_calorias NUMBER(5) NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE SEQUENCE	SEQ_HT_EXERCICIO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

ALTER TABLE T_HT_EXERCICIO
	ADD CONSTRAINT PK_HT_EXERCICIO PRIMARY KEY (cd_exercicio);
-------------------------------------------------------------------------------
-- Alimento
-------------------------------------------------------------------------------
CREATE TABLE T_HT_ALIMENTO (
	cd_alimento NUMBER(5) NOT NULL,
	nm_alimento VARCHAR2(60) NOT NULL,
	vl_calorias NUMBER(5) NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE SEQUENCE	SEQ_HT_ALIMENTO
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

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
	REFERENCES T_HT_USUA_RIO (cd_usuario)
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
