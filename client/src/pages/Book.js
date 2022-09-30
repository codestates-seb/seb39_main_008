import ContentCard from '../components/common/ContentCard';
import { useEffect, useState } from 'react';
import { getBook } from '../lib/axios';
import { useNavigate, useParams } from 'react-router-dom';
import { ContentCardGridContainer } from './Main';
import MakeButton from '../components/common/MakeButton';
import styled from 'styled-components';
import { space } from '../assets/styles/theme';

const Container = styled.div`
  padding: ${space.spaceL};
  padding-top: 0;
`;

const Book = ({ setHeaderData }) => {
  const id = useParams().bookId;
  const [data, setData] = useState([]);
  const navigate = useNavigate();

  useEffect(async () => {
    const res = await getBook(id);
    setData(res.data);
    setHeaderData({
      title: `${res.data[0].nickname}의 일기장 일기장 제목`,
      description: '북생성일 부터 공개, 비공개로 작성된 기록들이 담겨있습니다',
    });
  }, [id]);

  return (
    <Container>
      {/* //todo 일기장 표지 등 해당 일기장 정보 랜더링 */}
      <ContentCardGridContainer>
        <MakeButton type={'diary'} />
        {data &&
          data.map((el, idx) => (
            <ContentCard
              className="item"
              // API 연동 후 변경
              // data={bookListData}
              data={{
                title: el.title,
                createdAt: el.createdAt,
                profile: el.profile,
                isFollow: true, //안옴
                totalComment: 1000, //안옴
                totalLike: 10000, //안옴
                category: 1,
                diaryimage:
                  'https://images.unsplash.com/photo-1542577731-55541be363d4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
                nickname: el.nickname,
                memberId: el.memberid,
                diaryId: el.diariesid,
              }}
              key={idx}
              isDiary={true}
              onClick={() => {
                navigate(`/diary/${el.diariesid}`);
              }}
            />
          ))}
      </ContentCardGridContainer>
    </Container>
  );
};

export default Book;
