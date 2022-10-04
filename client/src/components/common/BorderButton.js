import styled from 'styled-components';

const Button = styled.button`
  box-sizing: border-box;
  background-color: ${({ theme }) => theme.colors.main};
  width: ${(props) => props.width || 'auto'};
  color: ${({ theme }) => theme.colors.text1};
  font-size: ${(props) => props.fontSize || `var(--fontSizeS)`};
  margin: 0px;
  padding: 0;
  border: none;

  &:after {
    display: block;
    content: '';
    border-bottom: solid 1.2px ${({ theme }) => theme.colors.text3};
    transform: scaleX(0);
    transition: transform 300ms ease-in-out;
  }

  &:hover:after {
    transform: scaleX(0.97);
  }

  &:hover {
    color: ${({ theme }) => theme.colors.text1};
  }

  .container {
    height: ${(props) => props.height || 'auto'};
    border: 1.2px solid ${({ theme }) => theme.colors.text4};
    padding: 2px;
    border-radius: var(--borderRadiusS);
    white-space: nowrap;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .container:hover {
    border: 1.2px solid transparent;
    transition: 0.5s ease-out;
    background-color: ${({ theme }) => theme.colors.text4};
  }

  .container:not(:hover) {
    transition: 0.5s ease-out;
  }

  .icon {
    margin-left: 4px;
    display: flex;
    align-items: center;
    justify-content: center;
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
