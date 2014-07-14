package org.invoices.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.invoices.Position;
import org.invoices.service.dao.PositionDao;

@Controller
@RequestMapping("/positions")
public class PositionController {

	private PositionDao positionDao;
	
	@Autowired 
	public void setPositionDao(PositionDao positionDao){
		this.positionDao = positionDao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPositions(Model model){
		List <Position> positions = positionDao.list();
		model.addAttribute("positions", positions);
		
		return "positions/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getPosition(@PathVariable("id") long id, Model model) {
	    Position poz = positionDao.find(id);
	    model.addAttribute("position", poz);
	 
	    return "positions/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updatePosition(@PathVariable("id") long id, Position poz) {
	    poz.setId(id);
	    positionDao.update(poz);
	 
	    return "redirect:/positions";
	}
}
