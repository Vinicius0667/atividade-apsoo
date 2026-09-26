-- Sequence para o id da tabela cargo_profissional
CREATE SEQUENCE IF NOT EXISTS public.cargos_profissionais_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- Tabela de tipos de conta
CREATE TABLE IF NOT EXISTS public.tipo_conta (
    id int4 NOT NULL,
    nome varchar(50) NOT NULL,
    CONSTRAINT tipo_conta_pkey PRIMARY KEY (id)
);

-- Inserir tipo de conta padrão para Cliente
INSERT INTO public.tipo_conta (id, nome) VALUES (1, 'Cliente') ON CONFLICT (id) DO NOTHING;

CREATE TABLE IF NOT EXISTS public.cargo_profissional (
    id int4 DEFAULT nextval('cargos_profissionais_id_seq'::regclass) NOT NULL,
    "nome" varchar(50) NOT NULL,
    status bool DEFAULT true NOT NULL,
    criado_em timestamptz DEFAULT now() NULL,
    CONSTRAINT cargos_profissionais_nome_key UNIQUE (nome),
    CONSTRAINT cargos_profissionais_pkey PRIMARY KEY (id)
);
ALTER TABLE public.cargo_profissional ENABLE ROW LEVEL SECURITY;

-- ======================================================================
-- 2. TABELA USUARIO (Depende de tipo_conta)
-- ======================================================================

CREATE TABLE IF NOT EXISTS public.usuario (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    nome varchar(40) NOT NULL,
    telefone varchar(15) NOT NULL,
    email varchar(60) NOT NULL,
    senha varchar(255) NOT NULL,
    criado_em timestamp DEFAULT now() NULL,
    tipo_conta_id int4 NULL,
    cpf varchar(14) NULL,
    CONSTRAINT usuarios_email_key UNIQUE (email),
    CONSTRAINT usuarios_telefone_key UNIQUE (telefone),
    CONSTRAINT usuarios_pkey PRIMARY KEY (id),
    CONSTRAINT usuarios_tipo_conta_id_fkey FOREIGN KEY (tipo_conta_id) REFERENCES public.tipo_conta(id)
);
ALTER TABLE public.usuario ENABLE ROW LEVEL SECURITY;

-- ======================================================================
-- 3. TABELAS CLIENTE E PROFISSIONAL (Dependem de usuario e cargo_profissional)
-- ======================================================================

CREATE TABLE IF NOT EXISTS public.cliente (
    id uuid NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id),
    CONSTRAINT cliente_id_fkey FOREIGN KEY (id) REFERENCES public.usuario(id) ON DELETE CASCADE
);
ALTER TABLE public.cliente ENABLE ROW LEVEL SECURITY;

CREATE TABLE IF NOT EXISTS public.profissional (
    id uuid NOT NULL,
    status bool DEFAULT true NOT NULL,
    cargo_id int4 NULL,
    cpf varchar(14) NULL,
    percentual_comissao numeric DEFAULT '0'::numeric NOT NULL,
    CONSTRAINT profissional_pkey PRIMARY KEY (id),
    CONSTRAINT profissional_id_fkey FOREIGN KEY (id) REFERENCES public.usuario(id) ON DELETE CASCADE,
    CONSTRAINT profissional_cargo_id_fkey FOREIGN KEY (cargo_id) REFERENCES public.cargo_profissional(id)
);
ALTER TABLE public.profissional ENABLE ROW LEVEL SECURITY;
