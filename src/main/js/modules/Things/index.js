import { fromJS } from "immutable";
import { getElements } from "../../api/index";
import { takeLatest } from "redux-saga/effects";

// ---------------------- CONSTANTS ----------------------- //

const GET_THINGS_REQUEST = "GET_THINGS_REQUEST";
const GET_THINGS_SUCCESS = "GET_THINGS_SUCCESS";
const GET_THINGS_FAIL = "GET_THINGS_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  things : [],
  error  : null,
  loading: false
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {

  case GET_THINGS_REQUEST:
    return state
      .set("loading", true)
      .set("error", null);

  case GET_THINGS_SUCCESS:
    return state
      .set("things", action.payload)
      .set("loading", false)
      .set("error", null);

  case GET_THINGS_FAIL:
    return state
      .set("loading", false)
      .set("error", action.payload);

  default:
    return state;
  }
};

// ----------------- ACTIONS ----------------------- //

export const sendThingsRequest = () => ({
  type: GET_THINGS_REQUEST
});

export const getThingsSuccess = (data) => ({
  type   : GET_THINGS_SUCCESS,
  payload: data
});

export const getThingsFail = (error) => ({
  type   : GET_THINGS_FAIL,
  payload: error,
  error  : true
});

// ----------------------- SAGAS ------------------------ //

function* getThings() {
  yield* getElements("/things", getThingsSuccess, getThingsFail);
}

export function* watchThingsActions() {
  yield takeLatest(GET_THINGS_REQUEST, getThings);
}

// ------------------ SELECTORS -------------------- //

export const selectThingsContainer = (state) => state.containers.things.list;
export const selectThingsData = (state) => selectThingsContainer(state).get("things");
