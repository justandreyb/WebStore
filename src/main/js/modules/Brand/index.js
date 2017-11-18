import { fromJS } from "immutable";
import { sendElement, getElement, updateElement, deleteElement } from "../../api";
import { takeEvery, takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const BRANDS_URL = "/brands";

const CREATE_BRAND_REQUEST = "CREATE_BRAND_REQUEST";
const CREATE_BRAND_SUCCESS = "CREATE_BRAND_SUCCESS";
const CREATE_BRAND_FAIL = "CREATE_BRAND_FAIL";

const GET_BRAND_REQUEST = "GET_BRAND_REQUEST";
const GET_BRAND_SUCCESS = "GET_BRAND_SUCCESS";
const GET_BRAND_FAIL = "GET_BRAND_FAIL";

const UPDATE_BRAND_REQUEST = "UPDATE_BRAND_REQUEST";
const UPDATE_BRAND_SUCCESS = "UPDATE_BRAND_SUCCESS";
const UPDATE_BRAND_FAIL = "UPDATE_BRAND_FAIL";

const DELETE_BRAND_REQUEST = "DELETE_BRAND_REQUEST";
const DELETE_BRAND_SUCCESS = "DELETE_BRAND_SUCCESS";
const DELETE_BRAND_FAIL = "DELETE_BRAND_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  brand  : {},
  error  : null,
  loading: false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case CREATE_BRAND_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case CREATE_BRAND_SUCCESS:
    return state
      .set("brand", action.payload)
      .set("loading", false)
      .set("error", null);

  case CREATE_BRAND_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case GET_BRAND_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_BRAND_SUCCESS:
    return state
      .set("brand", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_BRAND_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case UPDATE_BRAND_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case UPDATE_BRAND_SUCCESS:
    return state
      .set("brand", action.payload)
      .set("loading", false)
      .set("error", null);

  case UPDATE_BRAND_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case DELETE_BRAND_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case DELETE_BRAND_SUCCESS:
    return state
      .set("brand", action.payload)
      .set("loading", false)
      .set("error", null);

  case DELETE_BRAND_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const createBrandRequest = (data) => ({
  type   : CREATE_BRAND_REQUEST,
  payload: data
});

export const createBrandSuccess = () => ({
  type: CREATE_BRAND_SUCCESS
});

export const createBrandFail = (error) => ({
  type   : CREATE_BRAND_FAIL,
  payload: error,
  error  : true
});


export const getBrandRequest = (data) => ({
  type   : GET_BRAND_REQUEST,
  payload: data
});

export const getBrandSuccess = (data) => ({
  type   : GET_BRAND_SUCCESS,
  payload: data
});

export const getBrandFail = (error) => ({
  type   : GET_BRAND_FAIL,
  payload: error,
  error  : true
});


export const updateBrandRequest = (data) => ({
  type   : UPDATE_BRAND_REQUEST,
  payload: data
});

export const updateBrandSuccess = () => ({
  type: UPDATE_BRAND_SUCCESS
});

export const updateBrandFail = (error) => ({
  type   : UPDATE_BRAND_FAIL,
  payload: error,
  error  : true
});


export const deleteBrandRequest = (id) => ({
  type   : DELETE_BRAND_REQUEST,
  payload: id
});

export const deleteBrandSuccess = () => ({
  type: DELETE_BRAND_SUCCESS
});

export const deleteBrandFail = (error) => ({
  type   : DELETE_BRAND_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* createBrand(action) {
  yield sendElement(BRANDS_URL, action.payload, createBrandSuccess, createBrandFail);
}

function* getBrand(action) {
  yield getElement(BRANDS_URL, action.payload, getBrandSuccess, getBrandFail);
}

function* updateBrand(action) {
  yield updateElement(BRANDS_URL, action.payload.id, action.payload, updateBrandSuccess, updateBrandFail);
}

function* deleteBrand(action) {
  yield deleteElement(BRANDS_URL, action.payload, deleteBrandSuccess, deleteBrandFail);
}

export function* watchBrandActions() {
  yield takeEvery(CREATE_BRAND_REQUEST, createBrand);
  yield takeLatest(GET_BRAND_REQUEST, getBrand);
  yield takeEvery(UPDATE_BRAND_REQUEST, updateBrand);
  yield takeEvery(DELETE_BRAND_REQUEST, deleteBrand);
}

// ------------------ SELECTORS -------------------- //

export const selectBrandContainer = (state) => state.containers.brands.targetBrand;
export const selectBrandData = (state) => selectBrandContainer(state).get("brand");
