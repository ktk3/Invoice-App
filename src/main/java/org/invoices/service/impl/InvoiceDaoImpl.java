package org.invoices.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.invoices.Invoice;
import org.invoices.Position;
import org.invoices.service.dao.InvoiceDao;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends HibernateDao<Invoice, Long> implements
		InvoiceDao {

	@Override
	public List<Invoice> list() {
		return currentSession().createCriteria(Invoice.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}
	
	public boolean removeInvoice(Invoice invoice) {
        //remove Positions from positions list
		
		//invoice.clearPositions();
		//update(invoice);
		
 
        // ok, remove as usual
        remove(invoice);
        return true;
    }

}
