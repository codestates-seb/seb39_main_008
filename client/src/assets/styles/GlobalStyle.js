import { createGlobalStyle } from 'styled-components';
import { reset } from 'styled-reset';

export const GlobalStyle = createGlobalStyle`
${reset};
@import url('https://fonts.googleapis.com/css2?family=Fira+Sans+Extra+Condensed:ital,wght@1,100&family=Gowun+Batang:wght@400;700&display=swap');
* {
  box-sizing: border-box;
}
html,
  body {
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
    font-family: 'Fira Sans Extra Condensed', sans-serif;
font-family: 'Gowun Batang', serif;
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
