import ContentCard from '../components/common/ContentCard';
import { useEffect, useState } from 'react';
import { getDiaries, getMembers } from '../lib/axios';
import styled from 'styled-components';
import { space, fontSize } from '../assets/styles/theme';
import UserCard from '../components/common/UserCard';

const MainContainer = styled.div`
  padding: ${space.spaceL};
  padding-top: 0;

  > div:last-child {
    padding: ${space.spaceM};
  }
`;

const PopularPeopleContainer = styled.div`
  max-width: 835px;
  margin: 0 ${space.spaceM};

  > div {
    padding: ${space.spaceL} ${space.spaceS};
    padding-top: ${space.spaceM};
    margin-bottom: ${space.spaceM};
    gap: 20px;
    display: flex;
    flex-wrap: no-wrap;
    overflow-x: auto;
  }
`;

const ContentCardGridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  grid-auto-columns: minmax(350px, 407px);
  grid-auto-rows: 286px;
  grid-gap: ${space.spaceM};
`;

const TitleFilterBox = styled.div`
  display: flex;
  justify-content: space-between;

  > div {
    padding: 0 ${space.spaceM};
  }
`;

const SubTitle = styled.p`
  font-size: ${fontSize.fontSizeL};
  padding: 0 ${space.spaceM};
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
    setData(resDiaries.data);
    const resPeople = await getMembers(1, 10, 'followdesc');
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
