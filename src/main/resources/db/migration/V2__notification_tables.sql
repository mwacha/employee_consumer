CREATE TABLE IF NOT EXISTS consumer_employee.notification_received
(
    id         uuid                        NOT NULL,
    body       text                        not null,
    status     character varying(8)        not null default 'PROGRESS',
    created_at timestamp without time zone NOT null default now(),
    updated_at timestamp without time zone,
    deleted_at timestamp without time zone,
    CONSTRAINT pk_notification_received PRIMARY KEY (id)
);


DROP TRIGGER IF EXISTS notification_received_tgr_bu ON consumer_employee.notification_received;

CREATE TRIGGER notification_received_tgr_bu
    BEFORE UPDATE
    ON consumer_employee.notification_received
    FOR EACH ROW
EXECUTE PROCEDURE consumer_employee.trigger_set_updated_at();