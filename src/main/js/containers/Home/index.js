import React, { Component } from "react";

class HomeContainer extends Component {

  /* TODO: Create slider by react components */

  render() {
    return (
      <div className="container">
        <div className="topProducts text-center">
          <h3>asd</h3><br/>
          <div id="topProducts" className="carousel slide" data-ride="carousel">
                #NAV_ELEMS
                #SLIDES
            <div id="sliderButtons">
              <a className="left carousel-control" role="button" data-slide="prev">
                <span className="glyphicon glyphicon-chevron-left" aria-hidden="true" />
              </a>
              <a className="right carousel-control" role="button" data-slide="next">
                <span className="glyphicon glyphicon-chevron-right" aria-hidden="true" />
              </a>
            </div>
          </div>
        </div>

        <br/>

        <div className="well text-center" id="search-box">
          <h4>search</h4>
          <p>Here will be search box</p>
        </div>
      </div>
    );
  }
}

export const Home = HomeContainer;
