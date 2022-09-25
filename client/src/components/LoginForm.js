import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import Input from './common/Input';
import BorderButton from './common/BorderButton';
import TextButton from './common/TextButton';
import { theme } from '../assets/styles/theme';
const schema = yup.object().shape({
  email: yup
    .string()
    .email('이메일 형식으로 적어주세요')
    .required('이메일을 적어주세요'),
  password: yup
    .string()
    .required('영문 숫자 특수문자 포함 8~16자를 입력해주세요'),
});
const data = {
  email: {
    id: 'email',
    type: 'email',
    label: '이메일',
    placeholder: ' ',
  },
  password: {
    id: 'password',
    type: 'password',
    label: '비밀번호',
    placeholder: ' ',
  },
};
const LoginForm = () => {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  const submitForm = (data) => {
    console.log(data);
    alert('login successful');
  };
  return (
    <form onSubmit={handleSubmit(submitForm)}>
      {Object.values(data).map((e, i) => {
        return <Input key={i} option={e} register={register} errors={errors} />;
      })}
      <BorderButton
        width={'100%'}
        height={'2.2rem'}
        fontSize={theme.fontSize.fontSizeM}
        type="submit"
        text={'Login'}
      />
      <TextButton
        text={'아직 계정이 없으신가요? 회원가입 페이지로'}
        onClick={() => {
          navigate('/signup');
        }}
      />
    </form>
  );
};

export default LoginForm;
