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
    padding:0;
    background-color: transparent;
    border:none;
    outline: 0;
    cursor: pointer;
  }
  
  :root{
  --fontSizeLL: 36px;
  --fontSizeL: 24px;
  --fontSizeM: 16px;
  --fontSizeS: 12px;
  
  --shadowXS: rgba(0, 0, 0, 0.3) 0px 3px 6px, rgba(0, 0, 0, 0.22) 0px 3px 4px;
  --shadowS: rgba(0, 0, 0, 0.3) 0px 7px 14px, rgba(0, 0, 0, 0.22) 0px 6px 6px;
  --shadowM:
      rgba(0, 0, 0, 0.3) 0px 11px 22px, rgba(0, 0, 0, 0.22) 0px 9px 8px;
  --shadowL:
      rgba(0, 0, 0, 0.3) 0px 15px 30px, rgba(0, 0, 0, 0.22) 0px 12px 10px;
  --shadowXL:
      rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;
  
  --borderRadiusL: 12px;
  --borderRadiusM: 8px;
  --borderRadiusS: 4px;
  
  --spaceL: 40px;
  --spaceM: 20px;
  --spaceS: 10px;
}
`;
