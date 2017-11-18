import React, { Component } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

import { ThingItem } from "./Thing/listItem"

class Things extends Component {
  static createListItem(element) {
    return (
      <ThingItem
        name = {element.name}

      />
    );
  }

  render() {
    return (
      <div>
        <label className={"glyphicon " + this.props.glyphicon}>{this.props.elementsName}</label>
        <ul className="nav nav-pills nav-stacked">
          {this.props.elements.map(Things.createListItem)}
        </ul>



        {/* <c:forEach items="${product.things}" var="thing"> */}
        <div className="row">
          <div className="col-sm-4">
            {/* <c:if test="${not empty thing.photos}">
                                    <img src="${thing.photos[0].href}" class="img-responsive" style="width:100%" alt="Image">
                                </c:if>
                                <c:if test="${empty thing.photos}">
                                    <img src="https://placehold.it/255x135?text=No image" class="img-responsive" style="width:100%" alt="Image">
                                </c:if> */}
          </div>
          <div className="col-sm-6 thing-desc-box">
            <h3>${thing.name}</h3>
            <label>${description} : ${thing.description}</label>
            <br />
            <label>${category} : ${thing.category}</label>
            <br />
            <label>${brand} : ${thing.brand}</label>
            <br />
            <label>${rating} : ${thing.rating}</label>
          </div>
        </div>
        <br />
        {/* </c:forEach> */}
      </div>
    );
  }
}

export const ProductThingsComponent = Things;
