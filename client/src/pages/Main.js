import ContentCard from '../components/common/ContentCard';
import { useEffect, useState } from 'react';
import { getDiaries, getMembers } from '../lib/api';
import styled from 'styled-components';
import UserCard from '../components/common/UserCard';

const MainContainer = styled.div`
  & > div:last-child {
    padding: var(--spaceM);
  }
`;

const PopularPeopleContainer = styled.div`
  max-width: 835px;
  margin: 0 var(--spaceM);

  > div {
    padding: var(--spaceL) var(--spaceS);
    padding-top: var(--spaceM);
    margin-bottom: var(--spaceM);
    gap: 20px;
    display: flex;
    flex-wrap: no-wrap;
    overflow-x: auto;
  }
`;

export const ContentCardGridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  grid-auto-columns: minmax(350px, 407px);
  grid-auto-rows: 286px;
  grid-gap: var(--spaceM);
`;

const TitleFilterBox = styled.div`
  display: flex;
  justify-content: space-between;

  > div {
    padding: 0 var(--spaceM);
  }
`;

const SubTitle = styled.p`
  font-size: var(--fontSizeL);
  padding: 0 var(--spaceM);
  color: ${({ theme }) => theme.colors.text2};
`;

const Main = ({ setHeaderData }) => {
  const [data, setData] = useState([]);
  const [currentTitle, setCurrentTitle] = useState('최신글');
  const [popularPeople, setPopularPeople] = useState([]);

  useEffect(async () => {
    setHeaderData({
      title: '메인페이지',
      description: `인기 작가들과 ${currentTitle}을 확인해보세요.`,
    });
    // api 연동 및 필러팅 기능 추가시 수정
    setCurrentTitle('최신글');
    const resDiaries = await getDiaries();
    console.log(resDiaries);
    setData(resDiaries.data);
    const resPeople = await getMembers(1, 10, 'followdesc');
    console.log(resPeople);
    setPopularPeople(resPeople.data);
  }, []);

  return (
    <MainContainer>
      <SubTitle>DUSKHOUR의 인기 작가들</SubTitle>
      <PopularPeopleContainer>
        <div>
          {popularPeople.map((e) => (
            <UserCard
              key={e.memberId}
              nickname={e.name}
              userImage={e.profile}
              total_content={e.total_content}
              total_follower={e.total_follower}
              isFollow={e.isFollow}
              memberId={e.memberId}
            ></UserCard>
          ))}
        </div>
      </PopularPeopleContainer>
      <TitleFilterBox>
        <SubTitle>작가들의 {currentTitle}</SubTitle>
        <div>
          <span>최신글</span>
          <span>인기글</span>
        </div>
      </TitleFilterBox>
      <ContentCardGridContainer>
        {data &&
          data.map((e) => (
            <ContentCard
              onClick={() => {
                // navigate(`/book/:bookId/:diaryId`);
              }}
              className="item"
              data={e}
              key={e.diaryId}
              isDiary={true}
            />
          ))}
      </ContentCardGridContainer>
    </MainContainer>
  );
};

export default Main;
