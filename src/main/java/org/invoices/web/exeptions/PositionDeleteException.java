package org.invoices.web.exeptions;

import org.invoices.Position;
/**
 * When position cannot be deleted.
 */
public class PositionDeleteException extends Exception {

	private Position position;

 
    public PositionDeleteException(Position position) {
        this.position = position;
    }
 
    public Position getPosition() {
        return position;
    }
}