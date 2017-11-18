import React, { Component } from "react";

import Navbar from "react-bootstrap/lib/Navbar";

class FooterContainer extends Component {
  render() {
    return (
      <Navbar fixedBottom>
        <p className="container text-center">Hello</p>
      </Navbar>
    );
  }
}

export const Footer = FooterContainer;
