import { fromJS } from "immutable";
import { sendElement, getElement, updateElement, deleteElement } from "../../api";
import { takeEvery, takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const THINGS_URL = "/things";

const CREATE_THING_REQUEST = "CREATE_THING_REQUEST";
const CREATE_THING_SUCCESS = "CREATE_THING_SUCCESS";
const CREATE_THING_FAIL = "CREATE_THING_FAIL";

const GET_THING_REQUEST = "GET_THING_REQUEST";
const GET_THING_SUCCESS = "GET_THING_SUCCESS";
const GET_THING_FAIL = "GET_THING_FAIL";

const UPDATE_THING_REQUEST = "UPDATE_THING_REQUEST";
const UPDATE_THING_SUCCESS = "UPDATE_THING_SUCCESS";
const UPDATE_THING_FAIL = "UPDATE_THING_FAIL";

const DELETE_THING_REQUEST = "DELETE_THING_REQUEST";
const DELETE_THING_SUCCESS = "DELETE_THING_SUCCESS";
const DELETE_THING_FAIL = "DELETE_THING_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  thing  : {},
  error  : null,
  loading: false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case CREATE_THING_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case CREATE_THING_SUCCESS:
    return state
      .set("thing", action.payload)
      .set("loading", false)
      .set("error", null);

  case CREATE_THING_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case GET_THING_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_THING_SUCCESS:
    return state
      .set("thing", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_THING_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case UPDATE_THING_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case UPDATE_THING_SUCCESS:
    return state
      .set("thing", action.payload)
      .set("loading", false)
      .set("error", null);

  case UPDATE_THING_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);


  case DELETE_THING_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case DELETE_THING_SUCCESS:
    return state
      .set("thing", action.payload)
      .set("loading", false)
      .set("error", null);

  case DELETE_THING_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const createThingRequest = (data) => ({
  type   : CREATE_THING_REQUEST,
  payload: data
});

export const createThingSuccess = () => ({
  type: CREATE_THING_SUCCESS
});

export const createThingFail = (error) => ({
  type   : CREATE_THING_FAIL,
  payload: error,
  error  : true
});


export const getThingRequest = (data) => ({
  type   : GET_THING_REQUEST,
  payload: data
});

export const getThingSuccess = (data) => ({
  type   : GET_THING_SUCCESS,
  payload: data
});

export const getThingFail = (error) => ({
  type   : GET_THING_FAIL,
  payload: error,
  error  : true
});


export const updateThingRequest = (data) => ({
  type   : UPDATE_THING_REQUEST,
  payload: data
});

export const updateThingSuccess = () => ({
  type: UPDATE_THING_SUCCESS
});

export const updateThingFail = (error) => ({
  type   : UPDATE_THING_FAIL,
  payload: error,
  error  : true
});


export const deleteThingRequest = (id) => ({
  type   : DELETE_THING_REQUEST,
  payload: id
});

export const deleteThingSuccess = () => ({
  type: DELETE_THING_SUCCESS
});

export const deleteThingFail = (error) => ({
  type   : DELETE_THING_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* createThing(action) {
  yield* sendElement(THINGS_URL, action.payload, createThingSuccess, createThingFail);
}

function* getThing(action) {
  yield* getElement(THINGS_URL, action.payload, getThingSuccess, getThingFail);
}

function* updateThing(action) {
  yield* updateElement(THINGS_URL, action.payload.id, action.payload, updateThingSuccess, updateThingFail);
}

function* deleteThing(action) {
  yield* deleteElement(THINGS_URL, action.payload, deleteThingSuccess, deleteThingFail);
}

export function* watchThingActions() {
  yield takeEvery(CREATE_THING_REQUEST, createThing);
  yield takeLatest(GET_THING_REQUEST, getThing);
  yield takeEvery(UPDATE_THING_REQUEST, updateThing);
  yield takeEvery(DELETE_THING_REQUEST, deleteThing);
}

// ------------------ SELECTORS -------------------- //

export const selectThingContainer = (state) => state.containers.things.targetThing;
export const selectThingData = (state) => selectThingContainer(state).get("thing");
