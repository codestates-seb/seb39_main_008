import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { BiBookAdd } from 'react-icons/bi';
import { FiPenTool } from 'react-icons/fi';
const Container = styled.div`
  & > svg {
    color: ${({ theme }) => theme.colors.text4};
    margin-bottom: ${({ theme }) => theme.space.spaceM};
  }

  &:hover {
    & > svg {
      color: ${({ theme }) => theme.colors.text3};
    }
  }

  & > p {
    color: ${({ theme }) => theme.colors.text2};
    font-size: ${({ theme }) => theme.fontSize.fontSizeLL};
  }

  background-color: ${({ theme }) => theme.colors.grey};
  box-shadow: ${({ theme }) => theme.boxShadow.shadowM};
  border-radius: ${({ theme }) => theme.borderRadius.borderRadiusM};
  ${({ theme }) => theme.layout.flexCenterColumn};
  cursor: pointer;
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
