import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

import { ProductsComponent } from "../../components";

import {
  sendProductsRequest,
  selectProductsData
} from "../../modules/Products"

class ProductsContainer extends Component {

  componentWillMount() {
    this.props.actions.sendProductsRequest()
  }

  render() {
    return (
      <div className="container">
        <div className="text-center">
          <h3>Products</h3>
          <div className="row">
            <ProductsComponent
              elements={this.props.products}
            />
          </div>
        </div>
      </div>
    )
  }
}

export const Products = connect(
  (state) => ({
    products: selectProductsData(state)
  }),
  (dispatch) => ({
    actions: bindActionCreators({
      sendProductsRequest
    }, dispatch)
  })
)(ProductsContainer);
