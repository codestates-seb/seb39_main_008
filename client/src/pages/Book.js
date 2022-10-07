import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getBook } from '../lib/axios';
import ContentCard from '../components/common/ContentCard';
import MakeButton from '../components/common/MakeButton';
import styled from 'styled-components';
import { ContentCardGridContainer } from './Main';

const Container = styled.div``;

const Book = ({ setHeaderData }) => {
  const id = useParams().bookId;

  const [data, setData] = useState([]);

  const navigate = useNavigate();

  useEffect(async () => {
    const res = await getBook(id);
    setData(res.data.data);
    setHeaderData({
      title: `${res.data.data[0].nickname}의 일기장입니다`,
      description: '2022년 9월 11일 부터 공개로 작성된 기록들이 담겨있습니다',
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
              // data={data}
              data={el}
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
