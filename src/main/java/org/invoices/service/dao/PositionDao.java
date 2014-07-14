package org.invoices.service.dao;

import org.invoices.Position;
import org.invoices.service.GenericDao;

public interface PositionDao extends GenericDao<Position, Long>{

	/**
	 * Tries to remove position from the system.
	 * @param position Position to remove
	 * @return {@code true} id position is not
	 * element of any invoice. Else {@code false}.
	 */
	boolean removePosition(Position position);
}
