import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getDiary } from '../lib/axios';
import Date from '../components/common/Date';
import Profile from '../components/common/Profile';
import TextEditor from '../components/TextEditor';
import Comment from '../components/Diary/Comment';
import styled from 'styled-components';
import { HiHeart, HiOutlineHeart } from 'react-icons/hi';

const TEMPCONTENT = `<h1>Lorem ipsum odor amet, consectetuer adipiscing elit.<span class="ql-size-small"> </span></h1><p><br></p><h1 class="ql-indent-1"><span class="ql-size-small" style="color: rgb(0, 97, 0);">Convallis ridiculus convallis mauris curae et lacinia amet. </span></h1><ol><li class="ql-indent-1"><span class="ql-size-small">Justo venenatis eget nec cras imperdiet faucibus massa justo. </span></li><li class="ql-indent-1"><span class="ql-size-small">Vivamus euismod condimentum risus per tempor viverra inceptos consequat. Primis augue consequat massa purus ullamcorper sit. </span></li></ol><p><br></p><h3><span class="ql-size-small">Commodo turpis ultrices eros, ultricies pretium himenaeos.</span></h3><h3><span class="ql-size-small"> Nibh id imperdiet litora bibendum egestas posuere. Curae proin congue neque urna euismod morbi. </span></h3><h3><span class="ql-size-small">Auctor commodo inceptos aliquet netus id.</span></h3>`;

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
    // console.log(res.data);
    setDiary(res.data);
  }, []);

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
                  'https://images.unsplash.com/photo-1604607055958-4def78942d6e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80',
                content:
                  'Adrenalinum, Thyroidinum, Aloe, Calc. carb toxicodendron, Silicea, Spom, ceaAdrenalinum, Thyroidinum, Aloe, Calc. carb., Chelidonium majus, Conium, Graphites, Iodium, Lachesis, Lycopodium, Lycopus virginicus, Phytolacca, Rhus toxicodendron, Silicea, Spongia, Echinacea',
                createdAt: '2022-09-15 09:26:06',
              }}
            />
            <Comment
              data={{
                nickname: USERDATA.nickname,
                profile: USERDATA.image,
              }}
            ></Comment>
          </Comments>
        </>
      )}
    </Container>
  );
};

const Container = styled.div`
  padding: 0 var(--spaceM);
`;

const Titles = styled.div`
  h1 {
    font-size: var(--fontSizeLL);
    color: ${({ theme }) => theme.colors.text1};
    margin-bottom: var(--spaceM);
  }

  h2 {
    color: ${({ theme }) => theme.colors.text2};
    font-size: var(--fontSizeL);
    margin-bottom: var(--spaceM);
  }

  p {
    color: ${({ theme }) => theme.colors.text1};
    margin-top: var(--spaceS);
  }
`;

const Contents = styled.div`
  margin: var(--spaceM) 0;
  display: flex;
  flex-direction: column;
  color: ${({ theme }) => theme.colors.text1};

  .like,
  .quill + div {
    align-self: end;
  }

  .thumbnail {
    padding: var(--spaceL);
    width: 100%;
  }
`;

const Comments = styled.div`
  padding: var(--spaceM) 0;
  border-top: 1px solid ${({ theme }) => theme.colors.border};

  & > p:first-child {
    font-size: var(--fontSizeL);
    color: ${({ theme }) => theme.colors.text1};
  }
`;

export default Diary;
