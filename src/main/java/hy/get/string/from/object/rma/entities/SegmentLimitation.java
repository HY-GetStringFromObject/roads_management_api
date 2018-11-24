package hy.get.string.from.object.rma.entities;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "segment_limitation")
public class SegmentLimitation {

	private Integer segLimId;
	private Timestamp modifyDate;
	private Timestamp insertDate;

	@Id
	@Column(name = "seg_lim_id")
	public Integer getSegLimId() {
		return segLimId;
	}

	public void setSegLimId(Integer segLimId) {
		this.segLimId = segLimId;
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
		SegmentLimitation that = (SegmentLimitation) o;

		return Objects.equals(segLimId, that.segLimId) &&
			Objects.equals(modifyDate, that.modifyDate) &&
			Objects.equals(insertDate, that.insertDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(segLimId, modifyDate, insertDate);
	}

}
