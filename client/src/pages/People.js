import { useState, useRef, useEffect } from 'react';
import styled from 'styled-components';
import UserCard from '../components/common/UserCard';
import { space } from '../assets/styles/theme';
import { getMembers } from '../lib/axios';
import PeopleFilter from '../components/People/PeopleFilter';
import LoadingUnit from '../components/common/LoadingUnit';
import useInfinityScroll from '../lib/useInfinityScroll';

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
  const [isLoading, setLoading] = useState(false);
  const [active, setActive] = useState(0);
  const [filterModal, setFilterModal] = useState(false);
  const obsRef = useRef(null);
  const endRef = useRef(false);
  const isFilterModal = useRef();
  const isButtonModal = useRef();

  useInfinityScroll(obsRef, endRef, setPage);

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

    window.addEventListener('mousedown', handleModal);

    return () => {
      window.removeEventListener('mousedown', handleModal);
    };
  }, []);

  useEffect(() => {
    getUserData();
  }, [page]);

  const handleFilter = (size, sortby) => {
    setUserDatas([]);
    setPage(1);
    setSize(size);
    setSortby(sortby);
    endRef.current = false;
  };

  const getUserData = async () => {
    setLoading(true);
    const res = await getMembers(page, size, sortby.head + sortby.tail);
    setLoading(false);
    if (res.data) {
      setUserDatas((prev) => [...prev, ...res.data]);
      if (res.data.length === 0) {
        endRef.current = true;
      }
    } else {
      console.log(res);
    }
  };

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
        {endRef && (
          <NoticeContainer>더 이상 불러올 유저가 없습니다.</NoticeContainer>
        )}
        <div ref={obsRef}>{isLoading && <LoadingUnit />}</div>
      </PeopleContainer>
    </div>
  );
};

export default People;
