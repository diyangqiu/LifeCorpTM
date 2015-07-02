package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PRODUCTS database table.
 * 
 */
@Entity
@Table(name="PRODUCTS",schema="GUITARSHOP4")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRODUCT_ID")
	private long productId;

	@Column(name="LIST_PRICE")
	private BigDecimal listPrice;

	private String product;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="product")
	private List<OrderItem> orderItems;

	public Product() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public BigDecimal getListPrice() {
		return this.listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setProduct(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setProduct(null);

		return orderItem;
	}

}