ALTER TABLE note ADD status_note BIGINT NOT NULL;

ALTER TABLE note ADD CONSTRAINT status_note_fk FOREIGN KEY (status_note) REFERENCES(id);