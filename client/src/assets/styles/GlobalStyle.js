import { createGlobalStyle } from 'styled-components';
import { reset } from 'styled-reset';

export const GlobalStyle = createGlobalStyle`
${reset};
* {
  box-sizing: border-box;  
  font-family: 'Gowun Batang', serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
html,
  body {
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
  
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
    background-color: transparent;
    border:none;
    outline: 0;
    cursor: pointer;
  }
`;
