import React, { Component } from "react";
import {bindActionCreators} from "redux";
import { connect } from "react-redux";

import { NavigationWidgetComponent } from "../../../components"
import {
  selectBrandsData,
  sendBrandsRequest
} from "../../../modules/Brands"
import {
  selectCategoriesData,
  sendCategoriesRequest
} from "../../../modules/Categories"

class NavigationWidgetsContainer extends Component {

  componentWillMount() {
    this.props.actions.sendBrandsRequest();
    this.props.actions.sendCategoriesRequest();
  }

  render() {
    return (
      <div>
        <br />
        <div>
          <NavigationWidgetComponent
            elementsName="brands"
            glyphicon="glyphicon-tags"
            elements={this.props.brands}
          />
          <br />
          <NavigationWidgetComponent
            elementsName="categories"
            glyphicon="glyphicon-ice-lolly-tasted"
            elements={this.props.categories}
          />
        </div>
        <br />
      </div>
    );
  }
}

export const NavigationWidgets = connect(
  (state) => ({
    brands    : selectBrandsData(state),
    categories: selectCategoriesData(state)
  }),
  (dispatch) => ({
    actions: bindActionCreators({
      sendBrandsRequest,
      sendCategoriesRequest
    }, dispatch)
  })
)(NavigationWidgetsContainer);
