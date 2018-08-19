package za.co.digitalplatoon.invoiceservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineItem {
	
	@Id
	@GeneratedValue
	@Column(name="ITEM_ID")
	private Long itemId;
	
	@Column(name="QUANTITY")
	private Long quantity;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="UNIT_PRICE")
	private BigDecimal unitPrice;
	
	@ManyToOne()
	@JoinColumn(name="INVOICE_ID",referencedColumnName="INVOICE_ID")
	private Invoice invoice;

	public LineItem() {
		super();
	}

	public Long getId() {
		return itemId;
	}

	public void setId(Long id) {
		this.itemId = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public BigDecimal getLineItemTotal() {
		BigDecimal lineItemTotal = getUnitPrice().multiply(new BigDecimal(getQuantity()));
		return lineItemTotal;
	}
}
