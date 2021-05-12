package com.ust.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Favourite")


public class Favourite {
@Id
//@GeneratedValue(strategy= GenerationType.IDENTITY)
	
private int id;
public Favourite(int id, String userId, String restaurantName, String category, String foodItem, double price,
		String username) {
	super();
	this.id = id;
	this.userId = userId;
	this.restaurantName = restaurantName;
	this.category = category;
	this.foodItem = foodItem;
	this.price = price;
	this.username = username;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
private String userId;

public  int getId() {
	return id;
}
public Favourite() {
	super();
	// TODO Auto-generated constructor stub
}
public void setId(int id) {
	this.id = id;
}
public String getRestaurantName() {
	return restaurantName;
}
public void setRestaurantName(String restaurantName) {
	this.restaurantName = restaurantName;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getFoodItem() {
	return foodItem;
}
public void setFoodItem(String foodItem) {
	this.foodItem = foodItem;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
private String restaurantName;

private String category;

private String foodItem;

private double price;
/**
 * @Email annotation is used to validate the field to be email
 *
 **/

private String username;

}
