package za.co.digitalplatoon.invoiceservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import za.co.digitalplatoon.invoiceservice.model.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

}
