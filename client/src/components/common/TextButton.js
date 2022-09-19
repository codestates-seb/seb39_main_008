import styled from 'styled-components';

const Button = styled.button``;

const TextButton = ({ text, onClick }) => {
  return <Button onClick={onClick}>{text}</Button>;
};

export default TextButton;
