package Shared;

import java.io.Serializable;
import java.util.Map;

import javax.json.Json;

import Utils.JsonMapper;

public class Order implements Serializable {

	public int id;
	public String status;
	public String comment;
	public String receipt;
	public int idTable;

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