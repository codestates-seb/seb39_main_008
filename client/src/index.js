import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import { store } from './redux/store/store';
import { Provider } from 'react-redux';
import { worker } from './mocks/browser';
import App from './App';
const container = document.getElementById('root');
worker.start({
  // quiet: true,
  onUnhandledRequest: () => {
    return;
  },
});
ReactDOM.render(
  <BrowserRouter>
    <Provider store={store}>
      <App />
    </Provider>
  </BrowserRouter>,
  container
);
