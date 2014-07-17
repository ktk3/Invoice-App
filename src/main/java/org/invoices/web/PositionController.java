package org.invoices.web;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.invoices.Position;
import org.invoices.service.dao.PositionDao;
import org.invoices.web.exeptions.PositionDeleteException;
 
import java.util.List;
 
/**
 * Controller for handling Positions.
 */
@Controller
@RequestMapping("/positions")
public class PositionController {

	private PositionDao positionDao;
	
	@Autowired 
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
	
	public PositionDao getPositionDao() {
        return positionDao;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPositions(@RequestParam(required = false) final Integer page, Model model){
		int index = (page == null ? 0 : page);
		List <Position> positions = positionDao.list(index,5);
		model.addAttribute("positions", positions);
		model.addAttribute("has_prev", (index == 0  ? 0 : 1));
		long pages = positionDao.count();
		pages = pages / 5 + ((pages % 5) == 0  ? 0 : 1);
		model.addAttribute("has_next", (index < pages - 1  ? 1 : 0));
		model.addAttribute("next_page", index + 1);
		model.addAttribute("prev_page", index - 1);
		return "positions/list";
	}
	
	/**
     * Deletes position with specified ID
     * @param id Position's ID
     * @return redirects to positions if everything was ok
     * @throws PositionDeleteException When position cannot be deleted
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePosition(@PathVariable("id") long id)
            throws PositionDeleteException {
 
        Position toDelete = positionDao.find(id);
        boolean wasDeleted = positionDao.removePosition(toDelete);
 
        if (!wasDeleted) {
            throw new PositionDeleteException(toDelete);
        }
 
        // everything OK, see remaining positions
        return "redirect:/positions";
    }
    
    /**
     * Handles PositionDeleteException
     * @param e Thrown exception with position that couldn't be deleted
     * @return binds position to model and returns positions/delete-error
     */
    @ExceptionHandler(PositionDeleteException.class)
    public ModelAndView handleDeleteException(PositionDeleteException e) {
        ModelMap model = new ModelMap();
        model.put("position", e.getPosition());
        return new ModelAndView("positions/delete-error", model);
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
	
	/**
     * Creates form for new position
     * @param model Model to bind to HTML form
     * @return positions/new
     */
    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createPositionForm(Model model) {
        model.addAttribute("position", new Position());
        return "positions/new";
    }
 
    /**
     * Saves new position to the database
     * @param position Position to save
     * @return redirects to positions
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addPosition(Position position) {
        positionDao.add(position);
 
        return "redirect:/positions";
    }
}
