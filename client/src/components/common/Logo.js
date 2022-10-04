import { ReactComponent as LogoSvg } from '../../assets/logo.svg';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const LogoButton = styled.div`
  cursor: pointer;
  & > svg > path:nth-child(1) {
    fill: ${({ theme }) => theme.colors.text1};
  }
`;

const Logo = (props) => {
  const navigate = useNavigate();
  return (
    <LogoButton
      onClick={() => {
        navigate('/');
      }}
    >
      <LogoSvg width={props.width} height={props.height} />
    </LogoButton>
  );
};

export default Logo;
