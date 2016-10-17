package com.sapient.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="BLOCK")
@DynamicUpdate(true)
@DynamicInsert(true)
@SelectBeforeUpdate
@XmlRootElement
public class Block {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BLOCK_ID")
	private Long id;
	
	@Column(name="SIDE",nullable=false,length=40)
	private String side;
	
	@Column(name="SYMBOL",nullable=false,length=5)
	private String symbol;
	
	@Column(name="TOTAL_QUANTITY",nullable=false)
	private Long total_quantity;
	
	@Column(name="TYPE",nullable=false,length=40)
	private String type;
	
	@Column(name="STATUS",nullable=false,length=40)
	private String status;
	
	@Column(name="LIMIT_PRICE",nullable=true)
	private double limit_price;
	
	@Column(name="STOP_PRICE",nullable=true)
	private double stop_price;
	
	@Column(name="EXECUTED_QUANTITY",nullable=true)
	private Long executed_quantity;
	
	@Column(name="EXECUTED_DATE",nullable=true)
	private Date executed_date;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getSide() {
		return side;
	}
	
	@XmlElement
	public void setSide(String side) {
		this.side = side;
	}

	public String getSymbol() {
		return symbol;
	}
	
	@XmlElement
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getTotal_quantity() {
		return total_quantity;
	}
	
	@XmlElement
	public void setTotal_quantity(Long total_quantity) {
		this.total_quantity = total_quantity;
	}

	public String getType() {
		return type;
	}
	
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}
	
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}

	public double getLimit_price() {
		return limit_price;
	}
	
	@XmlElement
	public void setLimit_price(double limit_price) {
		this.limit_price = limit_price;
	}

	public double getStop_price() {
		return stop_price;
	}
	
	@XmlElement
	public void setStop_price(double stop_price) {
		this.stop_price = stop_price;
	}

	public Long getExecuted_quantity() {
		return executed_quantity;
	}
	
	@XmlElement
	public void setExecuted_quantity(Long executed_quantity) {
		this.executed_quantity = executed_quantity;
	}

	public Date getExecuted_date() {
		return executed_date;
	}
	
	@XmlElement
	public void setExecuted_date(Date executed_date) {
		this.executed_date = executed_date;
	}

	public Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(String side, String symbol, Long total_quantity, String type, String status, double limit_price,
			double stop_price, Long executed_quantity, Date executed_date) {
		super();
		this.side = side;
		this.symbol = symbol;
		this.total_quantity = total_quantity;
		this.type = type;
		this.status = status;
		this.limit_price = limit_price;
		this.stop_price = stop_price;
		this.executed_quantity = executed_quantity;
		this.executed_date = executed_date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Block [id=");
		builder.append(id);
		builder.append(", side=");
		builder.append(side);
		builder.append(", symbol=");
		builder.append(symbol);
		builder.append(", total_quantity=");
		builder.append(total_quantity);
		builder.append(", type=");
		builder.append(type);
		builder.append(", status=");
		builder.append(status);
		builder.append(", limit_price=");
		builder.append(limit_price);
		builder.append(", stop_price=");
		builder.append(stop_price);
		builder.append(", executed_quantity=");
		builder.append(executed_quantity);
		builder.append(", executed_date=");
		builder.append(executed_date);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}