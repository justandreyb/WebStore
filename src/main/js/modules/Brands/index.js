import { fromJS } from "immutable";
import { getElements } from "../../api";
import { takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const GET_BRANDS_REQUEST = "GET_BRANDS_REQUEST";
const GET_BRANDS_SUCCESS = "GET_BRANDS_SUCCESS";
const GET_BRANDS_FAIL = "GET_BRANDS_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  brands : [],
  error  : null,
  loading: false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case GET_BRANDS_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_BRANDS_SUCCESS:
    return state
      .set("brands", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_BRANDS_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const sendBrandsRequest = () => ({
  type: GET_BRANDS_REQUEST
});

export const getBrandsSuccess = (data) => ({
  type   : GET_BRANDS_SUCCESS,
  payload: data
});

export const getBrandsFail = (error) => ({
  type   : GET_BRANDS_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* getBrands() {
  yield* getElements("/brands", getBrandsSuccess, getBrandsFail);
}

export function* watchBrandsActions() {
  yield takeLatest(GET_BRANDS_REQUEST, getBrands);
}

// ------------------ SELECTORS -------------------- //

export const selectBrandsContainer = (state) => state.containers.brands.list;
export const selectBrandsData = (state) => selectBrandsContainer(state).get("brands");
