import { useState, useRef, useEffect, useCallback } from 'react';
import styled from 'styled-components';
import UserCard from '../components/common/UserCard';
import { space } from '../assets/styles/theme';
import { getMembers } from '../lib/axios';
import PeopleFilter from '../components/People/PeopleFilter';
import LoadingUnit from '../components/common/LoadingUnit';

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

const NoticeContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: ${space.spaceL};
`;

const People = ({ setHeaderData }) => {
  const [userDatas, setUserDatas] = useState([]);
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(10);
  const [sortby, setSortby] = useState({ head: 'follow', tail: 'desc' });
  const [endPage, setEndPage] = useState(false);
  const [isLoading, setLoading] = useState(false);
  const [active, setActive] = useState(0);
  const [filterModal, setFilterModal] = useState(false);
  const obsRef = useRef(null);
  const preventRef = useRef(true);
  const endRef = useRef(false);
  const isFilterModal = useRef();
  const isButtonModal = useRef();

  useEffect(async () => {
    setHeaderData({
      title: '나와 다른, 또 같은 사람들',
      description: 'DUSKHOUR와 함께하는 여러 사람들과 소통해요',
    });

    const handleModal = (e) => {
      if (
        !filterModal &&
        isFilterModal.current &&
        !isFilterModal.current.contains(e.target) &&
        !isButtonModal.current.contains(e.target)
      ) {
        setFilterModal(false);
      }
    };

    const observer = new IntersectionObserver(handlerObs, { threshold: 0.5 });
    if (obsRef.current) observer.observe(obsRef.current);
    window.addEventListener('mousedown', handleModal);

    return () => {
      observer.disconnect();
      window.removeEventListener('mousedown', handleModal);
    };
  }, []);

  useEffect(() => {
    getUserData();
  }, [page, size]);

  const handlerObs = (entries) => {
    const target = entries[0];
    if (!endRef.current && target.isIntersecting && preventRef.current) {
      preventRef.current = false;
      setPage((prev) => prev + 1);
    }
  };

  const handleFilter = (size, sortby) => {
    setPage(1);
    setSize(size);
    setSortby(sortby);
    setUserDatas([]);
  };

  const getUserData = useCallback(async () => {
    setLoading(true);
    const res = await getMembers(page, size, sortby.head + sortby.tail);
    setLoading(false);
    if (res.data) {
      setUserDatas((prev) => [...prev, ...res.data]);
      preventRef.current = true;
      if (res.data.length === 0) {
        preventRef.current = false;
        setEndPage(true);
      }
    } else {
      console.log(res);
    }
  }, [page, size]);
  return (
    <div>
      <PeopleFilter
        active={active}
        setActive={setActive}
        sortby={sortby}
        setSortby={setSortby}
        handleFilter={handleFilter}
        filterModal={filterModal}
        setFilterModal={setFilterModal}
        isFilterModal={isFilterModal}
        isButtonModal={isButtonModal}
      />
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
        <div ref={obsRef}>{isLoading && <LoadingUnit />}</div>
      </PeopleContainer>
    </div>
  );
};

export default People;
