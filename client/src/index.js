import ReactDOM from 'react-dom';
import { ThemeProvider } from 'styled-components';
import { BrowserRouter } from 'react-router-dom';
import { store } from './redux/store/store';
import { theme } from './assets/styles/theme';
import { GlobalStyle } from './assets/styles/GlobalStyle';
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
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <App />
      </ThemeProvider>
    </Provider>
  </BrowserRouter>,
  container
);
