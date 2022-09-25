import styled from 'styled-components';
import { theme } from '../../assets/styles/theme';
const Text = styled.span`
  color: ${({ theme }) => theme.colors.text4};
  font-size: ${(props) => props.fontSize || theme.fontSize.fontSizeS};
  cursor: pointer;
  &:hover {
    color: ${(props) => props.hoverColor || theme.colors.text3};
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
