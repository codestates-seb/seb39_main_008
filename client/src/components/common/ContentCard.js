import styled from 'styled-components';
import Thumbnail from './Thumbnail';
import {
  borderRadius,
  space,
  colors,
  fontSize,
  boxShadow,
} from '../../assets/styles/theme';
import { FiMessageSquare, FiBook } from 'react-icons/fi';
import { HiHeart } from 'react-icons/hi';
import Profile from './Profile';
import Date from './Date';

const Container = styled.article`
  cursor: pointer;
  overflow: hidden;
  padding: ${space.spaceS};
  background-color: ${colors.white};
  box-shadow: ${boxShadow.shadowM};
  border-radius: ${borderRadius.borderRadiusM};
  .mid {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-bottom: ${space.spaceM};
    margin-top: ${space.spaceS};
  }
`;
const Details = styled.div`
  margin-left: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: calc(${space.spaceS} / 3);
`;
const Labels = styled.div`
  text-align: end;
  display: flex;
  align-items: center;

  & > p {
    margin: 0 10px 0 4px;
  }
`;
const Label = styled.span`
  background-color: ${colors.black};
  color: ${colors.white};

  padding: 3px 10px;
  border-radius: ${borderRadius.borderRadiusS};
`;
const Text = styled.p`
  font-size: ${(props) =>
    props.fontsize ? props.fontsize : fontSize.fontSizeM};
`;
const ContentCard = ({ data, isDiary, onClick }) => {
  return (
    <Container onClick={onClick}>
      <Thumbnail
        height="166px"
        borderRadius={borderRadius.borderRadiusM}
        imageURL={data.diaryimage || data.bookimage}
      />
      <div className="mid">
        {isDiary && (
          <Profile
            userImage={data.profile}
            isFollow={data.isFollow}
            nickname={data.nickname}
            memberId={data.memberId}
          />
        )}
        <Details>
          <Labels>
            {isDiary ? (
              <>
                <FiMessageSquare />
                <Text fontsize={fontSize.fontSizeS}>{data.totalComment}</Text>
                <HiHeart />
                <Text fontsize={fontSize.fontSizeS}>{data.totalLike}</Text>
              </>
            ) : (
              <>
                <FiBook />
                <Text fontsize={fontSize.fontSizeS}>{data.totalbook}</Text>
              </>
            )}
            <Label>{isDiary ? '일상' : '공개여부'}</Label>
          </Labels>
          <Date date={data.createdAt} />
        </Details>
      </div>
      <Text>{data.title} </Text>
    </Container>
  );
};

export default ContentCard;
