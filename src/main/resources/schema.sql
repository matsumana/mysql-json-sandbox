DROP TABLE IF EXISTS note;

CREATE TABLE note (
    id     INT AUTO_INCREMENT
  , title  VARCHAR(64)
  , detail JSON
  , name0  VARCHAR(64) AS (TRIM('"' FROM detail->'$.names[0].name')) STORED NOT NULL
  , name1  VARCHAR(64) AS (TRIM('"' FROM detail->'$.names[1].name')) STORED
  , PRIMARY KEY (id)
)
  COLLATE UTF8MB4_BIN;

ALTER TABLE note
  ADD INDEX index_name0(name0);
