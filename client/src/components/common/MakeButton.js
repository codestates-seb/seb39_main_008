import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { BiBookAdd } from 'react-icons/bi';
import { FiPenTool } from 'react-icons/fi';
const Container = styled.div`
  & > svg {
    color: ${({ theme }) => theme.colors.text4};
    margin-bottom: var(--spaceM);
  }

  &:hover {
    & > svg {
      color: ${({ theme }) => theme.colors.text1};
    }
  }

  & > p {
    color: ${({ theme }) => theme.colors.text1};
    font-size: var(--fontSizeLL);
  }

  background-color: ${({ theme }) => theme.colors.dimGrey};
  box-shadow: var(--shadowM);
  border-radius: var(--borderRadiusM);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;

  @media screen and (max-width: 576px) {
    height: 250px;
    & > svg {
      width: 60px;
      margin: 0px;
    }
    & > p {
      color: ${({ theme }) => theme.colors.text1};
      font-size: var(--fontSizeL);
    }
  }
`;

const MakeButton = ({ type }) => {
  const navigate = useNavigate();

  const BUTTONOPTION = {
    book: {
      icon: <BiBookAdd size={100} />,
      text: <p>일기장 만들기</p>,
      onClick: () => {
        navigate('/makebook');
      },
    },
    diary: {
      icon: <FiPenTool size={80} />,
      text: <p>기록 남기기</p>,
      onClick: () => {
        navigate('/writediary');
      },
    },
  };

  const { icon, text, onClick } = BUTTONOPTION[type];

  return (
    <Container onClick={onClick}>
      {icon}
      {text}
    </Container>
  );
};

export default MakeButton;
