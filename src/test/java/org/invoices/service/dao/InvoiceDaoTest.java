package org.invoices.service.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.invoices.DomainAwareBase;
import org.invoices.Position;
import org.invoices.Invoice;
import org.invoices.service.dao.InvoiceDao;
import org.invoices.service.dao.PositionDao;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
 
@ContextConfiguration(locations = "/beans.xml")
public class InvoiceDaoTest extends DomainAwareBase {
     
    @Autowired
    private InvoiceDao invoiceDao;
 
    @Autowired
    private PositionDao positionDao;
 
    @Test
    public void testAdd() {
        int size = invoiceDao.list().size();
 
        Invoice inv = newSpringInvoice();
        invoiceDao.add(inv);
 
        assertTrue(size < invoiceDao.list().size());
    }
 
    @Test
    public void testUpdate() {
    	Invoice inv = newSpringInvoice();
        invoiceDao.add(inv);
         
        // update task
        inv.setBuyer("New buyer");
        invoiceDao.update(inv);
 
        Invoice found = invoiceDao.find(inv.getId());
        assertEquals("New buyer", found.getBuyer());
    }
 
    @Test
    public void testFind() {
    	Invoice inv = newSpringInvoice();
        invoiceDao.add(inv);
         
        assertEquals(inv, invoiceDao.find(inv.getId()));
    }
     
    @Test
    public void testList() {
        assertEquals(0, invoiceDao.list().size());
        Invoice templateInvoice = newSpringInvoice();
         
        List<Invoice> invoices = Arrays.asList(
                newInvoiceFromTemplate(templateInvoice, "1"),
                newInvoiceFromTemplate(templateInvoice, "2"),
                newInvoiceFromTemplate(templateInvoice, "3")
        );
        for (Invoice inv : invoices) {
            invoiceDao.add(inv);
        }
 
        List<Invoice> found = invoiceDao.list();
        assertEquals(3, found.size());
        for (Invoice f : found) {
            assertTrue(invoices.contains(f));
        }
    }
     
    @Test
    public void testRemove() {
    	Invoice invoice = newSpringInvoice();
        invoiceDao.add(invoice);
         
        // successfully added
        assertEquals(invoice, invoiceDao.find(invoice.getId()));
         
        // try to remove
        invoiceDao.remove(invoice);
        assertNull(invoiceDao.find(invoice.getId()));
    }
 
    /**
     * @return Dummy invoice for testing
     */
    private Invoice newSpringInvoice() {
 
        Position poz1 = new Position("pozycja1", 159);
        Position poz2 = new Position("pozycja2", 49);
        positionDao.add(poz1);
        positionDao.add(poz2);
 
        return new Invoice("Sprzedawca", "Nabywca", poz1, poz2);
    }
 
    /**
     * Creates dummy invoice for testing as copy of existing task and
     * adds additional information to every field.
     * @param templateInvoice Invoice to copy
     * @param randomInfo Info to append everywhere
     * @return Random invoice for testing
     */
    private Invoice newInvoiceFromTemplate(Invoice templateInvoice, 
            String randomInfo) {
        String buyer = templateInvoice.getBuyer() 
                + randomInfo;
        
        String sprzedawca = templateInvoice.getSeller() 
                + randomInfo;
         
        List<Position> templatePoz = templateInvoice.getPositions();
        Position[] pozycje = new Position[templatePoz.size()];
 
        int idx = 0;
        for (Position templatePosition : templatePoz) {
        	Position poz = new Position(
                    templatePosition.getName() + randomInfo,
                    templatePosition.getPrice());
            pozycje[idx++] = poz;
            positionDao.add(poz);
        }
 
        return new Invoice(buyer, sprzedawca, pozycje);
    }
}