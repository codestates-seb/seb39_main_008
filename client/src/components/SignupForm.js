import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import SiginupInput from './SiginupInput';
import BorderButton from './common/BorderButton';
import TextButton from './common/TextButton';
import { theme } from '../assets/styles/theme';
const schema = yup.object().shape({
  name: yup
    .string()
    .min(2, '2자 이상으로 적어주세요')
    .max(20, '최대 20자 입니다.')
    .matches(/^[가-힣]{2,4}|[a-zA-Z]{2,20}$/, '올바른 형식으로 적어주세요')
    .required('이름을 적어주세요'),
  nickname: yup
    .string()
    .min(2, '2자 이상으로 적어주세요')
    .max(20, '최대 20자 입니다.')
    .matches(
      /^[a-zA-Zㄱ-힣0-9-_.]{2,20}$/,
      '한글, 영문, 특수문자 (- _ .) 포함한 2 ~ 20자'
    )
    .required('닉네임을 적어주세요'),
  email: yup
    .string()
    .email('이메일 형식으로 적어주세요')
    .required('이메일을 적어주세요'),
  password: yup
    .string()
    .min(8, '비밀번호는 최소 8자 입니다')
    .max(16, '비밀번호는 최대 16자 입니다.')
    .required('영문 숫자 특수문자 포함 8~16자를 입력해주세요')
    .matches(
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/,
      '영문 숫자 특수문자 포함 8~16자리를 입력해주세요'
    ),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref('password'), null], '비밀번호가 일치하지 않습니다'),
});

const SigninForm = () => {
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
    alert('Sign up successful');
  };
  const data = {
    name: {
      id: 'name',
      type: 'text',
      label: '이름',
      placeholder: '한글 또는 영문',
    },
    nickname: {
      id: 'nickname',
      type: 'text',
      label: '닉네임',
      placeholder: '한글, 영문, 특수문자 (- _ .)',
    },
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
      placeholder: '영문 숫자 특수문자 포함 8~16자',
    },
    confirmPassword: {
      id: 'confirmPassword',
      type: 'password',
      label: '비밀번호 확인',
      placeholder: ' ',
    },
  };
  return (
    <form onSubmit={handleSubmit(submitForm)}>
      {Object.values(data).map((e, i) => {
        return (
          <SiginupInput
            key={i}
            option={e}
            register={register}
            errors={errors}
          />
        );
      })}
      <BorderButton
        width={'100%'}
        height={'2.2rem'}
        fontSize={theme.fontSize.fontSizeM}
        type="submit"
        text={'Sign up'}
      />
      <TextButton
        text={'이미 계정이 있으신가요? 로그인 페이지로'}
        onClick={() => {
          navigate('/login');
        }}
      />
    </form>
  );
};

export default SigninForm;
