import { FcGoogle } from 'react-icons/fc';
import { RiKakaoTalkFill } from 'react-icons/ri';
import { theme } from '../assets/styles/theme';
import TextButton from '../components/common/TextButton';
import styled from 'styled-components';
import signinImg from '../assets/img/signinImg.png';

const Signin = () => {
  return (
    <Container>
      <ImageBox>
        <HeaderBox>
          <p>Welcom to </p>
          <p>the DUSKHOUR</p>
        </HeaderBox>
        <ButtonBox>
          <TextButton
            width={'15rem'}
            height={'3rem'}
            fontSize={theme.fontSize.fontSizeM}
            icon={<FcGoogle size={'1.4rem'} />}
            text={'Sign in with Google'}
            onClick={() => {
              window.open(
                // eslint-disable-next-line no-undef
                `${process.env.REACT_APP_GOOGLE_AUTH_URL}`
              );
            }}
          />
          <TextButton
            width={'15rem'}
            height={'3rem'}
            fontSize={theme.fontSize.fontSizeM}
            icon={<RiKakaoTalkFill size={'1.4rem'} />}
            text={'Sign in with Kakao'}
            onClick={() => {
              window.open(
                // eslint-disable-next-line no-undef
                `${process.env.REACT_APP_KAKAO_AUTH_URL}`
              );
            }}
          />
        </ButtonBox>
      </ImageBox>
    </Container>
  );
};

export default Signin;

const Container = styled.div`
  box-sizing: border-box;
  width: 100vw;
  height: 100vh;
  ${theme.layout.flexCenter};
  @media ${theme.screen.mobile} {
    ${theme.layout.flexColumnCenter};
  }
`;
const HeaderBox = styled.div`
  & > p {
    font-size: ${theme.fontSize.fontSizeLL};
  }
  @media ${theme.screen.mobile} {
    & > p {
      display: inline;
      font-size: ${theme.fontSize.fontSizeL};
    }
    padding-top: ${theme.space.spaceL};
    text-align: center;
  }
`;
const ButtonBox = styled.div`
  align-self: flex-end;
  display: flex;
  flex-direction: column;
  justify-content: center;
  & > button {
    margin-top: ${theme.space.spaceS};
  }
  @media ${theme.screen.mobile} {
    align-self: center;
  }
`;
const ImageBox = styled.div`
  box-shadow: ${theme.boxShadow.shadowXL};
  width: 60rem;
  height: 40rem;
  background-image: url(${signinImg});
  background-repeat: no-repeat;
  background-position: center;
  padding: ${theme.space.spaceL};
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border-radius: ${theme.borderRadius.borderRadiusM};
  @media ${theme.screen.tablet} {
    width: 100%;
    height: 100%;
  }
  @media ${theme.screen.mobile} {
    width: 100%;
    height: 100%;
  }
`;
