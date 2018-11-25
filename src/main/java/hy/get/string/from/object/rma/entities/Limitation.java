package hy.get.string.from.object.rma.entities;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Limitation {

	private Integer limId;
	private Double maxWeight;
	private Timestamp fromDate;
	private Timestamp toDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lim_id", unique = true, nullable = false)
	public Integer getLimId() {
		return limId;
	}

	public void setLimId(Integer limId) {
		this.limId = limId;
	}

	@Basic
	@Column(name = "max_weight")
	public Double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Basic
	@Column(name = "from_date")
	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	@Basic
	@Column(name = "to_date")
	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Limitation that = (Limitation) o;

		return Objects.equals(limId, that.limId) &&
			Objects.equals(maxWeight, that.maxWeight) &&
			Objects.equals(fromDate, that.fromDate) &&
			Objects.equals(toDate, that.toDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(limId, maxWeight, fromDate, toDate);
	}

}
