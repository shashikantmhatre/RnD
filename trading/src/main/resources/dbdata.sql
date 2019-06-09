CREATE SEQUENCE IF NOT EXISTS receivedordernumber nomaxvalue cache 100;

CREATE SEQUENCE IF NOT EXISTS historysequence nomaxvalue cache 100;

CREATE TABLE trade_history
(
	product VARCHAR(100),
	orderSequence number,
	currentPrice NUMBER,
	open24h number,
	volumn24h number,
	low24h number,
	high24h number,
	bestBid number,
	bestAsk number,
	bidType varchar(10),
	bidTime TIMESTAMP,
	receivedTime TIMESTAMP,
	receivedOrderSequence number,
);


CREATE TABLE trade_trend
(
	product VARCHAR(100),
	newprice NUMBER,
	open24h number,
	volumn24h number,
	low24h number,
	high24h number,
	bestBid number,
	bestAsk number,
	trendingTowards varchar2(10),
	trendChangeTime timestamp,
	oldprice NUMBER
);

CREATE TABLE trade_buy_order
(
	id UUID,
	product VARCHAR(100),
	paidPrice NUMBER,
	askPrice NUMBER,
	coinBought number,
	tradeTime timestamp,
	status varchar(10)
);

CREATE TABLE trade_sell_order
(
	id UUID,
	product VARCHAR(100),
	soldPrice NUMBER,
	askPrice NUMBER,
	coinToSell number,
	tradeTime timestamp,
	status varchar(10)
);

CREATE TABLE trade_alert
(
	id UUID,
	product VARCHAR(100),
	askPrice NUMBER,
	netDifference NUMBER,
	tradeType varchar2(10),
	tradeTime timestamp
);


CREATE TABLE order_book
(
	product VARCHAR(100),
	side VARCHAR(10),
	price NUMBER,
	size NUMBER,
	trade_time TIMESTAMP DEFAULT SYSDATE,
	orderSequence number default receivedordernumber.nextval,
	request_type VARCHAR(2) default 'I'
);

CREATE TABLE trade_request (
product VARCHAR(10),
side VARCHAR(10),
price number,
request_size number,
trade_time TIMESTAMP,
orderSequence number default receivedordernumber.nextval,
request_type VARCHAR(2) default 'C'
);

CREATE INDEX orderbook_idx1 ON ORDER_BOOK (product, side, price);


--execute below trigger before calling the gdax app
--CREATE TRIGGER trade_order_insert_trigger BEFORE INSERT ON ORDER_BOOK FOR EACH ROW CALL "com.shashi.triggers.LogTradeInit";
CREATE TRIGGER trade_order_change_trigger BEFORE UPDATE ON ORDER_BOOK FOR EACH ROW CALL "com.shashi.triggers.LogTradeChange";

