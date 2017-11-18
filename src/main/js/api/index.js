import { call, put } from "redux-saga/effects";
import axios from "axios";

// const serverURL = "";
// Test REST API Server url : https://jsonplaceholder.typicode.com/

const serverURL = "https://reqres.in/api";

export function* getElements(url, successHandler, failHandler) {
  try {
    const response = yield call(axios.get, serverURL + url);

    yield put(successHandler(response.data.data));
  }
  catch (e) {
    yield put(failHandler(e.message));
  }
}

export function* getElement(url, id, successHandler, failHandler) {
  try {
    const response = yield call(axios.get, serverURL + url + "/" + id);
    yield put(successHandler(response.data.data));
  }
  catch (e) {
    yield put(failHandler(e.message));
  }
}

export function* sendElement(url, data, successHandler, failHandler) {
  try {
    const response = yield call(axios.post, serverURL + url, { data: data });
    console.log(response);
    yield put(successHandler());
  }
  catch (e) {
    yield put(failHandler(e.message));
  }
}

export function* updateElement(url, id, data, successHandler, failHandler) {
  try {
    const response = yield call(axios.post, serverURL + url + "/" + id, { data: data });
    console.log(response);
    yield put(successHandler(response));
  }
  catch (e) {
    yield put(failHandler(e.message));
  }
}

export function* deleteElement(url, id, successHandler, failHandler) {
  try {
    const response = yield call(axios.delete, serverURL + url + "/" + id);
    console.log(response);
    yield put(successHandler());
  }
  catch (e) {
    yield put(failHandler(e.message));
  }
}
