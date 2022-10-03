import styled from 'styled-components';
import FilterModal from './FilterModal';

const PeopleFilterContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  padding-right: calc(
    ${({ theme }) => theme.space.spaceL} + ${({ theme }) => theme.space.spaceM}
  );
  position: relative;

  > button {
    color: ${({ theme }) => theme.colors.text4};
    font-size: ${({ theme }) => theme.fontSize.fontSizeS};
    padding-left: ${({ theme }) => theme.space.spaceS};
  }

  > button.active {
    color: ${({ theme }) => theme.colors.text2};
  }
`;

const PeopleFilter = ({
  active,
  setActive,
  sortby,
  setSortby,
  handleFilter,
  filterModal,
  setFilterModal,
  isFilterModal,
  isButtonModal,
}) => {
  return (
    <PeopleFilterContainer>
      <button
        className={active === 0 ? 'active' : ''}
        onClick={() => {
          setActive(0);
          handleFilter(10, { head: 'follow', tail: 'desc' });
        }}
      >
        팔로우 순
      </button>
      <button
        className={active === 1 ? 'active' : ''}
        onClick={() => {
          setActive(1);
          handleFilter(10, { head: 'totalwrite', tail: 'desc' });
        }}
      >
        작성글 순
      </button>
      <button
        ref={isButtonModal}
        className={active === 2 ? 'active' : ''}
        onClick={() => {
          setActive(2);
          setFilterModal(!filterModal);
        }}
      >
        필터
      </button>
      {filterModal && (
        <FilterModal
          innerRef={isFilterModal}
          setFilterModal={setFilterModal}
          handleFilter={handleFilter}
          sortby={sortby}
          setSortby={setSortby}
        />
      )}
    </PeopleFilterContainer>
  );
};

export default PeopleFilter;
