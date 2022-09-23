import styled from 'styled-components';
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
  /* bottom: -110px; */
  top: 50%;
  left: 50%;
  min-width: 200px;
  transform: translate((-50%, -50%));
  padding: ${space.spaceM};
  background: #fff;
  box-shadow: ${boxShadow.shadowXS};
  border-radius: ${borderRadius.borderRadiusM};
  font-size: ${fontSize.fontSizeS};
  color: ${colors.text2};

  > div.title {
    display: block;
    width: 100%;
    font-size: ${fontSize.fontSizeM};
    margin-bottom: ${space.spaceS};
  }

  > div {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 60px;
  }

  > div label,
  div input {
    cursor: pointer;
  }
`;

const FilterModal = ({ innerRef, setFilterModal, handleFilter, setSortby }) => {
  return (
    <FilterModalContainer ref={innerRef}>
      <div className="title">필터링 기준</div>
      <div>
        <label htmlFor="follow">팔로우</label>
        <input id="follow" name="filterd" type="radio" value="follow"></input>
      </div>
      <div>
        <label htmlFor="write">작성글</label>
        <input
          id="write"
          name="filterd"
          type="radio"
          value="totalwrite"
        ></input>
      </div>
      <div>
        <label htmlFor="joined">가입일</label>
        <input id="joined" name="filterd" type="radio" value="recent"></input>
      </div>

      <div className="title">정렬 기준</div>
      <div>
        <label htmlFor="many">많은순</label>
        <input id="many" name="sorted" type="radio" value="desc"></input>
      </div>
      <div>
        <label htmlFor="little">적은순</label>
        <input id="little" name="sorted" type="radio" value="asc"></input>
      </div>

      <div className="title">시간 기준</div>
      <div>
        <label htmlFor="last">최신순</label>
        <input id="last" name="sorted" type="radio" value="desc"></input>
      </div>
      <div>
        <label htmlFor="first">과거순</label>
        <input id="first" name="sorted" type="radio" value="asc"></input>
      </div>
      <div>
        <button
          onClick={() => {
            setSortby('followdesc');
            handleFilter(10, 'followdesc');
            setFilterModal(false);
          }}
        >
          apply
        </button>
        <div>cancel</div>
      </div>
    </FilterModalContainer>
  );
};

export default FilterModal;
