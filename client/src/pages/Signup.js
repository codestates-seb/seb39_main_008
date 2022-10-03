import styled from 'styled-components';
import signinImg from '../assets/img/signinImg.png';
import SignupForm from '../components/SignupForm';

const Signup = () => {
  return (
    <Container>
      <ImageBox>
        <HeaderBox>
          <p>Welcome to&nbsp;</p>
          <p>the DUSKHOUR</p>
        </HeaderBox>
      </ImageBox>
      <FormBox>
        <p>Sign up</p>
        <SignupForm />
      </FormBox>
    </Container>
  );
};

export default Signup;

export const Container = styled.div`
  box-sizing: border-box;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 50rem;
  height: 37rem;
  margin: auto;
  box-shadow: ${({ theme }) => theme.boxShadow.shadowS};
  border-radius: ${({ theme }) => theme.borderRadius.borderRadiusM};
  display: flex;

  @media ${({ theme }) => theme.screen.tablet} {
    width: 100%;
    height: 100%;
  }

  @media ${({ theme }) => theme.screen.mobile} {
    ${({ theme }) => theme.layout.flexColumnCenter};
    width: 100%;
    height: 100%;
    flex-direction: column;
  }
`;

export const HeaderBox = styled.div`
  margin: ${({ theme }) => theme.space.spaceL};

  & > p {
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
  }

  @media ${({ theme }) => theme.screen.mobile} {
    margin: 0;
    width: 100%;
    height: 100%;
    background-color: ${({ theme }) => theme.colors.dimGrey};
    ${({ theme }) => theme.layout.flexCenter}

    & > p {
      font-size: ${({ theme }) => theme.fontSize.fontSizeL};
    }
  }
`;
export const ImageBox = styled.div`
  width: 25rem;
  background: url(${signinImg}) no-repeat center;

  @media ${({ theme }) => theme.screen.mobile} {
    min-width: 400px;
    width: 100%;
    height: 10%;
  }
`;

export const FormBox = styled.div`
  height: 100%;
  width: 50%;
  min-width: 400px;
  padding: ${({ theme }) => theme.space.spaceL};
  display: flex;
  flex-direction: column;

  & > p {
    text-align: center;
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
    margin-bottom: 20px;
  }

  & > form {
    width: 90%;
    margin: auto;
  }

  & > form > .checkKeepLogin {
    width: ${({ theme }) => theme.fontSize.fontSizeS};
    height: ${({ theme }) => theme.fontSize.fontSizeS};
    margin-right: calc(${({ theme }) => theme.fontSize.fontSizeS} / 2);
    cursor: pointer;
  }

  & > form > button + p,
  & > form > .checkKeepLogin + span {
    display: inline-block;
    font-size: ${({ theme }) => theme.fontSize.fontSizeS};
    margin: ${({ theme }) => theme.space.spaceS} 0;
  }

  @media ${({ theme }) => theme.screen.mobile} {
    width: 100%;
    height: 100%;

    & > p {
      text-align: center;
      font-size: ${({ theme }) => theme.fontSize.fontSizeL};
      margin-bottom: ${({ theme }) => theme.space.spaceL};
    }

    & > form {
      margin: auto;
      height: 100%;
    }

    & > form > button {
      margin-top: ${({ theme }) => theme.space.spaceL};
    }
  }
`;
