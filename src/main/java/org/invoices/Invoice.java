package org.invoices;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String seller;
	private String buyer;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "invoice_position", joinColumns = { @JoinColumn(name = "invoice_id") }, inverseJoinColumns = { @JoinColumn(name = "position_id") })
	private List<Position> positions = new ArrayList<Position>();

	public Invoice(){
		
	}
	public Invoice(String seller, String buyer) {
		this.seller = seller;
		this.setBuyer(buyer);
	}
	
	public Invoice(String seller, String buyer, Position ...pos) {
		this.buyer = buyer;
		this.seller = seller;
		for (int i=0; i< pos.length; i++){
			this.addPosition(pos[i]);
		}
	}
	
	public void addPosition(Position pos) {
		positions.add(pos);
	}
	
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
System.out.println("DUUUUUUPAAAAAAA");


		if (obj == null)
			return false;
System.out.println("1: " + this.hashCode());
System.out.println("2: " + obj.hashCode());
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
System.out.println("3: " + other.hashCode());

		if (buyer == null) {
			if (other.buyer != null){
				System.out.println("buyers 1");
				return false;

			}
		} else if (!buyer.equals(other.buyer)){
			System.out.println("buyers 2");
			System.out.println(this.buyer);
			System.out.println(other.buyer);
			return false;
		}
		if (id == null) {
			if (other.id != null){
				System.out.println("id 1");
				return false;
			}
		} else if (!id.equals(other.id)){
			System.out.println("id 2");
			return false;
		}
		if (positions == null) {
			if (other.positions != null){
				
				System.out.println("positions 1");
				return false;
			}
		} else if (positions.isEmpty()) {
			if (!other.positions.isEmpty()){
				System.out.println("positions 2");
				return false;
			}
		} else if (!positions.equals(other.positions)){
			System.out.println("positions 3");
			System.out.println(this.toString());
			System.out.println(other.toString());
			return false;
		}
		if (seller == null) {
			if (other.seller != null){
				System.out.println("seller 1");
				return false;
			}
		} else if (!seller.equals(other.seller)){
			System.out.println("seller 2");	
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", seller=" + seller + ", buyer=" + buyer
				+ ", positions=" + positions + "]";
	}
	
	
}
