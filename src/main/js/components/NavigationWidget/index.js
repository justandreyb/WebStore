import React, { Component } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

class NavigationWidget extends Component {
  static createListItem(elementsName, element) {
    return (
      <li key={element.id}>
        <Link to={"/" + elementsName + "/" + element.id}>{element.name}</Link>
      </li>
    );
  }

  render() {
    return (
      <div className="well">
        <label className={"glyphicon " + this.props.glyphicon}>    {this.props.elementsName}</label>
        <ul className="nav nav-pills nav-stacked">
          {this.props.elements.map((element) => NavigationWidget.createListItem(this.props.elementsName, element))}
        </ul>
      </div>
    );
  }
}

NavigationWidget.propTypes = {
  elementsName: PropTypes.string,
  glyphicon   : PropTypes.string
};

export const NavigationWidgetComponent = NavigationWidget;
