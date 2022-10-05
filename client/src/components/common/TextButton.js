import styled from 'styled-components';
const Text = styled.span`
  color: ${({ theme }) => theme.colors.text4};
  font-size: ${(props) =>
    props.fontSize ? props.fontSize : `var(--fontSizeS)`};
  cursor: pointer;

  &:hover {
    color: ${(props) =>
      props.hoverColor ? props.hoverColor : ({ theme }) => theme.colors.text1};
    cursor: pointer;
  }
`;
const TextButton = ({ text, onClick, ...props }) => {
  return (
    <Text
      fontSize={props.fontSize}
      color={props.color}
      hoverColor={props.hoverColor}
      onClick={onClick}
      type={props.type || 'button'}
    >
      {text}
    </Text>
  );
};

export default TextButton;
