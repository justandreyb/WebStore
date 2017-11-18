import { fork, all } from "redux-saga/effects";

import { watchAppActions } from "./modules/App";
import { watchAccountActions } from "./modules/Account";
import { watchCartActions } from "./modules/UserCart";
import { watchProductActions } from "./modules/Product";
import { watchProductsActions } from "./modules/Products";
import { watchThingsActions } from "./modules/Things";
import { watchThingActions } from "./modules/Thing";
import { watchBrandActions } from "./modules/Brand";
import { watchBrandsActions } from "./modules/Brands";
import { watchCategoryActions } from "./modules/Category";
import { watchCategoriesActions } from "./modules/Categories";

const sagas = [
  watchAppActions,
  watchAccountActions,
  watchCartActions,

  watchProductActions,
  watchProductsActions,

  watchThingsActions,
  watchThingActions,

  watchBrandActions,
  watchBrandsActions,

  watchCategoryActions,
  watchCategoriesActions
];

export default function* globalSagas() {
  const globalSagasForks = sagas.map((saga) => fork(saga));

  yield all([...globalSagasForks]);
}
