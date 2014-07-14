package org.invoices.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.invoices.Invoice;
import org.invoices.service.dao.InvoiceDao;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

	private InvoiceDao invoiceDao;
	
	@Autowired 
	public void setInvoiceDao(InvoiceDao invoiceDao){
		this.invoiceDao = invoiceDao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showInvoices(Model model){
		List <Invoice> invoices = invoiceDao.list();
		model.addAttribute("invoices", invoices);
		
		return "invoices/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getInvoice(@PathVariable("id") long id, Model model) {
	    Invoice inv = invoiceDao.find(id);
	    model.addAttribute("invoice", inv);
	 
	    return "invoices/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateInvoice(@PathVariable("id") long id, Invoice inv) {
	    inv.setId(id);
	    invoiceDao.update(inv);
	 
	    return "redirect:/invoices";
	}
}
