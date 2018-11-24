package hy.get.string.from.object.rma.entities;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

	private Integer vehId;
	private Double weight;
	private String plate;
	private Timestamp modifyDate;
	private Timestamp insertDate;

	@Id
	@Column(name = "veh_id")
	public Integer getVehId() {
		return vehId;
	}

	public void setVehId(Integer vehId) {
		this.vehId = vehId;
	}

	@Basic
	@Column(name = "weight")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Basic
	@Column(name = "plate")
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
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
		Vehicle vehicle = (Vehicle) o;

		return Objects.equals(vehId, vehicle.vehId) &&
			Objects.equals(weight, vehicle.weight) &&
			Objects.equals(plate, vehicle.plate) &&
			Objects.equals(modifyDate, vehicle.modifyDate) &&
			Objects.equals(insertDate, vehicle.insertDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vehId, weight, plate, modifyDate, insertDate);
	}

}
