import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

import { ProductComponent } from "../../components";

import {
  getProductRequest,
  cleanProductWorkspace,
  selectProductData
} from "../../modules/Product"

class ProductContainer extends Component {

  componentWillMount() {
    this.props.actions.getProductRequest(this.props.match.params.id)
  }

  componentWillUnmount() {
    this.props.actions.cleanProductWorkspace()
  }

  render() {
    return (
      <div className="container col-sm-10">
        <div className="text-center">
          <h3>Products</h3>
          <div className="row">
            <ProductComponent
              product={this.props.product}
            />
          </div>
        </div>
      </div>
    )
  }
}

export const Product = connect(
  (state) => ({
    product: selectProductData(state)
  }),
  (dispatch) => ({
    actions: bindActionCreators({
      getProductRequest,
      cleanProductWorkspace
    }, dispatch)
  })
)(ProductContainer);
