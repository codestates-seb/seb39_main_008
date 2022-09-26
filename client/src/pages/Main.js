import ContentCard from '../components/common/ContentCard';
import { useEffect, useState } from 'react';
import { getDiaries } from '../lib/axios';
import styled from 'styled-components';
import { space } from '../assets/styles/theme';
export const ContentCardGridContainer = styled.div`
  margin: ${space.spaceL};
  display: grid;
  padding: ${space.spaceM};
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  grid-auto-columns: minmax(350px, 407px);
  grid-auto-rows: 286px;
  grid-gap: ${space.spaceM};
  & > .item {
  }
`;
const Main = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    getDiaries().then((res) => {
      setData(
        res.data.map((el) => {
          return {
            diaryId: el.diaryId,
            thumbnailURL: el.userImage,
            isFollow: el.isFollow,
            nickname: el.nickname,
            memberId: el.memberId,
            profileURL: el.userPicture,
            totalComment: el.totalComment,
            totalLike: el.totalLike,
            createdAt: el.createdAt,
            title: el.title,
          };
        })
      );
    });
  }, []);
  return (
    <ContentCardGridContainer>
      {data &&
        data.map((el, idx) => (
          <ContentCard
            onClick={() => {
              // navigate(`/book/:bookId/:diaryId`);
            }}
            className="item"
            data={el}
            key={idx}
            isDiary={true}
          />
        ))}
    </ContentCardGridContainer>
  );
};

export default Main;
