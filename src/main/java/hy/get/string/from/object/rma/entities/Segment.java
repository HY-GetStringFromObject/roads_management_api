package hy.get.string.from.object.rma.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Segment {

	private Integer segId;
	private String name;
	private Double length;
	private Timestamp modifyDate;
	private Timestamp insertDate;
	private Node nodeByFirNode;
	private Node nodeBySecNode;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seg_id", unique = true, nullable = false)
	public Integer getSegId() {
		return segId;
	}

	public void setSegId(Integer segId) {
		this.segId = segId;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Segment segment = (Segment) o;

		return Objects.equals(segId, segment.segId) &&
			Objects.equals(name, segment.name) &&
			Objects.equals(length, segment.length) &&
			Objects.equals(modifyDate, segment.modifyDate) &&
			Objects.equals(insertDate, segment.insertDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(segId, name, length, modifyDate, insertDate);
	}

	@ManyToOne
	@JoinColumn(name = "fir_node", referencedColumnName = "nod_id", nullable = false)
	public Node getNodeByFirNode() {
		return nodeByFirNode;
	}

	public void setNodeByFirNode(Node nodeByFirNode) {
		this.nodeByFirNode = nodeByFirNode;
	}

	@ManyToOne
	@JoinColumn(name = "sec_node", referencedColumnName = "nod_id", nullable = false)
	public Node getNodeBySecNode() {
		return nodeBySecNode;
	}

	public void setNodeBySecNode(Node nodeBySecNode) {
		this.nodeBySecNode = nodeBySecNode;
	}

}
