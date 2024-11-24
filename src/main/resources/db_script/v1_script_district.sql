-- DISTRICT ID Sequence creation
CREATE SEQUENCE IF NOT EXISTS district_info_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Create table district_info
CREATE TABLE IF NOT EXISTS district_info
(
    id integer NOT NULL DEFAULT nextval('district_info_id_seq'::regclass),
    active_status boolean,
    address character varying(255),
    code character varying(255),
    created_by bigint,
    created_date timestamp(6) without time zone,
    name_bn character varying(255),
    name_en character varying(255),
    updated_by bigint,
    updated_date timestamp(6) without time zone,
    CONSTRAINT district_info_pkey PRIMARY KEY (id),
    CONSTRAINT uk_district_code_distirct_info UNIQUE (code)
    );

-- insert data for district info
INSERT INTO district_info(
    active_status,code,name_en)
VALUES (true, 'DHA','Dhaka')
        ,(true, 'CTG','Chotrogram'),
       (true, 'GAZ','Gazipur');