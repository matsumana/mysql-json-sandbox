DROP TABLE IF EXISTS note;
CREATE TABLE note (
    id     INT AUTO_INCREMENT
  , title  VARCHAR(64)
  , detail JSON
  , PRIMARY KEY (id)
);
