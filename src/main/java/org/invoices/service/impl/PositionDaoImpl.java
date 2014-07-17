package org.invoices.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import org.invoices.Invoice;
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
	
	@Override
	public List<Position> list(int pageIndex, int pageSize) {
		Query q = currentSession().createQuery("FROM " + Position.class.getName());	
		q = q.setFirstResult(pageSize * pageIndex);
		q = q.setMaxResults(pageSize);
		q = q.setReadOnly(true);

		return (List<Position>) q.list();
	}
	
	public long count(){
		return (Long) currentSession().createCriteria(Position.class).setProjection(Projections.rowCount()).uniqueResult();
		 
	}

}
