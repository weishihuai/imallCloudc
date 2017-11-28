import {createStore, applyMiddleware} from "redux";
import thunkMiddleware from "redux-thunk";
import createLogger from "redux-logger";
import rootReducer from "../reducers/rootReducer";

export default function configureStore(preloadedState) {
  const store = createStore(
      rootReducer,
      preloadedState,
      applyMiddleware(thunkMiddleware, createLogger())
  );
  return store
}
