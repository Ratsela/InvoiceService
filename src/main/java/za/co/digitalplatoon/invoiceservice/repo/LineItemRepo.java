package za.co.digitalplatoon.invoiceservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.digitalplatoon.invoiceservice.model.LineItem;

public interface LineItemRepo extends JpaRepository<LineItem, Long> {

}
