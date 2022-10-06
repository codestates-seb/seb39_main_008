import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import MakeButton from '../components/common/MakeButton';
import { getBookList } from '../lib/axios';
import ContentCard from '../components/common/ContentCard';
import { ContentCardGridContainer } from './Main';

const BookList = ({ setHeaderData }) => {
  const { memberId } = useParams();
  const [bookListData, setBookListData] = useState([]);
  const navigate = useNavigate();

  const USERDATA = {
    name: '김코딩',
    profileURL:
      'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
    nickname: 'codingjoa',
  };

  useEffect(async () => {
    const booklist = await getBookList(memberId);
    setBookListData(booklist.data);
    setHeaderData({
      title: `${USERDATA.nickname}의 일기장`,
      description: `총 ${booklist.data.length}개의 일기장을 사용하고 있습니다.`,
    });
  }, [memberId]);

  return (
    <ContentCardGridContainer>
      {bookListData &&
        bookListData.map((el, idx) => (
          <ContentCard
            isDiary={false}
            key={el.bookId || idx}
            onClick={() => {
              navigate(`/book/${el.bookId}`);
            }}
            className="item"
            // API 연동 후 변경
            // data={bookListData}
            data={{
              title: el.title,
              createdAt: el.created_at,
              total_diary_count: 10,
              bookimage: el.image,
              public: false,
            }}
          />
        ))}
      <MakeButton type={'book'} />
    </ContentCardGridContainer>
  );
};

export default BookList;
