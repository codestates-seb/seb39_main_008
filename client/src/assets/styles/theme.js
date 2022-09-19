const colors = {
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

export const theme = { colors, fontSize, borderRadius, space, layout };
