import React, { Component } from "react";

import { ProductItemComponent } from "./Product/listItem"

class Products extends Component {
  static createListItem(element) {
    return (
      <li key={element.id} className="well col-sm-4">
        <ProductItemComponent
          product = {element}
        />
      </li>
    );
  }

  showProducts() {
    let code;

    if (this.props.elements.size === 0)
      code = <label>Nothing to show...</label>;
    else
      code = <ul>{this.props.elements.map(Products.createListItem)}</ul>;
    
    return code;
  }

  render() {
    return (
      this.showProducts()
    );
  }
}

export const ProductsComponent = Products;
