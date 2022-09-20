import { createGlobalStyle } from 'styled-components';
import { reset } from 'styled-reset';

export const GlobalStyle = createGlobalStyle`
${reset};
* {
  font-family: 'Gowun Batang', serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  box-sizing: border-box;  

}
html,
  body {  
    font-family: 'Gowun Batang', serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
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
