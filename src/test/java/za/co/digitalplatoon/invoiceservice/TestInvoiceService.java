package za.co.digitalplatoon.invoiceservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestInvoiceService extends InvoiceServiceApplicationTests{

	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	@Test
	public void testGetAllInvoices() throws Exception {
		mockMvc.perform(get("/invoices")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void testAddInvoice() throws Exception {
		mockMvc.perform(post("/invoices")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void testGetInvoiceById() throws Exception {
		mockMvc.perform(get("/invoices/4")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
}
