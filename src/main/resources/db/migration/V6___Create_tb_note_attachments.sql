CREATE TABLE attachment_note (
    note_id BIGINT,
    attachment_id BIGINT,

    PRIMARY KEY (note_id, attachment_id),
    CONSTRAINT fk_note FOREIGN KEY (note_id) REFERENCES note(id),
    CONSTRAINT fk_attachment FOREIGN KEY (attachment_id) REFERENCES `attachments`(id)

)