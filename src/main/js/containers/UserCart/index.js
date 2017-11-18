import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

import "../../../webapp/resources/css/table.css"

import {
  loadCartRequest,
  selectCartData
} from "../../modules/UserCart"
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";

class CartContainer extends Component {

  cellFormatter = (cell, row) => {
    return (
      <button
        type='button'
        className='btn btn-danger'
        onClick={ () => this.handleDelete(row) }
      >
        <i>&#10006;</i>
      </button>
    );
  };

  componentWillMount() {
    this.props.actions.loadCartRequest()
  }

  render() {
    return (
      <div className="container">
        <div className="text-center">
          <h3>Cart</h3>
          <div className="row">
            <BootstrapTable data={this.props.products} options={this.options}>
              <TableHeaderColumn width='55' dataField='id' isKey>ID</TableHeaderColumn>
              <TableHeaderColumn dataField='name'>Name</TableHeaderColumn>
              <TableHeaderColumn width='90' dataField='price'>Price</TableHeaderColumn>
              <TableHeaderColumn width='55' dataFormat={this.cellFormatter.bind(this)} />
            </BootstrapTable>
          </div>
        </div>
      </div>
    )
  }
}

export const Cart = connect(
  (state) => ({
    products: selectCartData(state)
  }),
  (dispatch) => ({
    actions: bindActionCreators({
      loadCartRequest
    }, dispatch)
  })
)(CartContainer);
