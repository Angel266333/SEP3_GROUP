package Shared;

import java.io.Serializable;

public class Order implements Serializable {

   public int [] items;
	public int id;
	public String status;
	public String comment;
	public String receipt;
	public int idTable;

	public static class STATUS {
		public static final String PENDING = "PENDING";
		public static final String DELIVERED = "DELIVERED";
		public static final String PAID = "PAID";

		public static String forName(String name) {
			switch(name) {
				case STATUS.DELIVERED:
					return name;
				case STATUS.PAID:
					return name;
				case STATUS.PENDING:
					return name;
				default:
					return null;
			}
		}

	}


	public Order() {

	}

	public Order(int id, int idTable, String status, String comment, String receipt, int []items) {
		this.id = id;
		this.status = status;
		this.comment = comment;
		this.receipt = receipt;
		this.idTable = idTable;
		this.items = items;
	}
	
	
}