import React, { Component } from "react"
import { connect } from "react-redux"
import { Redirect, Route } from "react-router-dom";


class SuperuserRouteContainer extends Route {
  render() {
    return (
      <Route render={
        this.props.info.superuser
          ? <Component {...this.props}/>
          : <Redirect to={{
            pathname: "/home",
            state   : { from: this.props.location }
          }}/>
      }/>
    )
  }
}

export const SuperuserRoute = connect(
  (state) => ({
    info    : state.containers.app.account.info,
    location: state.route.location
  })
)(SuperuserRouteContainer);
