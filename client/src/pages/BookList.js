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
    id: 1,
    email: 'dsinnie0@geocities.jp',
    password: 'sYVpE41iTW',
    name: 'Dud Sinnie',
    nickname: 'Sinnie',
    infomation: 'Guaifenesin',
    total_follower: 20,
    total_following: 35,
    image:
      'https://cdn.pixabay.com/photo/2018/02/20/20/52/people-3168830_960_720.jpg',
    isFollow: true,
    created_at: '2022-09-15 09:26:06',
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
      <MakeButton type={'book'} />
      {bookListData &&
        bookListData.map((el, idx) => (
          <ContentCard
            isDiary={false}
            key={el.id || idx}
            onClick={() => {
              navigate(`/book/${el.id}`);
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
    </ContentCardGridContainer>
  );
};

export default BookList;
