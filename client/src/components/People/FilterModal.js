import styled from 'styled-components';
import { useState } from 'react';

import {
  boxShadow,
  borderRadius,
  space,
  fontSize,
  colors,
} from '../../assets/styles/theme';

const FilterModalContainer = styled.div`
  position: absolute;
  z-index: 1;
  top: 30px;
  padding: ${space.spaceM};
  background: #fff;
  box-shadow: ${boxShadow.shadowXS};
  border-radius: ${borderRadius.borderRadiusM};
  font-size: ${fontSize.fontSizeS};
  color: ${colors.text4};

  > div {
    margin-bottom: ${space.spaceS};
  }

  > div:last-child {
    margin-bottom: 0;
  }

  > div:last-child > div > button {
    color: ${colors.text4};
  }

  > div:last-child > div > button:hover {
    color: ${colors.text2};
  }

  > div > div.title {
    display: block;
    width: 100%;
    font-size: ${fontSize.fontSizeM};
    margin-bottom: ${space.spaceS};
    color: ${colors.text2};
  }

  > div > div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 80px;
  }

  > div > div label,
  div > div input {
    cursor: pointer;
  }

  > div > div label:hover {
    color: ${colors.text2};
  }
`;

const FilterModal = ({
  innerRef,
  setFilterModal,
  handleFilter,
  sortby,
  setSortby,
}) => {
  const [selected, setSelected] = useState(sortby);

  const handleSelect = (e) => {
    switch (e.target.name) {
      case 'filterd':
        setSelected((prev) => {
          return { ...prev, head: e.target.value };
        });
        break;
      case 'sorted':
        setSelected((prev) => {
          return { ...prev, tail: e.target.value };
        });
        break;
      default:
        setSelected({ head: 'follow', tail: 'desc' });
    }
  };

  const handleSort = () => {
    setSortby(selected);
    handleFilter(10, selected);
    // setFilterModal(false);
  };

  return (
    <FilterModalContainer ref={innerRef}>
      <div>
        <div className="title">필터링 기준</div>
        <div>
          <label htmlFor="follow">팔로우</label>
          <input
            id="follow"
            name="filterd"
            type="radio"
            value="follow"
            checked={selected.head === 'follow'}
            onChange={handleSelect}
          ></input>
        </div>
        <div>
          <label htmlFor="write">작성글</label>
          <input
            id="write"
            name="filterd"
            type="radio"
            value="totalwrite"
            checked={selected.head === 'totalwrite'}
            onChange={handleSelect}
          ></input>
        </div>
        <div>
          <label htmlFor="joined">가입일</label>
          <input
            id="joined"
            name="filterd"
            type="radio"
            value="recent"
            checked={selected.head === 'recent'}
            onChange={handleSelect}
          ></input>
        </div>
      </div>
      <div>
        <div className="title">정렬 기준</div>
        <div>
          <label htmlFor="many">내림차순</label>
          <input
            id="many"
            name="sorted"
            type="radio"
            value="desc"
            checked={selected.tail === 'desc'}
            onChange={handleSelect}
          ></input>
        </div>
        <div>
          <label htmlFor="little">오름차순</label>
          <input
            id="little"
            name="sorted"
            type="radio"
            value="asc"
            checked={selected.tail === 'asc'}
            onChange={handleSelect}
          ></input>
        </div>
      </div>
      <div>
        <div>
          <button onClick={handleSort}>apply</button>
          <button onClick={() => setFilterModal(false)}>cancel</button>
        </div>
      </div>
    </FilterModalContainer>
  );
};

export default FilterModal;
