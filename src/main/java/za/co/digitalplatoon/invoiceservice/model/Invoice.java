package za.co.digitalplatoon.invoiceservice.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Invoice {

	@Id
	@GeneratedValue
	@Column(name="INVOICE_ID",nullable=false)
	private Long invoiceId;
	
	@Column(name="CLIENT")
	private String client;
	
	@Column(name="VAT_RATE")
	private Long vatRate;
	
	@Column(name="INVOICE_DATE")
	private Date invoiceDate;
	
	@OneToMany(mappedBy="invoice",targetEntity=LineItem.class,fetch=FetchType.EAGER)
	private List<LineItem> items;

	public Invoice() {
		super();
	}

	public Long getId() {
		return invoiceId;
	}

	public void setId(Long id) {
		this.invoiceId = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}
	
	public BigDecimal getSubTotal() {
		BigDecimal subTotal = BigDecimal.ZERO;
		for(LineItem item:getItems()) {
			subTotal = subTotal.add(item.getLineItemTotal());
		}
		return subTotal;
	}
	
	public BigDecimal getVat() {
		final BigDecimal vat = new BigDecimal(0.15);
		BigDecimal totalVat = getSubTotal().multiply(vat);
		totalVat = totalVat.setScale(2, BigDecimal.ROUND_HALF_UP);
		return totalVat;
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = getSubTotal().add(getVat());
		return total;
	}
}
