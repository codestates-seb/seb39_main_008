import styled from 'styled-components';

const Button = styled.button`
  box-sizing: border-box;
  height: ${(props) => props.height || 'auto'};
  width: ${(props) => props.width || 'auto'};
  color: ${({ theme }) => theme.colors.text1};
  font-size: ${(props) => props.fontSize || `var(--fontSizeS)`};
  border: 1.2px solid ${({ theme }) => theme.colors.text4};
  border-radius: var(--borderRadiusS);
  background-color: ${({ theme }) => theme.colors.main};
  padding: 2px;
  margin: 0px;
  white-space: nowrap;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    color: ${({ theme }) => theme.colors.text1};
    border: 1.2px solid transparent;
    transition: 0.5s ease-out;
    background-color: ${({ theme }) => theme.colors.text4};
  }

  &:not(:hover) {
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
      {props.icon && <div className="icon">{props.icon}</div>}
      <p className="text">{text}</p>
    </Button>
  );
};

export default BorderButton;
