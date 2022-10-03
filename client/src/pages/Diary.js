import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getDiary } from '../lib/axios';
import Date from '../components/common/Date';
import Profile from '../components/common/Profile';
import TextEditor from '../components/TextEditor';
import Comment from '../components/Diary/Comment';
import styled from 'styled-components';
import { HiHeart, HiOutlineHeart } from 'react-icons/hi';

const TEMPCONTENT = `<h2><span style="background-color: rgb(255, 255, 2…ll">용할 수 있도록 한다'라는 사명을 가지고 사업을 하고 있다.</span></h2>`;

const Diary = ({ setHeaderData }) => {
  const [diary, setDiary] = useState(null);

  const { diaryId } = useParams();

  useEffect(() => {
    setHeaderData({
      title: '일기장 제목 book정보 안옴',
      description: 'book정보 안옴',
    });
  }, []);

  useEffect(async () => {
    // const diaryAndComment = await Promise.all([getDiary(id),getComment(id)]);
    const res = await getDiary(diaryId);
    console.log(res.data);
    setDiary(res.data);
  }, []);

  return (
    <Container>
      {diary && (
        <>
          <Titles>
            <h1>{diary.title}</h1>
            <h2>{diary.subtitle}</h2>
            <Date date={diary.createdAt} />
          </Titles>
          <Contents>
            <div className="like">
              {diary.isLike ? <HiHeart /> : <HiOutlineHeart />}
              <span>{diary.totalLike}</span>
            </div>
            <img className="thumbnail" alt="thumbnail" src={diary.diaryimage} />
            <TextEditor content={diary.content || TEMPCONTENT} viewer={true} />
            <Profile
              userImage={diary.profile}
              isFollow={diary.isFollow}
              nickname={diary.nickname}
              memberId={diary.memberId}
            />
          </Contents>
          <Comments>
            <p>{`Comments ${diary.totalComment}`}</p>
            <Comment
              data={{
                commentId: 1,
                nickname: 'dsinnie0',
                profile:
                  'https://robohash.org/eligendieaaut.png?size=300x300&set=set1',
                content:
                  'Adrenalinum, Thyroidinum, Aloe, Calc. carb toxicodendron, Silicea, Spom, ceaAdrenalinum, Thyroidinum, Aloe, Calc. carb., Chelidonium majus, Conium, Graphites, Iodium, Lachesis, Lycopodium, Lycopus virginicus, Phytolacca, Rhus toxicodendron, Silicea, Spongia, Echinaceaㄷㅅㄴㄷㅅㄴㅅ',
                createdAt: '2022-09-15 09:26:06',
              }}
            />
            <Comment
              data={{
                nickname: 'codingjoa',
                profile:
                  'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
              }}
            ></Comment>
          </Comments>
        </>
      )}
    </Container>
  );
};

const Container = styled.div`
  padding: 0 ${({ theme }) => theme.space.spaceM};
`;

const Titles = styled.div`
  h1 {
    font-size: ${({ theme }) => theme.fontSize.fontSizeLL};
    margin-bottom: ${({ theme }) => theme.space.spaceM};
  }

  h2 {
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
    margin-bottom: ${({ theme }) => theme.space.spaceM};
  }

  p {
    margin-top: ${({ theme }) => theme.space.spaceS};
  }
`;

const Contents = styled.div`
  margin: ${({ theme }) => theme.space.spaceM} 0;
  display: flex;
  flex-direction: column;

  .like,
  .quill + div {
    align-self: end;
  }

  .thumbnail {
    padding: ${({ theme }) => theme.space.spaceL};
    width: 100%;
  }
`;

const Comments = styled.div`
  padding: ${({ theme }) => theme.space.spaceM} 0;
  border-top: 1px solid ${({ theme }) => theme.colors.grey};

  & > p:first-child {
    font-size: ${({ theme }) => theme.fontSize.fontSizeL};
  }
`;

export default Diary;
