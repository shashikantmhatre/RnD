package com.shashi.trading.vo;

import java.math.BigDecimal;

public class Change {
	private String side;
	private BigDecimal price;
	private BigDecimal size;
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSize() {
		return size;
	}
	public void setSize(BigDecimal size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Change [side=" + side + ", price=" + price + ", size=" + size + "]";
	}
	
	
}
