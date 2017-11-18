import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { ConnectedRouter } from "react-router-redux";

import { store, history } from "./store";

import { Navigation, Footer } from "./containers"
import { routes } from "./routes"

ReactDOM.render(
  <Provider store={store}>
    <ConnectedRouter history={history}>
      <div>
        <Navigation />
        <div className="layout workspace">
          { routes }
        </div>
        <Footer />
      </div>
    </ConnectedRouter>
  </Provider>,
  document.getElementById("root"),
);
