import React, { Component } from "react"
import { connect } from "react-redux"
import { Redirect, Route } from "react-router-dom";


class GuestRouteContainer extends Route {
  render() {
    return (
      <Route render={
        this.props.info.guest
          ? <Component {...this.props}/>
          : <Redirect to={{
            pathname: "/home",
            state   : { from: this.props.location }
          }}/>
      }/>
    )
  }
}

export const GuestRoute = connect(
  (state) => ({
    info    : state.containers.app.account.info,
    location: state.route.location
  })
)(GuestRouteContainer);
