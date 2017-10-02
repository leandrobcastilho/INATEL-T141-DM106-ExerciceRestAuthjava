package br.inatel.t141.dm107.execicirestauth.data.product;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductEntity {

	public ProductEntity() {
	}

	private Long id;
	private String name;

	public ProductEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

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

}
