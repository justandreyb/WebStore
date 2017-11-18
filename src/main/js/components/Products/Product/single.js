import React, { Component } from "react";

class Product extends Component {
  render() {
    return (
      <div className="container">

        <div className="container-fluid text-center">
          <div className="container-fluid">
            <div className="col-sm-10">
              <h3>{this.props.product.name}</h3>
              <h4>{this.props.product.price}</h4>
              <h5>{this.props.product.discount}</h5>
            </div>
            <div className="col-sm-2">
              <h5>Add to card</h5>
              {/* <c:if test="${sessionScope.user != null}">
                            <input type="button" className="btn btn-primary btn-add-to-order" value="Add to order" onclick="addToOrder()">
                        </c:if> */}
            </div>
          </div>
        
          <div className="row">
            
          </div>
        </div>

      </div>
    );
  }
}

export const ProductComponent = Product;
