import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const ErrorContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  padding-top: 50px;
  height: 100vh;
  color: ${({ theme }) => theme.colors.text1};

  > div {
    margin-bottom: 30px;
  }

  > button {
    font-size: 20px;
    border: none;
    outline: none;
    background: none;
    color: ${({ theme }) => theme.colors.text3};
    cursor: pointer;

    &:hover {
      color: ${({ theme }) => theme.colors.text1};
    }
  }
`;

const Error = () => {
  const navigate = useNavigate();

  return (
    <ErrorContainer>
      <div>잘못된 URL이에요!</div>
      <button
        onClick={() => {
          navigate(-1);
        }}
      >
        이전 페이지로 돌아가기
      </button>
    </ErrorContainer>
  );
};

export default Error;
