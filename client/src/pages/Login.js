import LoginForm from '../components/LoginForm';
import { Container, ImageBox, HeaderBox, FormBox } from './Signup';
const Login = () => {
  return (
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
  );
};

export default Login;
