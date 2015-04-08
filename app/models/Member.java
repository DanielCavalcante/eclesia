package models;

public class Member {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private String ministry;
	private String conversionDate;
	private String birthDate;
	private String image;
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMinistry() {
		return ministry;
	}

	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}

	public String getConversionDate() {
		return conversionDate;
	}

	public void setConversionDate(String conversionDate) {
		this.conversionDate = conversionDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Name: " + getName() + "\nPhone: " + getPhone() + "\nMail: "
				+ getEmail() + "\nMinistry: " + getMinistry()
				+ "\nConversion date: " + getConversionDate()
				+ "\nBirth date: " + getBirthDate()
				+ "\nImage: " + getImage()
				+ "\nAdress" + "\nStreet: "
				+ getAddress().getStreet() + "\nCEP: " + getAddress().getCep()
				+ "\nCity: " + getAddress().getCity() + "\nState: "
				+ getAddress().getState();
	}

}