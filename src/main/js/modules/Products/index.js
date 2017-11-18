import { fromJS } from "immutable";
import { getElements } from "../../api";
import { takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const GET_PRODUCTS_REQUEST = "GET_PRODUCTS_REQUEST";
const GET_PRODUCTS_SUCCESS = "GET_PRODUCTS_SUCCESS";
const GET_PRODUCTS_FAIL = "GET_PRODUCTS_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  products: [],
  error   : null,
  loading : false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case GET_PRODUCTS_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_PRODUCTS_SUCCESS:
    return state
      .set("products", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_PRODUCTS_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const sendProductsRequest = () => ({
  type: GET_PRODUCTS_REQUEST
});

export const getProductsSuccess = (data) => ({
  type   : GET_PRODUCTS_SUCCESS,
  payload: data
});

export const getProductsFail = (error) => ({
  type   : GET_PRODUCTS_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* getProducts() {
  yield* getElements("/products", getProductsSuccess, getProductsFail);
}

export function* watchProductsActions() {
  yield takeLatest(GET_PRODUCTS_REQUEST, getProducts);
}

// ------------------ SELECTORS -------------------- //

export const selectProductsContainer = (state) => state.containers.products.list;
export const selectProductsData = (state) => selectProductsContainer(state).get("products");
