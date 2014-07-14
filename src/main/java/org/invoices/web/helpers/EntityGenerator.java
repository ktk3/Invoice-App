package org.invoices.web.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.invoices.Invoice;
import org.invoices.Position;
import org.invoices.service.GenericDao;
import org.invoices.service.dao.InvoiceDao;
import org.invoices.service.dao.PositionDao;
/*
 * Small util helper for generating entities to simulate
 */
@Service
public final class EntityGenerator {

	@Autowired
	private PositionDao positionDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	public void generateDomain(){
		Position poz1 = new Position("position 1", 159); 
		Position poz2 = new Position("position 2", 25);
		Position poz3 = new Position("position 3", 50);
		
		Position poz10 = new Position("position 10", 159);
		Position poz11 = new Position("position 11", 99);
		Position poz12 = new Position("position 12", 49);
		Position poz13 = new Position("position 13", 59);
		
		Position poz21 = new Position("position 21", 1500);
		Position poz22 = new Position("position 22", 200);
		
		addAll(positionDao, poz1, poz2, poz3);
		addAll(positionDao, poz10, poz11, poz12, poz13);
		addAll(positionDao, poz21, poz22);
		
		Invoice invoice1 = new Invoice("Seller1", "Buyer1", poz1, poz2, poz3);
		Invoice invoice2 = new Invoice("Seller2", "Buyer2", poz10, poz11, poz12, poz13);
		Invoice invoice3 = new Invoice("Seller3", "Buyer1", poz21, poz22);
		Invoice invoice4 = new Invoice("Seller4", "Buyer4");
		
		addAll(invoiceDao, invoice1, invoice2, invoice3, invoice4);
	}
	
	public void deleteDomain(){
		List<Position> pozycje = positionDao.list();
		for (Position poz : pozycje){
			positionDao.remove(poz);
		}
		
		List<Invoice> invoices = invoiceDao.list();
		for (Invoice f : invoices){
			invoiceDao.remove(f);
		}
	}
	
	private <T> void addAll(GenericDao<T, Long> dao, T... entities){
		for (T o : entities){
			dao.add(o);
		}
	}
	
}
