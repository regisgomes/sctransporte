
CREATE SEQUENCE public.marca_id_marca_seq_1;

CREATE TABLE public.marca (
                id_marca BIGINT NOT NULL DEFAULT nextval('public.marca_id_marca_seq_1'),
                nome VARCHAR NOT NULL,
                CONSTRAINT marca_pk PRIMARY KEY (id_marca)
);


ALTER SEQUENCE public.marca_id_marca_seq_1 OWNED BY public.marca.id_marca;

CREATE SEQUENCE public.modelo_id_modelo_seq_1;

CREATE TABLE public.modelo (
                id_modelo BIGINT NOT NULL DEFAULT nextval('public.modelo_id_modelo_seq_1'),
                id_marca BIGINT NOT NULL,
                nome VARCHAR NOT NULL,
                CONSTRAINT modelo_pk PRIMARY KEY (id_modelo)
);


ALTER SEQUENCE public.modelo_id_modelo_seq_1 OWNED BY public.modelo.id_modelo;

CREATE SEQUENCE public.tipo_veiculo_id_tipo_veiculo_seq_1;

CREATE TABLE public.tipo_veiculo (
                id_tipo_veiculo BIGINT NOT NULL DEFAULT nextval('public.tipo_veiculo_id_tipo_veiculo_seq_1'),
                categoria VARCHAR NOT NULL,
                CONSTRAINT tipo_veiculo_pk PRIMARY KEY (id_tipo_veiculo)
);


ALTER SEQUENCE public.tipo_veiculo_id_tipo_veiculo_seq_1 OWNED BY public.tipo_veiculo.id_tipo_veiculo;

CREATE SEQUENCE public.carro_id_carro_seq_1;

CREATE TABLE public.carro (
                id_carro BIGINT NOT NULL DEFAULT nextval('public.carro_id_carro_seq_1'),
                id_modelo BIGINT NOT NULL,
                id_tipo_veiculo BIGINT NOT NULL,
                quilometragem INTEGER NOT NULL,
                cor VARCHAR NOT NULL,
                ano INTEGER NOT NULL,
                placa VARCHAR NOT NULL,
                CONSTRAINT carro_pk PRIMARY KEY (id_carro)
);


ALTER SEQUENCE public.carro_id_carro_seq_1 OWNED BY public.carro.id_carro;

CREATE SEQUENCE public.cliente_id_cliente_seq;

CREATE TABLE public.cliente (
                id_cliente BIGINT NOT NULL DEFAULT nextval('public.cliente_id_cliente_seq'),
                telefone VARCHAR NOT NULL,
                endereco VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                empresa VARCHAR NOT NULL,
                cnpj VARCHAR NOT NULL,
                CONSTRAINT cliente_pk PRIMARY KEY (id_cliente)
);


ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;

CREATE SEQUENCE public.cargo_id_cargo_seq;

CREATE TABLE public.cargo (
                id_cargo BIGINT NOT NULL DEFAULT nextval('public.cargo_id_cargo_seq'),
                nome VARCHAR NOT NULL,
                salario NUMERIC(16,2) NOT NULL,
                CONSTRAINT cargo_pk PRIMARY KEY (id_cargo)
);


ALTER SEQUENCE public.cargo_id_cargo_seq OWNED BY public.cargo.id_cargo;

CREATE SEQUENCE public.usuario_id_usuario_seq;

CREATE TABLE public.usuario (
                id_usuario BIGINT NOT NULL DEFAULT nextval('public.usuario_id_usuario_seq'),
                login VARCHAR NOT NULL,
                pass VARCHAR NOT NULL,
                nome VARCHAR NOT NULL,
                cpf VARCHAR NOT NULL,
                endereco VARCHAR NOT NULL,
                telefone VARCHAR NOT NULL,
                CONSTRAINT usuario_pk PRIMARY KEY (id_usuario)
);


ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;

