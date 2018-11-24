package hy.get.string.from.object.rma.entities;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Node {

	private Integer nodId;
	private Double length;
	private Double width;
	private Timestamp modifyDate;
	private Timestamp insertDate;

	@Id
	@Column(name = "nod_id")
	public Integer getNodId() {
		return nodId;
	}

	public void setNodId(Integer nodId) {
		this.nodId = nodId;
	}

	@Basic
	@Column(name = "length")
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@Basic
	@Column(name = "width")
	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	@Basic
	@Column(name = "modify_date")
	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Basic
	@Column(name = "insert_date")
	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;

		return Objects.equals(nodId, node.nodId) &&
			Objects.equals(length, node.length) &&
			Objects.equals(width, node.width) &&
			Objects.equals(modifyDate, node.modifyDate) &&
			Objects.equals(insertDate, node.insertDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nodId, length, width, modifyDate, insertDate);
	}

}
