import { fromJS } from "immutable";
import { sendElement, getElement, updateElement, deleteElement } from "../../api";
import { takeEvery, takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const CATEGORIES_URL = "/categories";

const CREATE_CATEGORY_REQUEST = "CREATE_CATEGORY_REQUEST";
const CREATE_CATEGORY_SUCCESS = "CREATE_CATEGORY_SUCCESS";
const CREATE_CATEGORY_FAIL = "CREATE_CATEGORY_FAIL";

const GET_CATEGORY_REQUEST = "GET_CATEGORY_REQUEST";
const GET_CATEGORY_SUCCESS = "GET_CATEGORY_SUCCESS";
const GET_CATEGORY_FAIL = "GET_CATEGORY_FAIL";

const UPDATE_CATEGORY_REQUEST = "UPDATE_CATEGORY_REQUEST";
const UPDATE_CATEGORY_SUCCESS = "UPDATE_CATEGORY_SUCCESS";
const UPDATE_CATEGORY_FAIL = "UPDATE_CATEGORY_FAIL";

const DELETE_CATEGORY_REQUEST = "DELETE_CATEGORY_REQUEST";
const DELETE_CATEGORY_SUCCESS = "DELETE_CATEGORY_SUCCESS";
const DELETE_CATEGORY_FAIL = "DELETE_CATEGORY_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  category: {},
  error   : null,
  loading : false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case CREATE_CATEGORY_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case CREATE_CATEGORY_SUCCESS:
    return state
      .set("category", action.payload)
      .set("loading", false)
      .set("error", null);

  case CREATE_CATEGORY_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case GET_CATEGORY_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_CATEGORY_SUCCESS:
    return state
      .set("category", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_CATEGORY_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case UPDATE_CATEGORY_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case UPDATE_CATEGORY_SUCCESS:
    return state
      .set("category", action.payload)
      .set("loading", false)
      .set("error", null);

  case UPDATE_CATEGORY_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case DELETE_CATEGORY_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case DELETE_CATEGORY_SUCCESS:
    return state
      .set("category", action.payload)
      .set("loading", false)
      .set("error", null);

  case DELETE_CATEGORY_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const createCategoryRequest = (data) => ({
  type   : CREATE_CATEGORY_REQUEST,
  payload: data
});

export const createCategorySuccess = () => ({
  type: CREATE_CATEGORY_SUCCESS
});

export const createCategoryFail = (error) => ({
  type   : CREATE_CATEGORY_FAIL,
  payload: error,
  error  : true
});


export const getCategoryRequest = (data) => ({
  type   : GET_CATEGORY_REQUEST,
  payload: data
});

export const getCategorySuccess = (data) => ({
  type   : GET_CATEGORY_SUCCESS,
  payload: data
});

export const getCategoryFail = (error) => ({
  type   : GET_CATEGORY_FAIL,
  payload: error,
  error  : true
});


export const updateCategoryRequest = (data) => ({
  type   : UPDATE_CATEGORY_REQUEST,
  payload: data
});

export const updateCategorySuccess = () => ({
  type: UPDATE_CATEGORY_SUCCESS
});

export const updateCategoryFail = (error) => ({
  type   : UPDATE_CATEGORY_FAIL,
  payload: error,
  error  : true
});


export const deleteCategoryRequest = (id) => ({
  type   : DELETE_CATEGORY_REQUEST,
  payload: id
});

export const deleteCategorySuccess = () => ({
  type: DELETE_CATEGORY_SUCCESS
});

export const deleteCategoryFail = (error) => ({
  type   : DELETE_CATEGORY_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* createCategory(action) {
  yield sendElement(CATEGORIES_URL, action.payload, createCategorySuccess, createCategoryFail);
}

function* getCategory(action) {
  yield getElement(CATEGORIES_URL, action.payload, getCategorySuccess, getCategoryFail);
}

function* updateCategory(action) {
  yield updateElement(CATEGORIES_URL, action.payload.id, action.payload, updateCategorySuccess, updateCategoryFail);
}

function* deleteCategory(action) {
  yield deleteElement(CATEGORIES_URL, action.payload, deleteCategorySuccess, deleteCategoryFail);
}

export function* watchCategoryActions() {
  yield takeEvery(CREATE_CATEGORY_REQUEST, createCategory);
  yield takeLatest(GET_CATEGORY_REQUEST, getCategory);
  yield takeEvery(UPDATE_CATEGORY_REQUEST, updateCategory);
  yield takeEvery(DELETE_CATEGORY_REQUEST, deleteCategory);
}

// ------------------ SELECTORS -------------------- //

export const selectCategoryContainer = (state) => state.containers.categories.targetCategory;
export const selectCategoryData = (state) => selectCategoryContainer(state).get("category");
