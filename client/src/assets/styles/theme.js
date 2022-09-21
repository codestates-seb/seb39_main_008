export const colors = {
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
export const fontSize = {
  fontSizeLL: '36px',
  fontSizeL: '24px',
  fontSizeM: '16px',
  fontSizeS: '12px',
};

export const boxShadow = {
  shadowXS: 'rgba(0, 0, 0, 0.3) 0px 3px 6px, rgba(0, 0, 0, 0.22) 0px 3px 4px',
  shadowS: 'rgba(0, 0, 0, 0.3) 0px 7px 14px, rgba(0, 0, 0, 0.22) 0px 6px 6px',
  shadowM: 'rgba(0, 0, 0, 0.3) 0px 11px 22px, rgba(0, 0, 0, 0.22) 0px 9px 8px',
  shadowL:
    'rgba(0, 0, 0, 0.3) 0px 15px 30px, rgba(0, 0, 0, 0.22) 0px 12px 10px',
  shadowXL:
    'rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px',
};
export const borderRadius = {
  borderRadiusL: '12px',
  borderRadiusM: '8px',
  borderRadiusS: '4px',
};
export const space = {
  spaceL: '40px',
  spaceM: '20px',
  spaceS: '10px',
};
export const screen = {
  mobile: `(max-width: 576px)`,
  tablet: `(min-width: 576px) and (max-width: 991.98px)`,
  desktop: `(min-width: 992px)`,
};

export const layout = {
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
