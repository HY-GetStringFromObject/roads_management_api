package hy.get.string.from.object.rma.entities;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Node {

	private Integer nodId;
	private Double lat;
	private Double lng;
	private Timestamp modifyDate;
	private Timestamp insertDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nod_id", unique = true, nullable = false)
	public Integer getNodId() {
		return nodId;
	}

	public void setNodId(Integer nodId) {
		this.nodId = nodId;
	}

	@Basic
	@Column(name = "lat")
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Basic
	@Column(name = "lng")
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
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
		if (!nodId.equals(node.nodId)) return false;
		if (!lat.equals(node.lat)) return false;
		if (!lng.equals(node.lng)) return false;
		if (modifyDate != null ? !modifyDate.equals(node.modifyDate) : node.modifyDate != null) return false;

		return insertDate != null ? insertDate.equals(node.insertDate) : node.insertDate == null;
	}

	@Override
	public int hashCode() {
		int result = nodId.hashCode();
		result = 31 * result + lat.hashCode();
		result = 31 * result + lng.hashCode();
		result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
		result = 31 * result + (insertDate != null ? insertDate.hashCode() : 0);

		return result;
	}

}
