import { fromJS } from "immutable";
import { takeLatest, call, put } from "redux-saga/effects";

export const GET_API_DATA_REQUEST = "GET_API_DATA_REQUEST";
export const GET_API_DATA_SUCCESS = "GET_API_DATA_SUCCESS";
export const GET_API_DATA_FAIL = "GET_API_DATA_FAIL";

// --------------------- INITIAL STATE --------------------- //

const initialState = fromJS({
  apiData        : null,
  apiDataLoading : null,
  apiDataLoaded  : null,
  apiDataError   : null,
  applicationName: "WebStore"
});

// ----------------------- REDUCER ------------------------ //

export const reducer = (state = initialState, action) => {
  switch (action.type) {
  case GET_API_DATA_REQUEST:
    return state
      .set("apiDataLoading", true)
      .set("apiDataError", null);
  case GET_API_DATA_SUCCESS:
    return state
      .set("apiData", action.data)
      .set("apiDataLoading", false)
      .set("apiDataLoaded", true)
      .set("apiDataError", null);
  case GET_API_DATA_FAIL:
    return state
      .set("apiDataLoading", false)
      .set("apiDataLoaded", false)
      .set("apiDataError", action.error);
  default:
    return state;
  }
};


// ----------------------- SAGAS ------------------------ //

export const fetchData = (url, options) => {
  const fetchRequest = new Request(url, options);

  return fetch(fetchRequest)
    .then((response) =>
      response.json().then((result) => ({ result }))
    )
    .catch((error) => ({ error }));
};

export function* getAPIData() {
  const { result, error } = yield call(fetchData, "/get", { method: "get" });

  if (error)
    yield put(getAPIDataFail(error));
  

  yield put(getAPIDataSuccess(result));
}

export function* apiData() {
  yield getAPIData();
}

export function* watchAppActions() {
  yield takeLatest(GET_API_DATA_REQUEST, apiData);
}

// ----------------- ACTIONS ----------------------- //

export const getAPIDataRequest = () => ({
  type: GET_API_DATA_REQUEST
});

export const getAPIDataSuccess = (data) => ({
  type: GET_API_DATA_SUCCESS,
  data
});

export const getAPIDataFail = (error) => ({
  type: GET_API_DATA_FAIL,
  error
});

// ------------------ SELECTORS -------------------- //

export const selectAppContainer = (state) => state.containers.app.workspace;
export const selectApiData = (state) => selectAppContainer(state).get("apiData");
export const selectApplicationName = (state) => selectAppContainer(state).get("applicationName");
