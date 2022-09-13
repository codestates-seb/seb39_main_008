import { createGlobalStyle } from 'styled-components';
import { reset } from 'styled-reset';

export const GlobalStyle = createGlobalStyle`
${reset};
* {
  box-sizing: border-box;
}
html,
  body {
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  }
  ol, ul, li {
    list-style: none;
  }

  a {
    text-decoration: none;
    &:link,
    &:visited {
      color: inherit;
    }
  }

  button {
    cursor: pointer;
  }
`;
