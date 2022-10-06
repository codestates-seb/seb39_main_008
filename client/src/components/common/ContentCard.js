import styled from 'styled-components';
import Thumbnail from './Thumbnail';
import { FiMessageSquare, FiBook } from 'react-icons/fi';
import { HiHeart } from 'react-icons/hi';
import Profile from './Profile';
import Date from './Date';

const Container = styled.article`
  cursor: pointer;
  overflow: hidden;
  padding: var(--spaceS);
  background-color: ${({ theme }) => theme.colors.contentCard};
  box-shadow: var(--shadowM);
  border-radius: var(--borderRadiusM);

  .mid {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-bottom: var(--spaceM);
    margin-top: var(--spaceS);
  }
`;

const Details = styled.div`
  margin-left: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: calc(var(--spaceS) / 3);
`;

const Labels = styled.div`
  text-align: end;
  display: flex;
  align-items: center;

  & > p {
    margin: 0 10px 0 4px;
  }

  & svg {
    color: ${({ theme }) => theme.colors.text1};
  }
`;

const Label = styled.span`
  white-space: nowrap;
  margin-left: auto;
  align-self: flex-end;
  background-color: ${({ theme }) => theme.colors.black};
  color: ${({ theme }) => theme.colors.white};
  padding: 3px 10px;
  border-radius: var(--borderRadiusS);
`;

const Text = styled.p`
  color: ${({ theme }) => theme.colors.text1};
  font-size: ${(props) =>
    props.fontsize ? props.fontsize : `var(--fontSizeM)`};
`;

const ContentCard = ({ data, isDiary, onClick }) => {
  return (
    <Container onClick={onClick}>
      <Thumbnail
        height="166px"
        borderRadius={`var(--borderRadiusM)`}
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
                <Text fontsize={`var(--fontSizeS)`}>{data.totalComment}</Text>
                <HiHeart />
                <Text fontsize={`var(--fontSizeS)`}>{data.totalLike}</Text>
              </>
            ) : (
              <>
                <FiBook />
                <Text fontsize={`var(--fontSizeS)`}>
                  {data.total_diary_count}
                </Text>
              </>
            )}
            <Label>{'전체공개'}</Label>
          </Labels>
          <Date date={data.createdAt} />
        </Details>
      </div>
      <Text>{data.title} </Text>
    </Container>
  );
};

export default ContentCard;
