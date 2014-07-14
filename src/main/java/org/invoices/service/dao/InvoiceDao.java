package org.invoices.service.dao;

import org.invoices.Invoice;
import org.invoices.service.GenericDao;

public interface InvoiceDao extends GenericDao<Invoice, Long>{

	    /**
	     * Tries to remove an invoice from the system.
	     * @param invoice Invoice to remove
	     * @return {@code true} 
	     */
	    boolean removeInvoice(Invoice invoice);
	 
	}