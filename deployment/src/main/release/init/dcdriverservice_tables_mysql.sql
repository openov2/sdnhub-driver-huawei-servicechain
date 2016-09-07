DROP TABLE IF EXISTS SERVICEINFO
/
CREATE TABLE SERVICEINFO
(
    SERVICENAME VARCHAR(128) NOT NULL,
    SERVICEVERSION VARCHAR(128),
    SERVICEOWNER VARCHAR(128),	
    PRIMARY KEY (SERVICENAME),
    UNIQUE KEY (SERVICENAME)
)
ENGINE = INNODB
/
