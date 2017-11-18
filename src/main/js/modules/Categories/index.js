import { fromJS } from "immutable";
import { getElements } from "../../api";
import { takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const GET_CATEGORIES_REQUEST = "GET_CATEGORIES_REQUEST";
const GET_CATEGORIES_SUCCESS = "GET_CATEGORIES_SUCCESS";
const GET_CATEGORIES_FAIL = "GET_CATEGORIES_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  categories: [],
  error     : null,
  loading   : false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case GET_CATEGORIES_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_CATEGORIES_SUCCESS:
    return state
      .set("categories", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_CATEGORIES_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const sendCategoriesRequest = () => ({
  type: GET_CATEGORIES_REQUEST
});

export const getCategoriesSuccess = (data) => ({
  type   : GET_CATEGORIES_SUCCESS,
  payload: data
});

export const getCategoriesFail = (error) => ({
  type   : GET_CATEGORIES_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* getCategories() {
  yield* getElements("/categories", getCategoriesSuccess, getCategoriesFail);
}

export function* watchCategoriesActions() {
  yield takeLatest(GET_CATEGORIES_REQUEST, getCategories);
}

// ------------------ SELECTORS -------------------- //

export const selectCategoriesContainer = (state) => state.containers.categories.list;
export const selectCategoriesData = (state) => selectCategoriesContainer(state).get("categories");
