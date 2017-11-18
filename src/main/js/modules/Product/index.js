import { fromJS } from "immutable";
import { sendElement, getElement, updateElement, deleteElement } from "../../api";
import { takeEvery, takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const PRODUCTS_URL = "/products";

const CREATE_PRODUCT_REQUEST = "CREATE_PRODUCT_REQUEST";
const CREATE_PRODUCT_SUCCESS = "CREATE_PRODUCT_SUCCESS";
const CREATE_PRODUCT_FAIL = "CREATE_PRODUCT_FAIL";

const GET_PRODUCT_REQUEST = "GET_PRODUCT_REQUEST";
const GET_PRODUCT_SUCCESS = "GET_PRODUCT_SUCCESS";
const GET_PRODUCT_FAIL = "GET_PRODUCT_FAIL";

const UPDATE_PRODUCT_REQUEST = "UPDATE_PRODUCT_REQUEST";
const UPDATE_PRODUCT_SUCCESS = "UPDATE_PRODUCT_SUCCESS";
const UPDATE_PRODUCT_FAIL = "UPDATE_PRODUCT_FAIL";

const DELETE_PRODUCT_REQUEST = "DELETE_PRODUCT_REQUEST";
const DELETE_PRODUCT_SUCCESS = "DELETE_PRODUCT_SUCCESS";
const DELETE_PRODUCT_FAIL = "DELETE_PRODUCT_FAIL";

const CLEAN_PRODUCT_WORKSPACE = "CLEAN_PRODUCT_WORKSPACE";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  product: {},
  error  : null,
  loading: false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case CREATE_PRODUCT_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case CREATE_PRODUCT_SUCCESS:
    return state
      .set("product", action.payload)
      .set("loading", false)
      .set("error", null);

  case CREATE_PRODUCT_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case GET_PRODUCT_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_PRODUCT_SUCCESS:
    return state
      .set("product", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_PRODUCT_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case UPDATE_PRODUCT_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case UPDATE_PRODUCT_SUCCESS:
    return state
      .set("product", action.payload)
      .set("loading", false)
      .set("error", null);

  case UPDATE_PRODUCT_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case DELETE_PRODUCT_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case DELETE_PRODUCT_SUCCESS:
    return state
      .set("product", action.payload)
      .set("loading", false)
      .set("error", null);

  case DELETE_PRODUCT_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  case CLEAN_PRODUCT_WORKSPACE:
    return initialState;

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const createProductRequest = (data) => ({
  type   : CREATE_PRODUCT_REQUEST,
  payload: data
});

export const createProductSuccess = () => ({
  type: CREATE_PRODUCT_SUCCESS
});

export const createProductFail = (error) => ({
  type   : CREATE_PRODUCT_FAIL,
  payload: error,
  error  : true
});


export const getProductRequest = (data) => ({
  type   : GET_PRODUCT_REQUEST,
  payload: data
});

export const getProductSuccess = (data) => ({
  type   : GET_PRODUCT_SUCCESS,
  payload: data
});

export const getProductFail = (error) => ({
  type   : GET_PRODUCT_FAIL,
  payload: error,
  error  : true
});


export const updateProductRequest = (data) => ({
  type   : UPDATE_PRODUCT_REQUEST,
  payload: data
});

export const updateProductSuccess = () => ({
  type: UPDATE_PRODUCT_SUCCESS
});

export const updateProductFail = (error) => ({
  type   : UPDATE_PRODUCT_FAIL,
  payload: error,
  error  : true
});


export const deleteProductRequest = (id) => ({
  type   : DELETE_PRODUCT_REQUEST,
  payload: id
});

export const deleteProductSuccess = () => ({
  type: DELETE_PRODUCT_SUCCESS
});

export const deleteProductFail = (error) => ({
  type   : DELETE_PRODUCT_FAIL,
  payload: error,
  error  : true
});

export const cleanProductWorkspace = () => ({
  type: CLEAN_PRODUCT_WORKSPACE
});

// ----------------------- SAGAS ------------------------ //

function* createProduct(action) {
  yield sendElement(PRODUCTS_URL, action.payload, createProductSuccess, createProductFail);
}

function* getProduct(action) {
  yield getElement(PRODUCTS_URL, action.payload, getProductSuccess, getProductFail);
}

function* updateProduct(action) {
  yield updateElement(PRODUCTS_URL, action.payload.id, action.payload, updateProductSuccess, updateProductFail);
}

function* deleteProduct(action) {
  yield deleteElement(PRODUCTS_URL, action.payload, deleteProductSuccess, deleteProductFail);
}

export function* watchProductActions() {
  yield takeEvery(CREATE_PRODUCT_REQUEST, createProduct);
  yield takeLatest(GET_PRODUCT_REQUEST, getProduct);
  yield takeEvery(UPDATE_PRODUCT_REQUEST, updateProduct);
  yield takeEvery(DELETE_PRODUCT_REQUEST, deleteProduct);
}

// ------------------ SELECTORS -------------------- //

export const selectProductContainer = (state) => state.containers.products.targetProduct;
export const selectProductData = (state) => selectProductContainer(state).get("product");