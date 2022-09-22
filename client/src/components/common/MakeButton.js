import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import {
  colors,
  boxShadow,
  borderRadius,
  fontSize,
  space,
  layout,
} from '../../assets/styles/theme';
import { BiBookAdd } from 'react-icons/bi';
import { FiPenTool } from 'react-icons/fi';
const Container = styled.div`
  & > svg {
    color: ${colors.text4};
    margin-bottom: ${space.spaceM};
  }
  &:hover {
    & > svg {
      color: ${colors.text3};
    }
  }
  & > p {
    color: ${colors.text2};
    font-size: ${fontSize.fontSizeLL};
  }
  background-color: ${colors.grey};
  box-shadow: ${boxShadow.shadowM};
  border-radius: ${borderRadius.borderRadiusM};
  ${layout.flexCenterColumn};
  cursor: pointer;
`;

const MakeButton = ({ type }) => {
  const navigate = useNavigate();
  const buttonType = {
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
  const { icon, text, onClick } = buttonType[type];
  return (
    <Container onClick={onClick}>
      {icon}
      {text}
    </Container>
  );
};

export default MakeButton;
