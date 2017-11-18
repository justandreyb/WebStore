import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";

import { selectApiData } from "../../modules/App";

import { NavigationWidgets } from "../Widgets/NavigationWidgets"

import { Link } from "react-router-dom";

import logo from "./assets/logo.svg";

class AppContainer extends Component {
  render() {
    return (
      <div className="app container">
        <div className="col-md-2 hidden-sm hidden-xs">
          <NavigationWidgets />
        </div>
        <div className="col-md-10">
          <div className="app-header">
            <img src={logo} className="app-logo" alt="logo" />
            <h2>Welcome to React</h2>
          </div>
          <p className="app-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
          </p>
          <p className="app-intro">
            <label><Link to="/home">Home</Link></label>
          </p>
        </div>
      </div>
    );
  }
}

AppContainer.defaultProps = {
  apiData: {}
};

AppContainer.propTypes = {
  apiData: PropTypes.object
};

export const App = connect(
  (state) => ({
    apiData: selectApiData(state)
  })
)(AppContainer);
