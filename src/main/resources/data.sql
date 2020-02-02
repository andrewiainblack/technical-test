DROP TABLE IF EXISTS SMART_METER_READINGS;

CREATE TABLE SMART_METER_READINGS (
  ACCOUNT_NUMBER  NUMBER PRIMARY KEY,
  ELEC_ID         NUMBER,
  GAS_ID          NUMBER,
  ELEC_SMART_READ DECIMAL,
  GAS_SMART_READ  DECIMAL
);


INSERT INTO SMART_METER_READINGS (ACCOUNT_NUMBER, ELEC_ID, GAS_ID, ELEC_SMART_READ, GAS_SMART_READ)
VALUES (9223372036, 736464, 37474843, 326.038, 093.038);
INSERT INTO SMART_METER_READINGS (ACCOUNT_NUMBER, ELEC_ID, GAS_ID, ELEC_SMART_READ, GAS_SMART_READ)
VALUES (3454322231, 374873, 98473733, 316.038, 283.238);
INSERT INTO SMART_METER_READINGS (ACCOUNT_NUMBER, ELEC_ID, GAS_ID, ELEC_SMART_READ, GAS_SMART_READ)
VALUES (8364647344, 984873, 24238485, 456.038, 928.028);
INSERT INTO SMART_METER_READINGS (ACCOUNT_NUMBER, ELEC_ID, GAS_ID, ELEC_SMART_READ, GAS_SMART_READ)
VALUES (3847263546, 397673, 38484842, 356.068, 424.018);
INSERT INTO SMART_METER_READINGS (ACCOUNT_NUMBER, ELEC_ID, GAS_ID, ELEC_SMART_READ, GAS_SMART_READ)
VALUES (0976388397, 847463, 03847271, 387.238, 305.383);
INSERT INTO SMART_METER_READINGS (ACCOUNT_NUMBER, ELEC_ID, GAS_ID, ELEC_SMART_READ, GAS_SMART_READ)
VALUES (0937462521, 093647, 36452526, 098.088, 095.038);
