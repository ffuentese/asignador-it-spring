package com.ffuentese.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "Id")
	@NotNull		
	private Type type;
	@ManyToOne
	@JoinColumn(referencedColumnName = "Id")
	@NotNull	
	private Brand brand;
	@ManyToOne
	@JoinColumn(referencedColumnName = "Id")
	@NotNull		
	private Model model;
	@ManyToOne
	@JoinColumn(referencedColumnName = "Id")
	@NotNull
	private Employee employee;
	private String serial;
	@Column
	@Lob
	private String notes;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

	
}