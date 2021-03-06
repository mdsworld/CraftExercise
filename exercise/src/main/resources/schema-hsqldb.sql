CREATE TABLE Item(
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  name   	VARCHAR(50),
  category   VARCHAR(100),
  price     DOUBLE,
  quantity	INTEGER
);

CREATE TABLE Promo(
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  promo_code   	VARCHAR(50),
  promo_amt		DOUBLE,
  notes	VARCHAR(50)
);

CREATE TABLE Order_TR(
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  item_ids   	VARCHAR(150),
  promo_id  	INTEGER,
  total_amt		DOUBLE,
  Order_Num		INTEGER
);
