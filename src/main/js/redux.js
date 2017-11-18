import { combineReducers } from "redux";
import { routerReducer } from "react-router-redux";

import { reducer as appReducer } from "./modules/App";
import { reducer as accountReducer } from "./modules/Account";
import { reducer as cartReducer } from "./modules/UserCart";
import { reducer as authModalReducer } from "./modules/Modals/AuthModal";
import { reducer as productReducer } from "./modules/Product";
import { reducer as productsReducer } from "./modules/Products";
import { reducer as thingReducer } from "./modules/Thing";
import { reducer as thingsReducer } from "./modules/Things";
import { reducer as categoriesReducer } from "./modules/Categories";
import { reducer as categoryReducer } from "./modules/Category";
import { reducer as brandsReducer } from "./modules/Brands";
import { reducer as brandReducer } from "./modules/Brand";

const containersReducer = {
  containers: combineReducers({
    app: combineReducers({
      workspace: appReducer,
      account  : combineReducers({
        info: accountReducer,
        cart: cartReducer
      }),
      modals: combineReducers({
        auth: authModalReducer
      })
    }),
    products: combineReducers({
      list         : productsReducer,
      targetProduct: productReducer
    }),
    things: combineReducers({
      list       : thingsReducer,
      targetThing: thingReducer
    }),
    brands: combineReducers({
      list       : brandsReducer,
      targetBrand: brandReducer
    }),
    categories: combineReducers({
      list          : categoriesReducer,
      targetCategory: categoryReducer
    })
  })
};


const globalReducer =
  combineReducers({
    ...containersReducer,
    route: routerReducer
  })
;

export default globalReducer;
