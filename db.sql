CREATE TABLE public.acopio_acum (
    id_acopio_acum integer NOT NULL,
    codigo integer,
    total_kls integer,
    manana integer,
    tarde integer,
    anno integer,
    mes integer,
    quincena integer
);

ALTER TABLE public.acopio_acum OWNER TO postgres;

ALTER TABLE public.acopio_acum ALTER COLUMN id_acopio_acum ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.acopio_acum_id_acopio_acum_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.calidad (
    id_calidad integer NOT NULL,
    id_acopio_acum integer,
    grasa integer,
    solido integer
);

ALTER TABLE public.calidad OWNER TO postgres;

ALTER TABLE public.calidad ALTER COLUMN id_calidad ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.calidad_id_calidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.pago (
    id_pago integer NOT NULL,
    id_acopio_acum integer,
    varl integer,
    varg integer,
    vars integer,
    pleche integer,
    pgrasa integer,
    psolidos integer,
    bonificacion integer,
    dvarl integer,
    dvarg integer,
    dvars integer,
    retencion integer,
    total integer,
    v_final integer
);

ALTER TABLE public.pago OWNER TO postgres;

ALTER TABLE public.pago ALTER COLUMN id_pago ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pago_id_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.proveedor (
    codigo integer NOT NULL,
    nombre text,
    categoria "char",
    retencion boolean
);

ALTER TABLE public.proveedor OWNER TO postgres;

ALTER TABLE ONLY public.acopio_acum
    ADD CONSTRAINT acopio_acum_pkey PRIMARY KEY (id_acopio_acum);


ALTER TABLE ONLY public.calidad
    ADD CONSTRAINT calidad_pkey PRIMARY KEY (id_calidad);

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);

ALTER TABLE ONLY public.proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (codigo);

ALTER TABLE ONLY public.acopio_acum
    ADD CONSTRAINT acopio_acum_codigo_fkey FOREIGN KEY (codigo) REFERENCES public.proveedor(codigo);

ALTER TABLE ONLY public.calidad
    ADD CONSTRAINT calidad_id_acopio_acum_fkey FOREIGN KEY (id_acopio_acum) REFERENCES public.acopio_acum(id_acopio_acum);

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_id_acopio_acum_fkey FOREIGN KEY (id_acopio_acum) REFERENCES public.acopio_acum(id_acopio_acum);
