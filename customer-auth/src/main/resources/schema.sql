
CREATE TABLE account (
   id bigint(20) NOT NULL AUTO_INCREMENT,
  active bit(1) NOT NULL,
  password varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ;
