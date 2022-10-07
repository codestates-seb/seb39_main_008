import LoginForm from '../components/LoginForm';
import {
  CenterLayout,
  Container,
  ImageBox,
  HeaderBox,
  FormBox,
} from './Signup';
const Login = () => {
  return (
    <CenterLayout>
      <Container>
        <ImageBox>
          <HeaderBox>
            <p>Welcome back to&nbsp;</p>
            <p>the DUSKHOUR</p>
          </HeaderBox>
        </ImageBox>
        <FormBox>
          <p>Login</p>
          <LoginForm />
        </FormBox>
      </Container>
    </CenterLayout>
  );
};

export default Login;
