import axios from 'axios';
import { useState, useRef, useEffect, useCallback } from 'react';
import styled from 'styled-components';
import UserCard from '../components/common/UserCard';
import { space, fontSize, colors } from '../assets/styles/theme';

const PeopleContainer = styled.div`
  width: 100%;
  box-sizing: border-box;
  padding: ${space.spaceL};
  position: relative;

  > div:first-child {
    display: grid;
    column-gap: ${space.spaceM};
    row-gap: ${space.spaceL};
    grid-template-columns: repeat(auto-fill, minmax(150px, auto));
    padding: 0 ${space.spaceM};
  }

  > div > div {
    justify-self: center;
  }
`;

const PeopleFilterContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  padding-right: calc(${space.spaceL} + ${space.spaceM});
  position: relative;

  > button {
    color: ${colors.text4};
    font-size: ${fontSize.fontSizeS};
    padding-left: ${space.spaceS};
  }

  > button.active {
    color: ${colors.text2};
  }
`;

const FilterModal = styled.div`
  position: absolute;
  z-index: 1;
  bottom: -110px;
  width: 300px;
  height: 100px;
  background: tomato;
`;

const NoticeContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: ${space.spaceL};
`;

const People = ({ setHeaderData }) => {
  const [userDatas, setUserDatas] = useState([]);
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(10);
  const [sortby, setSortby] = useState('followdesc');
  const [endPage, setEndPage] = useState(false);
  const [isLoading, setLoading] = useState(false);
  const [active, setActive] = useState(0);
  const [filterModal, setFilterModal] = useState(false);
  const obsRef = useRef(null);
  const preventRef = useRef(true);
  const endRef = useRef(false);
  const isFilterModal = useRef();

  useEffect(async () => {
    setHeaderData({
      title: '나와 다른, 또 같은 사람들',
      description: 'DUSKHOUR와 함께하는 여러 사람들과 소통해요',
    });

    const handleModal = (e) => {
      if (
        !filterModal &&
        (!isFilterModal.current || !isFilterModal.current.contains(e.target))
      ) {
        setFilterModal(false);
      }
    };
    const observer = new IntersectionObserver(obsHandler, { threshold: 0.5 });
    if (obsRef.current) observer.observe(obsRef.current);
    window.addEventListener('mousedown', handleModal);

    return () => {
      observer.disconnect();
      window.removeEventListener('mousedown', handleModal);
    };
  }, []);

  useEffect(() => {
    getPost();
  }, [page, size, sortby]);

  const obsHandler = (entries) => {
    const target = entries[0];
    if (!endRef.current && target.isIntersecting && preventRef.current) {
      preventRef.current = false;
      setPage((prev) => prev + 1);
    }
  };

  const handleFilter = (size, sortby) => {
    if (userDatas) {
      switch (sortby) {
        case 'followdesc':
          setUserDatas((prev) => [
            ...prev.sort((a, b) => b.follower - a.follower),
          ]);
          break;
        case 'totalwritedesc':
          setUserDatas((prev) => [
            ...prev.sort((a, b) => b.total_content - a.total_content),
          ]);
          break;
      }
    } else {
      setPage(1);
      setSize(size);
      setSortby(sortby);
      setUserDatas([]);
    }
  };

  const getPost = useCallback(async () => {
    setLoading(true);
    // api호출 방법 변경하기
    const res = await axios.get(
      `/api/v1/members?page=${page}&size=${size}&filterby=${sortby}`
    );
    if (res.data) {
      setUserDatas((prev) => [...prev, ...res.data.data]);
      preventRef.current = true;
      if (res.data.data.length === 0) {
        preventRef.current = false;
        setEndPage(true);
      }
    } else {
      console.log(res);
    }

    setLoading(false);
  }, [page, size, sortby]);
  return (
    <div>
      <PeopleFilterContainer>
        <button
          className={active === 0 ? 'active' : ''}
          onClick={() => {
            setActive(0);
            handleFilter(10, 'followdesc');
          }}
        >
          팔로우 순
        </button>
        <button
          className={active === 1 ? 'active' : ''}
          onClick={() => {
            setActive(1);
            handleFilter(10, 'totalwritedesc');
          }}
        >
          작성글 순
        </button>
        <button
          className={active === 2 ? 'active' : ''}
          onClick={() => {
            setActive(2);
            setFilterModal(!filterModal);
          }}
        >
          필터
        </button>
        {/* 필터 디자인 및 기능구현하기 */}
        {filterModal && (
          <FilterModal ref={isFilterModal}>
            <div>필터링 기준</div>
            <div>
              <label htmlFor="follow">팔로우</label>
              <input
                id="follow"
                name="chk_info"
                type="radio"
                value="follow"
              ></input>
            </div>
            <div>
              <label htmlFor="write">작성글</label>
              <input
                id="write"
                name="chk_info"
                type="radio"
                value="totalwrite"
              ></input>
            </div>
            <div>
              <label htmlFor="joined">가입일</label>
              <input
                id="joined"
                name="chk_info"
                type="radio"
                value="recent"
              ></input>
            </div>
          </FilterModal>
        )}
      </PeopleFilterContainer>
      <PeopleContainer>
        <div>
          {userDatas.map((e) => (
            <UserCard
              key={e.id}
              nickname={e.nickname}
              userImage={e.userPicture}
              total_content={e.total_content}
              isFollow={e.isFollow}
              follower={e.follower}
              memberId={e.id}
            ></UserCard>
          ))}
        </div>
        {endPage && (
          <NoticeContainer>더 이상 불러올 유저가 없습니다.</NoticeContainer>
        )}
        {/* <div>로딩중</div> 자리에 로딩 컴포넌트 불러오기 */}
        <div ref={obsRef}>{isLoading && <div>로딩중</div>}</div>
      </PeopleContainer>
    </div>
  );
};

export default People;
