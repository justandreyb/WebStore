import { fromJS } from "immutable";
import { sendElement, getElement, deleteElement, getElements } from "../../api";
import { takeEvery, takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const LOAD_CART_REQUEST = "LOAD_CART_REQUEST";
const LOAD_CART_SUCCESS = "LOAD_CART_SUCCESS";
const LOAD_CART_FAIL = "LOAD_CART_FAIL";

const ADD_TO_CART_REQUEST = "ADD_TO_CART_REQUEST";
const ADD_TO_CART_SUCCESS = "ADD_TO_CART_SUCCESS";
const ADD_TO_CART_FAIL = "ADD_TO_CART_FAIL";

const REMOVE_FROM_CART_REQUEST = "REMOVE_FROM_CART_REQUEST";
const REMOVE_FROM_CART_SUCCESS = "REMOVE_FROM_CART_SUCCESS";
const REMOVE_FROM_CART_FAIL = "REMOVE_FROM_CART_FAIL";

const BUY_PRODUCTS_REQUEST = "BUY_PRODUCTS_REQUEST";
const BUY_PRODUCTS_SUCCESS = "BUY_PRODUCTS_SUCCESS";
const BUY_PRODUCTS_FAIL = "BUY_PRODUCTS_FAIL";

const CLEAN_CART = "CLEAN_CART";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  products: [],
  error   : null
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case LOAD_CART_REQUEST:
    return state
      .set("error", null);

  case LOAD_CART_SUCCESS:
    return state
      .set("products", action.payload)
      .set("error", null);

  case LOAD_CART_FAIL:
    return state
      .set("error", action.payload);


  case ADD_TO_CART_REQUEST:
    return state
      .set("error", null);

  case ADD_TO_CART_SUCCESS:
    return state
      .updateIn(["products"], (arr) => arr.push(action.payload))
      .set("error", null);

  case ADD_TO_CART_FAIL:
    return state
      .set("error", action.payload);


  case REMOVE_FROM_CART_REQUEST:
    return state
      .set("cart", state.get("products").filter((o) => o.id !== action.payload))
      .set("error", null);

  case REMOVE_FROM_CART_SUCCESS:
    return state
      .set("error", null);

  case REMOVE_FROM_CART_FAIL:
    return state
      .set("error", action.payload);

  case BUY_PRODUCTS_REQUEST:
    return state
      .set("error", null);

  case BUY_PRODUCTS_SUCCESS:
    return initialState;

  case BUY_PRODUCTS_FAIL:
    return state
      .set("error", action.payload);

  case CLEAN_CART:
    return initialState;

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const loadCartRequest = () => ({
  type: LOAD_CART_REQUEST
});

export const loadCartSuccess = (data) => ({
  type   : LOAD_CART_SUCCESS,
  payload: data
});

export const loadCartFail = (error) => ({
  type   : LOAD_CART_FAIL,
  payload: error,
  error  : true
});


export const addToCartRequest = (data) => ({
  type   : ADD_TO_CART_REQUEST,
  payload: data
});

export const addToCartSuccess = () => ({
  type: ADD_TO_CART_SUCCESS
});

export const addToCartFail = (error) => ({
  type   : ADD_TO_CART_FAIL,
  payload: error,
  error  : true
});


export const removeFromCartRequest = (id) => ({
  type   : REMOVE_FROM_CART_REQUEST,
  payload: id
});

export const removeFromCartSuccess = () => ({
  type: REMOVE_FROM_CART_SUCCESS
});

export const removeFromCartFail = (error) => ({
  type   : REMOVE_FROM_CART_FAIL,
  payload: error,
  error  : true
});

export const buyProductsRequest = () => ({
  type: BUY_PRODUCTS_REQUEST
});

export const buyProductsSuccess = () => ({
  type: BUY_PRODUCTS_SUCCESS
});

export const buyProductsFail = (error) => ({
  type   : BUY_PRODUCTS_FAIL,
  payload: error,
  error  : true
});

export const cleanAccountData = () => ({
  type: CLEAN_CART
});

// ----------------------- SAGAS ------------------------ //

function* loadCart() {
  yield getElements("/cart", loadCartSuccess, loadCartFail);
}

function* addToCart(action) {
  yield sendElement("/cart", action.payload, addToCartSuccess, addToCartFail);
}

function* removeFromCart(action) {
  yield getElement("/cart", action.payload, removeFromCartSuccess, removeFromCartFail);
}

function* buyProducts(action) {
  yield deleteElement("/cart", action.payload, buyProductsSuccess, buyProductsFail);
}

export function* watchCartActions() {
  yield takeLatest(LOAD_CART_REQUEST, loadCart);
  yield takeEvery(ADD_TO_CART_REQUEST, addToCart);
  yield takeEvery(REMOVE_FROM_CART_REQUEST, removeFromCart);
  yield takeLatest(BUY_PRODUCTS_REQUEST, buyProducts);
}

// ------------------ SELECTORS -------------------- //

export const selectCartContainer = (state) => state.containers.app.account.cart;
export const selectCartData = (state) => selectCartContainer(state).get("products");
