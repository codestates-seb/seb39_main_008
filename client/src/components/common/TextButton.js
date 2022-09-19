import styled from 'styled-components';

const Button = styled.button`
  height: ${(props) => props.height || 'auto'};
  width: ${(props) => props.width || 'auto'};
  color: ${({ theme }) => theme.colors.text1};
  font-size: ${({ theme }) => theme.fontSize.fontSizeS};
  margin: 0;
  padding: 0;
  border: none;

  &:after {
    display: block;
    content: '';
    border-bottom: solid 1.2px ${({ theme }) => theme.colors.text5};
    transform: scaleX(0);
    transition: transform 300ms ease-in-out;
  }
  &:hover:after {
    transform: scaleX(0.95);
  }
  &:hover {
    color: ${({ theme }) => theme.colors.text2};
  }
  .container {
    border: 1.2px solid ${({ theme }) => theme.colors.black050};
    padding: 2px;
    border-radius: ${({ theme }) => theme.borderRadius.borderRadiusS};
    background-color: ${({ theme }) => theme.colors.white};
    white-space: nowrap;
    ${({ theme }) => theme.layout.flexCenter};
  }
  .container:hover {
    border: none;
    transition: 0.5s ease-out;
    background-color: ${({ theme }) => theme.colors.black050};
  }
  .container:not(:hover) {
    transition: 0.5s ease-out;
  }
  .icon {
    margin-left: 4px;
    ${({ theme }) => theme.layout.flexCenter};
  }
  .text {
    margin: 0 4px;
  }
`;

const TextButton = ({ text, onClick, ...props }) => {
  return (
    <Button onClick={onClick} width={props.width} height={props.height}>
      <div className="container">
        {props.icon && <div className="icon">{props.icon}</div>}
        <p className="text">{text}</p>
      </div>
    </Button>
  );
};

export default TextButton;
