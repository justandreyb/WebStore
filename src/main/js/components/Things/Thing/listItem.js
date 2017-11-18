import React, { Component } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

class ThingItem extends Component {
  render() {
    return (
      <li key={this.props.id} className="col-sm-4">
        <div className="col-sm-4">
          <img src={this.props.img} className="img-responsive" style="width:100%" alt="Image" />
          <p><Link to={"/things/" + this.props.id}>{this.props.name}</Link></p>
        </div>
      </li>
    );
  }
}

export const ThingItemComponent = ThingItem;
