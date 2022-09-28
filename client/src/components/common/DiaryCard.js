import styled from 'styled-components';
import { colors, space } from '../../assets/styles/theme';

const DiaryCardContainer = styled.div`
  background-image: url(${(props) => props.image});
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center center;
  width: 120px;
  height: 175px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  position: relative;
  z-index: 2;
  cursor: pointer;
  padding: ${space.spaceS};

  > div {
  }

  &:hover > div {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.3);
    z-index: -1;
  }

  &:hover > p {
    opacity: 1;
  }

  > p {
    opacity: 0;
    color: ${colors.white};
  }
`;

const DiaryCard = ({ image, title }) => {
  return (
    <DiaryCardContainer image={image}>
      <div></div>
      <p>{title}</p>
    </DiaryCardContainer>
  );
};

export default DiaryCard;
