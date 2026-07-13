CREATE TABLE `history_notes` (
    id integer primary key auto_increment,
    note_id integer,
    action VARCHAR(100),
    comment TEXT,
    timestamp DATE,


    CONSTRAINT fk_note_history FOREIGN KEY (note_id) REFERENCES note (id)

)