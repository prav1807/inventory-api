package com.project.template.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * ItemRequestApiBean
 */

@JsonTypeName("ItemRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-25T16:31:02.320211+04:00[Indian/Mauritius]")
public class ItemRequestApiBean {

  @NotBlank
  private String name;

  @NotNull
  @PositiveOrZero(message = "Quantity should be zero or greater than zero")
  private Integer quantity;

  @Positive(message = "Price should be greater than zero")
  private Float price;

  /**
   * Default constructor
   * @deprecated Use {@link ItemRequestApiBean#ItemRequestApiBean(String, Integer, Float)}
   */
  @Deprecated
  public ItemRequestApiBean() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ItemRequestApiBean(String name, Integer quantity, Float price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public ItemRequestApiBean name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull
  @Schema(name = "name", example = "Laptop", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ItemRequestApiBean quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */
  @NotNull
  @Schema(name = "quantity", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ItemRequestApiBean price(Float price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @NotNull
  @Schema(name = "price", example = "999.99", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemRequestApiBean itemRequest = (ItemRequestApiBean) o;
    return Objects.equals(this.name, itemRequest.name) &&
        Objects.equals(this.quantity, itemRequest.quantity) &&
        Objects.equals(this.price, itemRequest.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, quantity, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemRequestApiBean {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

