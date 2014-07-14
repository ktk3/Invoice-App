package org.invoices.service.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import org.invoices.Position;
import org.invoices.service.dao.PositionDao;

@Repository("positionDao")
public class PositionDaoImpl extends HibernateDao<Position, Long> implements PositionDao{

	public boolean removePosition(Position pos) {
		Query positionInvoiceQuery = currentSession().createQuery(
				"FROM Invoice inv WHERE :id IN elements(inv.positions)");
		positionInvoiceQuery.setParameter("id", pos.getId());
		//pos cannot be on any invoice
		if (!positionInvoiceQuery.list().isEmpty()){
			return false;
		}
		
		remove(pos);
		return true;
	}

}
