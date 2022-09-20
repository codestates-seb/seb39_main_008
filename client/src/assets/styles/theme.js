const colors = {
  dimGrey: '#babfc47d',
  white: '#ffffff',
  black: '#0c0d0e',
  grey: '#c8ccd0',
  yellow: '#fbf3d5',
  green: '#5eba7d',
  blue: '#0074cc',
  powder: '#e1ecf4',
  red: '#d0393e',
  black025: '#f7fafa',
  black050: '#f1f2f3',
  black075: '#e3e6e8',
  black100: '#d6d9dc',
  black150: '#c8ccd0',
  text5: '#babfc4',
  text4: '#6a737c',
  text3: '#3b4045',
  text2: '#232629',
  text1: '#0c0d0e',
};
const fontSize = {
  fontSizeLL: '36px',
  fontSizeL: '24px',
  fontSizeM: '16px',
  fontSizeS: '12px',
};

const boxShadow = {
  shadowXS: '0px 1px 4px -1px rgba(28, 48, 74, 0.5)',
  shadowS: 'rgba(0, 0, 0, 0.05) 0px 1px 2px 0px',
  shadowM:
    '0px 8px 24px -4px rgba(28, 50, 79, 0.38), 0px 2px 6px 0px rgba(28, 55, 90, 0.16)',
  shadowL:
    '0px 12px 48px -6px rgba(28, 50, 79, 0.38), 0px 3px 18px -2px rgba(28, 55, 90, 0.16)',
  shadowXL: 'rgba(33, 35, 38, 0.1) 0px 10px 10px -10px',
};
const borderRadius = {
  borderRadiusL: '12px',
  borderRadiusM: '8px',
  borderRadiusS: '4px',
};
const space = {
  spaceL: '40px',
  spaceM: '20px',
  spaceS: '10px',
};
const screen = {
  mobile: `(max-width: 576px)`,
  tablet: `(min-width: 768px) and (max-width: 991.98px)`,
  desctop: `(min-width: 992)`,
};

const layout = {
  flexCenter: `
  display: flex;
  align-items: center;
  justify-content: center;
`,
  flexCenterColumn: `
display: flex;
flex-direction: column;
justify-contents: center;
align-items: center;
`,
};

export const theme = {
  colors,
  fontSize,
  borderRadius,
  space,
  layout,
  screen,
  boxShadow,
};
