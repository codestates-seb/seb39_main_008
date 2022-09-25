import styled from 'styled-components';
import { colors, layout, borderRadius, theme } from '../../assets/styles/theme';
const Button = styled.button`
  box-sizing: border-box;
  width: ${(props) => props.width || 'auto'};
  color: ${colors.text1};
  font-size: ${(props) => props.fontSize || theme.fontSize.fontSizeS};
  margin: 0px;
  padding: 0;
  border: none;

  &:after {
    display: block;
    content: '';
    border-bottom: solid 1.2px ${colors.text5};
    transform: scaleX(0);
    transition: transform 300ms ease-in-out;
  }
  &:hover:after {
    transform: scaleX(0.97);
  }
  &:hover {
    color: ${colors.text2};
  }
  .container {
    height: ${(props) => props.height || 'auto'};
    border: 1.2px solid ${colors.black050};
    padding: 2px;
    border-radius: ${borderRadius.borderRadiusS};
    background-color: ${colors.white};
    white-space: nowrap;
    ${layout.flexCenter};
  }
  .container:hover {
    border: 1.2px solid transparent;
    transition: 0.5s ease-out;
    background-color: ${colors.black050};
  }
  .container:not(:hover) {
    transition: 0.5s ease-out;
  }
  .icon {
    margin-left: 4px;
    ${layout.flexCenter};
  }
  .text {
    margin: 0 4px;
  }
`;

const BorderButton = ({ text, onClick, ...props }) => {
  return (
    <Button
      onClick={onClick}
      width={props.width}
      height={props.height}
      fontSize={props.fontSize}
      type={props.type || 'button'}
    >
      <div className="container">
        {props.icon && <div className="icon">{props.icon}</div>}
        <p className="text">{text}</p>
      </div>
    </Button>
  );
};

export default BorderButton;
