-------------------------------------------------------------------------------
-- DDL
-------------------------------------------------------------------------------
-- Delete tables
-------------------------------------------------------------------------------
DROP TABLE IF EXISTS T_HT_USUARIO CASCADE;
DROP TABLE IF EXISTS T_HT_PRESSAO CASCADE;
DROP TABLE IF EXISTS T_HT_PESO CASCADE;
DROP TABLE IF EXISTS T_HT_ALTURA CASCADE;
DROP TABLE IF EXISTS T_HT_IMC CASCADE;
DROP TABLE IF EXISTS T_HT_SESSAO_EXERCICIO CASCADE;
DROP TABLE IF EXISTS T_HT_EXERCICIO CASCADE;
DROP TABLE IF EXISTS T_HT_REFEICAO CASCADE;
DROP TABLE IF EXISTS T_HT_ALIMENTO CASCADE;
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
	vl_peso NUMERIC NOT NULL,
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
	vl_altura NUMERIC NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_ALTURA
	ADD CONSTRAINT PK_HT_ALTURA PRIMARY KEY (cd_altura);
-------------------------------------------------------------------------------
-- IMC
-------------------------------------------------------------------------------
CREATE TABLE T_HT_IMC (
	cd_imc SERIAL,
	cd_usuario INT NOT NULL,
	cd_peso INT NOT NULL,
	cd_altura INT NOT NULL,
	dt_imc DATE NOT NULL,
	vl_imc INT NOT NULL,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_IMC
	ADD CONSTRAINT PK_HT_IMC PRIMARY KEY (cd_imc);
-------------------------------------------------------------------------------
-- Sessao Treino
-------------------------------------------------------------------------------
CREATE TABLE T_HT_SESSAO_EXERCICIO (
	cd_sessao_exercicio SERIAL,
	cd_usuario INT NOT NULL,
	cd_exercicio INT NOT NULL,
	dt_sessao_exercicio DATE NOT NULL,
	dt_duracao BIGINT,
	vl_calorias NUMERIC,
  dt_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE T_HT_SESSAO_EXERCICIO
	ADD CONSTRAINT PK_HT_SESSAO_EXERCICIO PRIMARY KEY (cd_sessao_exercicio);
-------------------------------------------------------------------------------
-- Refeicao
-------------------------------------------------------------------------------
CREATE TABLE T_HT_REFEICAO (
	cd_refeicao SERIAL,
	cd_usuario INT NOT NULL,
	cd_alimento INT NOT NULL,
	dt_refeicao DATE NOT NULL,
	qtd_alimento INT NOT NULL,
	vl_calorias NUMERIC,
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
-------------------------------------------------------------------------------
ALTER TABLE T_HT_PESO
	ADD CONSTRAINT FK_HT_USUARIO_PESO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;
-------------------------------------------------------------------------------
ALTER TABLE T_HT_ALTURA
	ADD CONSTRAINT FK_HT_USUARIO_ALTURA FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;
-------------------------------------------------------------------------------
ALTER TABLE T_HT_IMC
	ADD CONSTRAINT FK_HT_USUARIO_IMC FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_IMC
	ADD CONSTRAINT FK_HT_PESO_IMC FOREIGN KEY (cd_peso)
	REFERENCES T_HT_PESO (cd_peso)
	ON DELETE SET NULL;

ALTER TABLE T_HT_IMC
	ADD CONSTRAINT FK_HT_ALTURA_IMC FOREIGN KEY (cd_altura)
	REFERENCES T_HT_ALTURA (cd_altura)
	ON DELETE SET NULL;
-------------------------------------------------------------------------------
ALTER TABLE T_HT_SESSAO_EXERCICIO
	ADD CONSTRAINT FK_HT_USUARIO_SESSAO_EXERCICIO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_SESSAO_EXERCICIO
	ADD CONSTRAINT FK_HT_EXERCICIO_SESSAO_EXERCICIO FOREIGN KEY (cd_exercicio)
	REFERENCES T_HT_EXERCICIO (cd_exercicio)
	ON DELETE SET NULL;
-------------------------------------------------------------------------------
ALTER TABLE T_HT_REFEICAO
	ADD CONSTRAINT FK_HT_USUARIO_REFEICAO FOREIGN KEY (cd_usuario)
	REFERENCES T_HT_USUARIO (cd_usuario)
	ON DELETE SET NULL;

ALTER TABLE T_HT_REFEICAO
	ADD CONSTRAINT FK_HT_ALIMENTO_REFEICAO FOREIGN KEY (cd_alimento)
	REFERENCES T_HT_ALIMENTO(cd_alimento)
	ON DELETE SET NULL;
-------------------------------------------------------------------------------
-- Insert Default options
-------------------------------------------------------------------------------
-- Insert Exercices
-------------------------------------------------------------------------------
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Bicicleta');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Corrida');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Caminhada');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Natação');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Funcional');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Crossfit');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Academia');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Boxe');
INSERT INTO T_HT_EXERCICIO (nm_exercicio) VALUES ('Muay Thai');
-------------------------------------------------------------------------------
-- Insert Meals
-------------------------------------------------------------------------------
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Arroz');
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Feijão');
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Batata doce');
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Filé de Frango');
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Contra Filé');
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Filé de tilápia');
INSERT INTO T_HT_ALIMENTO (nm_alimento) VALUES ('Macarrão');
-------------------------------------------------------------------------------
-- TEST user information!
-------------------------------------------------------------------------------
-- TEST user information!
-------------------------------------------------------------------------------
-- TEST user information!
-------------------------------------------------------------------------------
INSERT INTO T_HT_USUARIO (
  nm_usuario,
  dt_nascimento,
  ds_genero,
  ds_email,
  ds_senha
)
VALUES (
  'teste',
  '1990-11-21',
  'Masculino',
  'teste@teste.com.br',
  -- teste
  '698dc19d489c4e4db73e28a713eab07b'
  );
-------------------------------------------------------------------------------
-- TEST Height information!
-------------------------------------------------------------------------------
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-01',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-02',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-03',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-04',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-05',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-06',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-07',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-08',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-09',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-10',
	1.75
);
INSERT INTO T_HT_ALTURA (
	cd_usuario,
	dt_altura,
	vl_altura
)
VALUES (
	1,
	'2021-01-11',
	1.75
);
-------------------------------------------------------------------------------
-- TEST Weight information!
-------------------------------------------------------------------------------
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-01',
	68.2
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-02',
	69.2
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-03',
	70.5
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-04',
	71.3
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-05',
	69.4
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-06',
	71.2
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-07',
	72.3
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-08',
	73.9
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-09',
	75.2
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-10',
	74.1
);
INSERT INTO T_HT_PESO (
	cd_usuario,
	dt_peso,
	vl_peso
)
VALUES (
	1,
	'2021-01-11',
	74.5
);
-------------------------------------------------------------------------------
-- TEST information!
-------------------------------------------------------------------------------