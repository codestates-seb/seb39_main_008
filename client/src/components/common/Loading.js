import Logo from './Logo';
import { theme } from '../../assets/styles/theme';
import styled from 'styled-components';
import LoadingUnit from './LoadingUnit';

const BackGround = styled.div`
  width: 100vw;
  height: 100vh;
  background: ${theme.colors.dimGrey};
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  & > h1 {
    font-size: ${theme.fontSize.fontSizeM};
  }
`;

const Loading = () => {
  return (
    <BackGround>
      <Logo width="120px" height="30px" />
      <LoadingUnit />
    </BackGround>
  );
};

export default Loading;
