package za.co.digitalplatoon.invoiceservice.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.digitalplatoon.invoiceservice.model.Invoice;
import za.co.digitalplatoon.invoiceservice.model.LineItem;
import za.co.digitalplatoon.invoiceservice.repo.InvoiceRepo;
import za.co.digitalplatoon.invoiceservice.repo.LineItemRepo;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private LineItemRepo lineItemRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Invoice> getAllInvoices(){
		return invoiceRepo.findAll();
	}
	
	/*@RequestMapping(method = RequestMethod.POST)
	public Invoice addInvoice(@RequestParam Invoice invoice){
		return invoiceRepo.save(invoice);
	}*/
	
	/*
	 * Had to use this method that does not receive Invoice as a Request param
	 * As i was using postman to test.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Invoice addInvoice(){
		Invoice invoice = new Invoice();
		invoice.setClient("Ratsela March");
		invoice.setVatRate(15L);
		invoice.setInvoiceDate(new Date());
		
		LineItem item = new LineItem();
		item.setDescription("Milk");
		item.setQuantity(15L);
		item.setUnitPrice(new BigDecimal(25.00));
		
		lineItemRepo.save(item);
		
		LineItem item2 = new LineItem();
		item2.setDescription("Cheese");
		item2.setQuantity(8L);
		item2.setUnitPrice(new BigDecimal(36.00));
		
		lineItemRepo.save(item2);
		
		LineItem item3 = new LineItem();
		item3.setDescription("Banana");
		item3.setQuantity(3L);
		item3.setUnitPrice(new BigDecimal(5.00));
		
		lineItemRepo.save(item3);
		
		List<LineItem> items = lineItemRepo.findAll();
		
		invoice.setItems(items);
		
		return invoiceRepo.save(invoice);
	}

	@RequestMapping(value = "/{invoiceId}",method = RequestMethod.GET)
	public Invoice getInvoiceById(@PathVariable Long invoiceId){
		return invoiceRepo.findById(invoiceId).get();
	}
}
