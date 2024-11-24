-- DISTRICT ID Sequence creation
CREATE SEQUENCE IF NOT EXISTS trip_information_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Create table trip_information
CREATE TABLE IF NOT EXISTS trip_information
(
    id bigint NOT NULL DEFAULT nextval('trip_information_id_seq'::regclass),
    assigned_transport bigint,
    code character varying(255) COLLATE pg_catalog."default",
    created_by bigint,
    create_date timestamp(6) without time zone,
    current_status character varying(255) COLLATE pg_catalog."default",
    drop_off_address character varying(255) COLLATE pg_catalog."default",
    end_date timestamp(6) without time zone,
    pickup_address character varying(255) COLLATE pg_catalog."default",
    real_time_location character varying(255) COLLATE pg_catalog."default",
    start_date timestamp(6) without time zone,
    updated_by bigint,
    update_date timestamp(6) without time zone,
    drop_off_district integer,
    pickup_district integer,
    CONSTRAINT pk_id_trip_information PRIMARY KEY (id),
    CONSTRAINT uk_code_trip_information UNIQUE (code),
    CONSTRAINT fk_pickup_district_trip_information FOREIGN KEY (pickup_district)
    REFERENCES public.district_info (id) MATCH SIMPLE
                             ON UPDATE NO ACTION
                             ON DELETE NO ACTION,
    CONSTRAINT fk_dopoff_district_trip_information FOREIGN KEY (drop_off_district)
    REFERENCES public.district_info (id) MATCH SIMPLE
                             ON UPDATE NO ACTION
                             ON DELETE NO ACTION
    );