import React, { Component } from "react";
import { Link } from "react-router-dom";

const imageStyle = {
  width: "100%"
};

class ProductItem extends Component {
  render() {
    return (
      <div className="col-sm-4">
        <img src={this.props.product.img} className="img-responsive" style={imageStyle} alt={this.props.product.img} />
        <p><Link to={"/products/" + this.props.product.id}>{this.props.product.name}</Link></p>
      </div>
    );
  }
}

export const ProductItemComponent = ProductItem;
