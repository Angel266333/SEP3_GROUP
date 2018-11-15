package Shared;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.json.Json;
import javax.print.DocFlavor;

import Utils.JsonMapper;

public class Order implements Serializable {

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

	public Order(int id, String status, String comment, String receipt, int idTable) {
		this.id = id;
		this.status = status;
		this.comment = comment;
		this.receipt = receipt;
		this.idTable = idTable;
	}

	public String toString() {
		return Json.createObjectBuilder().add("id", id).add("status", status).add("comment", comment)
				.add("receipt", receipt).add("idTable", idTable).build().toString();
	}

	public static Order fromString(String serial) {
		Map<String, String> map = JsonMapper.parse(serial);

		int id = Integer.parseInt(map.get("id"));

		String status = map.get("status").toString();
		String comment = map.get("comment").toString();
		String receipt = map.get("receipt").toString();

		int idTable = Integer.parseInt(map.get("idTable"));

		return new Order(id, status, comment, receipt, idTable);
	}
}