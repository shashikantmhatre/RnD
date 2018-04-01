package com.shashi.trading.vo;

import java.math.BigDecimal;

public class TrendData {
	private String type;

	private String productId;

	private BigDecimal price;

	private BigDecimal oldPrice;

	private BigDecimal open24h;

	private BigDecimal low24h;

	private BigDecimal high24h;

	private BigDecimal bestBid;

	private BigDecimal bestAsk;

	private String side;
	
	private String trendingTowards;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTrendingTowards() {
		return trendingTowards;
	}

	public void setTrendingTowards(String trendingTowards) {
		this.trendingTowards = trendingTowards;
	}

	@Override
	public String toString() {
		return "TrendData [type=" + type + ", productId=" + productId + ", price=" + price + ", open24h=" + open24h
				+ ", low24h=" + low24h + ", high24h=" + high24h + ", bestBid=" + bestBid + ", bestAsk=" + bestAsk
				+ ", side=" + side + ", trendingTowards=" + trendingTowards + "]";
	}

	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	
	
}
