

package com.project.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long productId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@Min(0)
	private double price;
	@Min(1)
	private int currQty;
	
	private String image;
	
//	@OneToMany(mappedBy="product", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
//	//, cascade=CascadeType.ALL
//	private List<Image> images;
//	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	//private int catId;
	private Category category;
	
	
	
	

	
	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



//	public void setImages(List<Image> images) {
//		this.images = images;
//	}



	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCurrQty() {
		return currQty;
	}
	public void setCurrQty(int currQty) {
		this.currQty = currQty;
	}
//	public List<Image> getImages() {
//		return images;
//	}
//	public void addImage(Image image) {
//		if(images==null){
//			images = new ArrayList<Image>();
//			images.add(image);
//			return;
//		}
//		images.add(image);
//	}
//	public int getStockId() {
//		return stockId;
//	}
//	public void setStockId(int stockId) {
//		this.stockId = stockId;
//	}
//	public int getCatId() {
//		return catId;
//	}
//	public void setCatId(int catId) {
//		this.catId = catId;
//	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}
	
	
}