CREATE SEQUENCE public.funcionario_id_funcionario_seq;

CREATE TABLE public.funcionario (
                id_funcionario BIGINT NOT NULL DEFAULT nextval('public.funcionario_id_funcionario_seq'),
                id_usuario BIGINT NOT NULL,
                id_cargo BIGINT NOT NULL,
                CONSTRAINT funcionario_pk PRIMARY KEY (id_funcionario)
);


ALTER SEQUENCE public.funcionario_id_funcionario_seq OWNED BY public.funcionario.id_funcionario;

CREATE SEQUENCE public.viagem_id_viagem_seq_1;

CREATE TABLE public.viagem (
                id_viagem BIGINT NOT NULL DEFAULT nextval('public.viagem_id_viagem_seq_1'),
                id_carro BIGINT NOT NULL,
                id_motorista BIGINT NOT NULL,
                data_saida TIMESTAMP NOT NULL,
                quilometragem_inicial INTEGER NOT NULL,
                quilometragem_final INTEGER NOT NULL,
                data_chegada TIMESTAMP NOT NULL,
                CONSTRAINT viagem_pk PRIMARY KEY (id_viagem)
);


ALTER SEQUENCE public.viagem_id_viagem_seq_1 OWNED BY public.viagem.id_viagem;

CREATE SEQUENCE public.entrega_id_entrega_seq;

CREATE TABLE public.entrega (
                id_entrega BIGINT NOT NULL DEFAULT nextval('public.entrega_id_entrega_seq'),
                id_viagem BIGINT NOT NULL,
                id_cliente_destino BIGINT NOT NULL,
                id_cliente_origem BIGINT NOT NULL,
                id_funcionario BIGINT NOT NULL,
                cod_rastreio VARCHAR NOT NULL,
                valor NUMERIC(16,2) NOT NULL,
                volumes INTEGER NOT NULL,
                data TIMESTAMP NOT NULL,
                descricao VARCHAR NOT NULL,
                CONSTRAINT entrega_pk PRIMARY KEY (id_entrega)
);


ALTER SEQUENCE public.entrega_id_entrega_seq OWNED BY public.entrega.id_entrega;

ALTER TABLE public.modelo ADD CONSTRAINT marca_modelo_fk
FOREIGN KEY (id_marca)
REFERENCES public.marca (id_marca)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.carro ADD CONSTRAINT modelo_carro_fk
FOREIGN KEY (id_modelo)
REFERENCES public.modelo (id_modelo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.carro ADD CONSTRAINT tipo_veiculo_carro_fk
FOREIGN KEY (id_tipo_veiculo)
REFERENCES public.tipo_veiculo (id_tipo_veiculo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.viagem ADD CONSTRAINT carro_viagem_fk
FOREIGN KEY (id_carro)
REFERENCES public.carro (id_carro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.entrega ADD CONSTRAINT cliente_entrega_fk
FOREIGN KEY (id_cliente_origem)
REFERENCES public.cliente (id_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.entrega ADD CONSTRAINT cliente_entrega_fk1
FOREIGN KEY (id_cliente_destino)
REFERENCES public.cliente (id_cliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.funcionario ADD CONSTRAINT cargo_funcionario_fk
FOREIGN KEY (id_cargo)
REFERENCES public.cargo (id_cargo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.funcionario ADD CONSTRAINT usuario_funcionario_fk
FOREIGN KEY (id_usuario)
REFERENCES public.usuario (id_usuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.entrega ADD CONSTRAINT funcionario_entrega_fk
FOREIGN KEY (id_funcionario)
REFERENCES public.funcionario (id_funcionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.viagem ADD CONSTRAINT funcionario_viagem_fk
FOREIGN KEY (id_motorista)
REFERENCES public.funcionario (id_funcionario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.entrega ADD CONSTRAINT viagem_entrega_fk
FOREIGN KEY (id_viagem)
REFERENCES public.viagem (id_viagem)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;