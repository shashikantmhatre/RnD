package com.shashi.trading.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageData {
	String type;
	BigDecimal sequence;

	@JsonProperty("product_id")
	String productId;

	BigDecimal price;

	@JsonProperty("open_24h")
	BigDecimal open24h;

	@JsonProperty("low_24h")
	BigDecimal low24h;

	@JsonProperty("high_24h")
	BigDecimal high24h;

	@JsonProperty("best_bid")
	BigDecimal bestBid;

	@JsonProperty("best_ask")
	BigDecimal bestAsk;

	@JsonProperty("last_size")
	private BigDecimal lastSize;

	String side;

	@JsonProperty("time")
	private Calendar bidTime;

	private List<List<BigDecimal>> bids;

	private List<List<BigDecimal>> asks;

	private List<List<String>> changes;

	public List<List<BigDecimal>> getBids() {
		if (bids == null) {
			bids = new ArrayList<>();
		}
		return bids;
	}

	public void setBids(List<List<BigDecimal>> bids) {
		this.bids = bids;
	}

	public List<List<BigDecimal>> getAsks() {
		if (asks == null) {
			asks = new ArrayList<>();
		}
		return asks;
	}

	public void setAsks(List<List<BigDecimal>> asks) {
		this.asks = asks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getSequence() {
		return sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOpen24h() {
		return open24h;
	}

	public void setOpen24h(BigDecimal open24h) {
		this.open24h = open24h;
	}

	public BigDecimal getLow24h() {
		return low24h;
	}

	public void setLow24h(BigDecimal low24h) {
		this.low24h = low24h;
	}

	public BigDecimal getHigh24h() {
		return high24h;
	}

	public void setHigh24h(BigDecimal high24h) {
		this.high24h = high24h;
	}

	public BigDecimal getBestBid() {
		return bestBid;
	}

	public void setBestBid(BigDecimal bestBid) {
		this.bestBid = bestBid;
	}

	public BigDecimal getBestAsk() {
		return bestAsk;
	}

	public void setBestAsk(BigDecimal bestAsk) {
		this.bestAsk = bestAsk;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Calendar getBidTime() {
		return bidTime;
	}

	public void setBidTime(Calendar bidTime) {
		this.bidTime = bidTime;
	}

	public BigDecimal getLastSize() {
		return lastSize;
	}

	public void setLastSize(BigDecimal lastSize) {
		this.lastSize = lastSize;
	}

	@Override
	public String toString() {
		return "TickerData [type=" + type + ", sequence=" + sequence + ", productId=" + productId + ", price=" + price
				+ ", open24h=" + open24h + ", low24h=" + low24h + ", high24h=" + high24h + ", bestBid=" + bestBid
				+ ", bestAsk=" + bestAsk + ", lastSize=" + lastSize + ", side=" + side + ", bidTime=" + bidTime + "]";
	}

	public List<List<String>> getChanges() {
		return changes;
	}

	public void setChanges(List<List<String>> changes) {
		this.changes = changes;
	}


}
