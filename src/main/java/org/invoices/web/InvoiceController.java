package org.invoices.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.invoices.Invoice;
import org.invoices.Position;
import org.invoices.service.dao.InvoiceDao;
import org.invoices.service.dao.PositionDao;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

	private InvoiceDao invoiceDao;
	private PositionDao positionDao;
	
	@Autowired 
	public void setInvoiceDao(InvoiceDao invoiceDao){
		this.invoiceDao = invoiceDao;
	}
	
	@Autowired
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public InvoiceDao getInvoiceDao() {
		return invoiceDao;
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showInvoices(Model model){
		List <Invoice> invoices = invoiceDao.list();
		model.addAttribute("invoices", invoices);
		return "invoices/list";
	}
	/**
	 * Removes assigned position from invoice
	 * @param id Invoice's ID
	 * @param positionId Assigned position's ID
	 */
	@RequestMapping(value = "/{id}/positions/{positionId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePosition(
	        @PathVariable("id") long invoiceId,
	        @PathVariable("positionId") long positionId) {
	 
	    Position pos = positionDao.find(positionId);
	    Invoice inv = invoiceDao.find(invoiceId);
	 
	    inv.removePosition(pos);
	    invoiceDao.update(inv);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteInvoice(@PathVariable("id") long id) {
	 
	    Invoice toDelete = invoiceDao.find(id);
	    invoiceDao.remove(toDelete);
	    return "redirect:/invoices";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getInvoice(@PathVariable("id") long id, Model model) {
	    Invoice inv = invoiceDao.find(id);
	    model.addAttribute("invoice", inv);
	    List <Position> positions = positionDao.list();
	    Set<Position> unlisted = new HashSet<Position>();
	    for (Position pos : positions){
	    	if (!inv.getPositions().contains(pos))
	    		unlisted.add(pos);
	    }
		model.addAttribute("unlisted", unlisted);
	    return "invoices/view";
	}
	
	
	
	/**
	 * Assigns position to tak
	 * @param invoiceId Invoice's ID
	 * @param positionId Position's ID (to assign)
	 * @return redirects back to altered invoice: invoices/invoiceId
	 */
	@RequestMapping(value = "/{id}/positions/{positionId}", method = RequestMethod.PUT)
	public String addPosition(
	        @PathVariable("id") long invoiceId,
	        @PathVariable("positionId") long positionId) {
	 
	    Position position = positionDao.find(positionId);
	    Invoice invoice = invoiceDao.find(invoiceId);
	 
	    invoice.addPosition(position);
	    invoiceDao.update(invoice);
	     
	    return "redirect:/invoices/" + invoiceId;
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	public String createInvoiceForm(Model model) {
	    model.addAttribute("invoice", new Invoice());
	    return "invoices/new";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String addInvoice(Invoice invoice) {
	    invoiceDao.add(invoice);
	 
	    return "redirect:/invoices";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateInvoice(@PathVariable("id") long id, Invoice inv) {
	    inv.setId(id);
	    invoiceDao.update(inv);
	 
	    return "redirect:/invoices";
	}
}
