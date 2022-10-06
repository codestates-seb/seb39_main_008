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
  /* position: absolute; */
  /* top: 50%; */
  /* left: 50%; */
  /* transform: translate(-50%, -50%); */
  width: 50rem;
  height: 37rem;
  margin: auto;
  box-shadow: var(--shadowS);
  border-radius: var(--borderRadiusM);
  display: flex;
  color: ${({ theme }) => theme.colors.text1};
  background-color: ${({ theme }) => theme.colors.main};

  @media screen and (min-width: 576px) and (max-width: 991.98px) {
    width: 100%;
    height: 100%;
  }

  @media screen and (max-width: 576px) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    flex-direction: column;
  }
`;

export const HeaderBox = styled.div`
  margin: var(--spaceL);

  & > p {
    color: black;
    font-size: var(--fontSizeL);
  }

  @media screen and(min-width: 576px) {
    margin: 0;
    width: 100%;
    height: 100%;
    background-color: ${({ theme }) => theme.colors.dimGrey};
    display: flex;
    align-items: center;
    justify-content: center;

    & > p {
      font-size: var(--fontSizeL);
    }
  }
`;
export const ImageBox = styled.div`
  width: 25rem;
  background: url(${signinImg}) no-repeat center;

  @media screen and (max-width: 576px) {
    min-width: 400px;
    width: 100%;
    height: 10%;
  }
`;

export const FormBox = styled.div`
  height: 100%;
  width: 50%;
  min-width: 400px;
  padding: var(--spaceL);
  display: flex;
  flex-direction: column;

  & > p {
    text-align: center;
    font-size: var(--fontSizeL);
    margin-bottom: 20px;
  }

  & > form {
    width: 90%;
    margin: auto;
  }

  & > form > .checkKeepLogin {
    width: var(--fontSizeS);
    height: var(--fontSizeS);
    margin-right: calc(var(--fontSizeS) / 2);
    cursor: pointer;
  }

  & > form > button + p,
  & > form > .checkKeepLogin + span {
    display: inline-block;
    font-size: var(--fontSizeS);
    margin: var(--spaceS) 0;
  }

  @media screen and(min-width: 576px) {
    width: 100%;
    height: 100%;

    & > p {
      text-align: center;
      font-size: var(--fontSizeL);
      margin-bottom: var(--spaceL);
    }

    & > form {
      margin: auto;
      height: 100%;
    }

    & > form > button {
      margin-top: var(--spaceL);
    }
  }
`;
