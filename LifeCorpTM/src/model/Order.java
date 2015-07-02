package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ORDERS database table.
 * 
 */
@Entity
@Table(name="ORDERS",schema="GUITARSHOP4")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORDER_ID")
	private long orderId;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	@Column(name="SHIP_AMOUNT")
	private BigDecimal shipAmount;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="order")
	private List<OrderItem> orderItems;

	public Order() {
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getShipAmount() {
		return this.shipAmount;
	}

	public void setShipAmount(BigDecimal shipAmount) {
		this.shipAmount = shipAmount;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setOrder(null);

		return orderItem;
	}

}