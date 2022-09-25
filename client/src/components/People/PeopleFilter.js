import styled from 'styled-components';
import { space, fontSize, colors } from '../../assets/styles/theme';
import FilterModal from './FilterModal';

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
