import React from "react";
import { Switch, Route } from "react-router-dom";

import { App, Home, Product, Products, Cart } from "./containers";
import { GuestRoute, UserRoute, SuperuserRoute } from "./containers/App/AuthRoute"

export const routes =
    <Switch>
      <Route exact path="/" component={App} />
      <Route path="/home" component={Home} />
      <Route exact path="/products" component={Products} />
      <Route exact path="/products/:id" component={Product} />
      <UserRoute exact path="/cart" component={Cart} />
      <SuperuserRoute exact path="/dashboard" component={Cart} />
    </Switch>;
