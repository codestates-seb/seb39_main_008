import styled from 'styled-components';
import Thumbnail from './Thumbnail';
import { FiMessageSquare, FiBook } from 'react-icons/fi';
import { HiHeart } from 'react-icons/hi';
import Profile from './Profile';
import Date from './Date';

const Container = styled.article`
  cursor: pointer;
  overflow: hidden;
  padding: ${({ theme }) => theme.space.spaceS};
  background-color: ${({ theme }) => theme.colors.white};
  box-shadow: ${({ theme }) => theme.boxShadow.shadowM};
  border-radius: ${({ theme }) => theme.borderRadius.borderRadiusM};

  .mid {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-bottom: ${({ theme }) => theme.space.spaceM};
    margin-top: ${({ theme }) => theme.space.spaceS};
  }
`;

const Details = styled.div`
  margin-left: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: calc(${({ theme }) => theme.space.spaceS} / 3);
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
  margin-left: auto;
  align-self: flex-end;
  background-color: ${({ theme }) => theme.colors.black};
  color: ${({ theme }) => theme.colors.white};
  padding: 3px 10px;
  border-radius: ${({ theme }) => theme.borderRadius.borderRadiusS};
`;

const Text = styled.p`
  font-size: ${(props) =>
    props.fontsize ? props.fontsize : ({ theme }) => theme.fontSize.fontSizeM};
`;

const ContentCard = ({ data, isDiary, onClick }) => {
  return (
    <Container onClick={onClick}>
      <Thumbnail
        height="166px"
        borderRadius={({ theme }) => theme.borderRadius.borderRadiusM}
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
                <Text fontsize={({ theme }) => theme.fontSize.fontSizeS}>
                  {data.totalComment}
                </Text>
                <HiHeart />
                <Text fontsize={({ theme }) => theme.fontSize.fontSizeS}>
                  {data.totalLike}
                </Text>
              </>
            ) : (
              <>
                <FiBook />
                <Text fontsize={({ theme }) => theme.fontSize.fontSizeS}>
                  {data.total_diary_count}
                </Text>
              </>
            )}
            <Label>
              {isDiary ? data.category : data.public ? '전체공개' : '비공개'}
            </Label>
          </Labels>
          <Date date={data.createdAt} />
        </Details>
      </div>
      <Text>{data.title} </Text>
    </Container>
  );
};

export default ContentCard;
