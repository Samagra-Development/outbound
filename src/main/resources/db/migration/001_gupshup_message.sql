create table gupshup_message(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	message_form_id BIGSERIAL NOT NULL,
	phone_no VARCHAR(15) NOT NULL,
	updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
	message VARCHAR(10000) NOT NULL,	
	is_last_message boolean not null	
);


CREATE INDEX index_phone_no ON gupshup_message(phone_no);
