package org.invoices.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.invoices.Invoice;
import org.invoices.service.dao.InvoiceDao;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends HibernateDao<Invoice, Long> implements
		InvoiceDao {

	@Override
	public List<Invoice> list(int pageIndex, int pageSize) {
		Query q = currentSession().createQuery("from " + Invoice.class.getName());	

		return currentSession().createCriteria(Invoice.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}
	
	public boolean removeInvoice(Invoice invoice) {
        remove(invoice);
        return true;
    }
	
}
