package org.invoices.service.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.invoices.DomainAwareBase;
import org.invoices.Invoice;
import org.invoices.Position;
import org.invoices.service.dao.InvoiceDao;
import org.invoices.service.dao.PositionDao;

@ContextConfiguration(locations ={"/beans.xml"})
public class PositionDaoTest extends DomainAwareBase{
	
	@Autowired
	private PositionDao positionDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Test
	public void testAdd(){
		int size = positionDao.list().size();
		positionDao.add(new Position("test-position", 150));
		
		assertTrue(size < positionDao.list().size());
	}
	
	@Test
	public void testUpdate(){
		Position pos = new Position("test-position", 150);
		positionDao.add(pos);
		pos.setName("new position");
		
		positionDao.update(pos);
		Position found = positionDao.find(pos.getId());
		assertEquals(found, pos);
	}
	
	@Test
	public void testList(){
		assertEquals(0, positionDao.list().size());
		
		List<Position> poz = Arrays.asList(
				new Position("name1", 50),
				new Position("name2", 100),
				new Position("name3", 150));
		for (Position p : poz){
			positionDao.add(p);
		}
		
		List<Position> found = positionDao.list();
		assertEquals(3, found.size());
		for(Position p : found){
			assertTrue(poz.contains(p));
		}
	}
	
	@Test
	public void testRemove(){
		Position poz = new Position("Position-test", 99);
		positionDao.add(poz);
		
		//succesfully added
		assertEquals(poz, positionDao.find(poz.getId()));
		
		//try to remove
		positionDao.remove(poz);
		assertNull(positionDao.find(poz.getId()));
	}
	
	@Test
	public void testRemovePosition(){
		Position position = new Position("Position1", 200);
		positionDao.add(position);
		
		Invoice invoice = new Invoice("buyer", "seller");
		invoice.addPosition(position);
		invoiceDao.add(invoice);
		
		assertFalse(positionDao.removePosition(position));
		
		invoiceDao.remove(invoice);
		
		assertTrue(positionDao.removePosition(position));
		
	}
	
}
